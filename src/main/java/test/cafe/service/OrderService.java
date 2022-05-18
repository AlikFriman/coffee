package test.cafe.service;

import test.cafe.dto.OrderDto;
import test.cafe.dto.OrderItemDto;

import java.util.List;
import java.util.Optional;


/**
 *
 */
public interface OrderService {

    /**
     * Создание заказа.
     * @param orderDto
     * @return
     */
    OrderDto create(OrderDto orderDto);

    /**
     *
     * @param id
     * @param orderDto
     * @return
     */
    Optional<OrderDto> edit(Integer id, OrderDto orderDto);

    /**
     *
     * @param id
     * @return
     */
    OrderDto confirm(Integer id);

    /**
     *
     * @param id
     * @return
     */
    Optional<OrderDto> cancel(Integer id,OrderDto orderDto);

    /**
     *
     * @param orderId
     * @param orderItemDto
     * @return
     */
    OrderItemDto addItem(Integer orderId, OrderItemDto orderItemDto);

    /**
     *
     * @param orderId
     * @param itemId
     * @return
     */
    OrderItemDto deleteItem(Integer orderId, Integer itemId);

    /**
     *
     * @param orderId
     * @param itemId
     * @param orderItemDto
     * @return
     */
    OrderItemDto editItem(Integer orderId, Integer itemId, OrderItemDto orderItemDto);

    /**
     *
     * @return
     */
    List<OrderDto> list();
}
