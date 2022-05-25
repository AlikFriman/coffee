package test.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.cafe.dto.OrderDto;
import test.cafe.dto.OrderItemDto;
import test.cafe.mapper.DeliveryTypeMapper;
import test.cafe.mapper.OrderItemMapper;
import test.cafe.mapper.OrderMapper;
import test.cafe.model.CoffeeType;
import test.cafe.model.Order;
import test.cafe.model.OrderItem;
import test.cafe.model.type.OrderStatus;
import test.cafe.repository.OrderItemRepository;
import test.cafe.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional // todo Разобраться с транзакциями в СУБД и Spring
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    // TODO: 17.05.2022 Spring IoC, Spring Context
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final DeliveryTypeMapper deliveryTypeMapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CoffeeTypeServiceInternal coffeeTypeService;
    private final CalculationServiceInternal calculationServiceInternal;

    // TODO: 18.05.2022 Реализовать проверки статусов заказов перед совершением операций

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order newOrder = orderMapper.toModel(orderDto);

        newOrder.setStatus(OrderStatus.CREATED);
        newOrder.setSum(BigDecimal.ZERO);

        Order savedOrder = orderRepository.save(newOrder);

        return orderMapper.toDto(savedOrder);
    }

    @Override
    public Optional<OrderDto> editOder(Integer id, OrderDto orderDto) {
        // TODO: 17.05.2022 Изучить Optional

        // Достать из репозитория заказ по id
        return orderRepository.findById(id)
                // Отфильтровываем
                .filter(order -> order.getStatus() == OrderStatus.CREATED)
                // Изменить соответствующие поля
                .map(order -> editInternal(order, orderDto))
                // Сохранить измененный (заказ в репозиторий
                .map(orderRepository::save)
                // Сохраненный заказ преобразовать в DTO
                .map(orderMapper::toDto);
    }

    @Override
    public Optional<OrderDto> confirmOrder(Integer id) {
        return orderRepository.findById(id)
                // Отфильтровываем
                .filter(order -> order.getStatus() == OrderStatus.CREATED)
                // Поменять статус на CONFIRMED
                .map(this::confirmInternal)
                // Сохранить подтвержденный заказ в репозиторий
                .map(orderRepository::save)
                // Сохраненный заказ преобразовать в DTO
                .map(orderMapper::toDto);
    }

    @Override
    public Optional<OrderDto> cancelOrder(Integer id) {
        // Достать из репозитория заказ по id
        return orderRepository.findById(id)
                // Отфильтровываем
                .filter(order -> order.getStatus() == OrderStatus.CREATED)
                // Поменять статус на CANCELED
                .map(this::cancelInternal)
                // Сохранить измененный (отмененный) заказ в репозиторий
                .map(orderRepository::save)
                // Сохраненный заказ преобразовать в DTO
                .map(orderMapper::toDto);
    }

    @Override
    public Optional<OrderItemDto> addItem(Integer orderId, OrderItemDto orderItemDto) {

        CoffeeType coffeeType = coffeeTypeService.getById(orderItemDto.getCoffeeTypeId());
        if (coffeeType == null || !coffeeType.isAvailable()) {
            return Optional.empty();
        }

        // Извлечь из БД заказ
        Order order = orderRepository.findById(orderId)
                // Отфильтровываем
                .filter(entity -> entity.getStatus() == OrderStatus.CREATED)
                .orElse(null);

        if (order == null) {
            return Optional.empty();
        }

        // Проебразовать ДТО в сущность
        OrderItem newOrderItem = orderItemMapper.toModel(orderItemDto);

        // Запонить идентификатором заказа поле id в позиции заказа
        newOrderItem.setOrder(order);

        orderItemRepository.save(newOrderItem);

        // Пересчитать сумму заказа
        calculationServiceInternal.processOrder(order);

        // Сохранить заказ (с добавленной позицией)
        orderRepository.save(order);

        // Преобразовать позицию заказа в ДТО
        return Optional.of(orderItemMapper.toDto(newOrderItem));
    }

    @Override
    public boolean deleteItem(Integer orderId, Integer itemId) {
        // Находим позицию заказа по ее идентификатору и идентификатору ее заказа
        Optional<OrderItem> orderItemOptional = orderItemRepository.findFirstByIdAndOrder_Id(itemId, orderId)
                .filter(orderItem -> orderItem.getOrder().getStatus() == OrderStatus.CREATED);
        if (orderItemOptional.isPresent()) {
            // Если нашли позицию, то передаем в переменную orderItem
            OrderItem orderItem = orderItemOptional.get();
            // ... в переменную order передаем ссылку на заказ
            Order order = orderItem.getOrder();
            // удаляем позицию заказа
            orderItemRepository.delete(orderItem);
            order.getItems().remove(orderItem);
            // пересчитываем заказ
            calculationServiceInternal.processOrder(order);
            return true;
        } else {
            // возвращаем false, если не удалось найти позицию заказа
            return false;
        }
    }

    @Override
    public Optional<OrderItemDto> editItem(Integer orderId, Integer itemId, OrderItemDto orderItemDto) {
        // Находим позицию заказа по ее идентификатору и идентификатору ее заказа
        return orderItemRepository.findFirstByIdAndOrder_Id(itemId, orderId)
                .filter(orderItem -> orderItem.getOrder().getStatus() == OrderStatus.CREATED)
                .map(orderItem -> editItemInternal(orderItem, orderItemDto))
                .map(orderItemMapper::toDto);
    }

    @Override
    public Page<OrderDto> listOrders(Pageable pageable) {
        // TODO: 11.05.2022  Что такое лямбда-выражения... Осознать
        return orderRepository.findAll(pageable)
                .map(orderMapper::toDto);
    }

    @Override
    public Optional<OrderDto> getOrder(Integer id) {
        // Достать из репозитория заказ по id
        return orderRepository.findById(id)
                // Заказ преобразовать в DTO
                .map(orderMapper::toDto);
    }

    @Override
    public Page<OrderItemDto> listOrderItems(Integer orderId, Pageable pageable) {
        return orderItemRepository.findAllByOrder_Id(orderId, pageable)
                .map(orderItemMapper::toDto);
    }

    private Order editInternal(Order order, OrderDto orderDto) {
        order.setCustomerName(orderDto.getCustomerName());
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        order.setDeliveryType(deliveryTypeMapper.toModel(orderDto.getDeliveryType()));
        return calculationServiceInternal.processOrder(order);
    }

    private Order confirmInternal(Order order) {
        order.setStatus(OrderStatus.CONFIRMED);
        order.setDateTime(LocalDateTime.now());
        return order;
    }

    private Order cancelInternal(Order order) {
        order.setStatus(OrderStatus.CANCELED);
        return order;
    }

    private OrderItem editItemInternal(OrderItem orderItem, OrderItemDto dto) {
        CoffeeType coffeeType = coffeeTypeService.getById(dto.getCoffeeTypeId());
        if (coffeeType == null || !coffeeType.isAvailable()) {
            return null;
        }
        orderItem.setCoffeeType(coffeeType);
        orderItem.setCount(dto.getCount());
        orderItemRepository.save(orderItem);
        calculationServiceInternal.processOrder(orderItem.getOrder());
        return orderItem;
    }
}
