package test.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.cafe.dto.OrderDto;
import test.cafe.dto.OrderItemDto;
import test.cafe.mapper.DeliveryTypeMapper;
import test.cafe.mapper.OrderMapper;
import test.cafe.model.Order;
import test.cafe.model.type.OrderStatus;
import test.cafe.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional // todo Разобраться с транзакциями в СУБД и Spring
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    // TODO: 17.05.2022 Spring IoC, Spring Context
    private final OrderMapper orderMapper;
    private final DeliveryTypeMapper deliveryTypeMapper;
    private final OrderRepository orderRepository;
    private final CalculationService calculationService;

    // TODO: 18.05.2022 Реализовать проверки статусов заказов перед совершением операций

    @Override
    public OrderDto create(OrderDto orderDto) {
        Order newOrder = orderMapper.toModel(orderDto);

        newOrder.setStatus(OrderStatus.CREATED);
        newOrder.setSum(BigDecimal.ZERO);

        Order savedOrder = orderRepository.save(newOrder);

        return orderMapper.toDto(savedOrder);
    }

    @Override
    public Optional<OrderDto> edit(Integer id, OrderDto orderDto) {
        // TODO: 17.05.2022 Изучить Optional

        // Достать из репозитория заказ по id
        return orderRepository.findById(id)
        // Изменить соответствующие поля
                .map(order -> editInternal(order, orderDto))
                // Сохранить измененный (заказ в репозиторий
                .map(orderRepository::save)
                // Сохраненный заказ преобразовать в DTO
                .map(orderMapper::toDto);
    }

    @Override
    public Optional<OrderDto> confirm(Integer id) {
        return orderRepository.findById(id)
                // Поменять статус на CONFIRMED
                .map(this::confirmInternal)
                // Сохранить подтвержденный заказ в репозиторий
                .map(orderRepository::save)
                // Сохраненный заказ преобразовать в DTO
                .map(orderMapper::toDto);
    }

    @Override
    public Optional<OrderDto> cancel(Integer id) {
        // Достать из репозитория заказ по id
        return orderRepository.findById(id)
                // Поменять статус на CANCELED
                .map(this::cancelInternal)
                // Сохранить измененный (отмененный) заказ в репозиторий
                .map(orderRepository::save)
                // Сохраненный заказ преобразовать в DTO
                .map(orderMapper::toDto);
    }

    @Override
    public Optional<OrderItemDto> addItem(Integer orderId, OrderItemDto orderItemDto) {
        // todo Извлечь из БД заказ
        // todo Проебразовать ДТО в сущность
        // todo Добавить созданную позицию в список позиций заказа
        // todo Запонить идентификатором заказа поле id в позиции заказа
        // todo Пересчитать сумму заказа
        return null;
    }

    @Override
    public Optional<OrderItemDto> deleteItem(Integer orderId, Integer itemId) {
        return null;
    }

    @Override
    public Optional<OrderItemDto> editItem(Integer orderId, Integer itemId, OrderItemDto orderItemDto) {
        return null;
    }

    @Override
    public List<OrderDto> list() {
        // TODO: 11.05.2022  Что такое лямбда-выражения... Осознать
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    private Order editInternal(Order order, OrderDto orderDto) {
        order.setCustomerName(orderDto.getCustomerName());
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        order.setDeliveryType(deliveryTypeMapper.toModel(orderDto.getDeliveryType()));
        return calculationService.processOrder(order);
    }

    private Order confirmInternal(Order order) {
        order.setStatus(OrderStatus.CONFIRMED);
        return order;
    }

    private Order cancelInternal(Order order) {
        order.setStatus(OrderStatus.CANCELED);
        return order;
    }
}
