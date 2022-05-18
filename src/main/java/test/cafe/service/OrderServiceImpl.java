package test.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.cafe.dto.OrderDto;
import test.cafe.dto.OrderItemDto;
import test.cafe.mapper.DeliveryTypeMapper;
import test.cafe.mapper.OrderMapper;
import test.cafe.mapper.OrderStatusMapper;
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
    private final OrderStatusMapper orderStatusMapper;
    private final OrderRepository orderRepository;
    private final CalculationService calculationService;

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
    public OrderDto confirm(Integer id) {
        return null;
    }

    @Override
    public Optional<OrderDto> cancel(Integer id, OrderDto orderDto) {

        // todo: Достать из репозитория заказ по id
        return orderRepository.findById(id)
                // todo: Поменять статус на Canceled
                .map(order -> cancelInternal(order, orderDto))
                // todo: Сохранить измененный (отмененный) заказ в репозиторий
                .map(orderRepository::save)
                // todo: Сохраненный заказ преобразовать в DTO
                .map(orderMapper::toDto);
//                .orElse(null);
    }

    @Override
    public OrderItemDto addItem(Integer orderId, OrderItemDto orderItemDto) {
        return null;
    }

    @Override
    public OrderItemDto deleteItem(Integer orderId, Integer itemId) {
        return null;
    }

    @Override
    public OrderItemDto editItem(Integer orderId, Integer itemId, OrderItemDto orderItemDto) {
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

    private Order cancelInternal(Order order, OrderDto orderDto) {
        order.setStatus(orderStatusMapper.toModel(orderDto.getStatus()));
        return null;
    }
}
