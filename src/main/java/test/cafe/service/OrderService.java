package test.cafe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    OrderDto createOrder(OrderDto orderDto);

    /**
     * Изменение заказа.
     * @param id
     * @param orderDto
     * @return
     */
    Optional<OrderDto> editOder(Integer id, OrderDto orderDto);

    /**
     * Подтверждение заказа.
     * @param id
     * @return
     */
    Optional<OrderDto> confirmOrder(Integer id);

    /**
     * Отмена заказа.
     * @param id
     * @return
     */
    Optional<OrderDto> cancelOrder(Integer id);

    /**
     * Добавление позиции заказа.
     * @param orderId
     * @param orderItemDto
     * @return
     */
    Optional<OrderItemDto> addItem(Integer orderId, OrderItemDto orderItemDto);

    /**
     * Удаление позиции заказа.
     * @param orderId
     * @param itemId
     * @return
     */
   boolean deleteItem(Integer orderId, Integer itemId);

    /**
     * Изменение позиции заказа.
     * @param orderId
     * @param itemId
     * @param orderItemDto
     * @return
     */
    Optional<OrderItemDto> editItem(Integer orderId, Integer itemId, OrderItemDto orderItemDto);

    /**
     * Получение списка заказов.
     * @return
     * @param pageable
     */
    Page<OrderDto> listOrders(Pageable pageable);

    /**
     * Получение заказа.
     * @return
     */
    Optional<OrderDto> getOrder(Integer id);
}
