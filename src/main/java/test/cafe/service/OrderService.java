package test.cafe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import test.cafe.dto.OrderDto;
import test.cafe.dto.OrderItemDto;

import java.util.Optional;


/**
 *
 */
public interface OrderService {

    /**
     * Создание заказа.
     * @param orderDto DTO с данными для создания нового заказа
     * @return HTTP-ответ с DTO созданного заказа
     */
    OrderDto createOrder(OrderDto orderDto);

    /**
     * Изменение заказа.
     * @param id Идентификатор заказа.
     * @param orderDto DTO с данными изменяемого заказа.
     * @return HTTP-ответ с DTO измененного заказа.
     */
    Optional<OrderDto> editOder(Integer id, OrderDto orderDto);

    /**
     * Подтверждение заказа.
     * @param id Идентификатор заказа.
     * @return HTTP-ответ о подтверждении заказа.
     */
    Optional<OrderDto> confirmOrder(Integer id);

    /**
     * Отмена заказа.
     * @param id Идентификатор заказа.
     * @return HTTP-ответ об отмене заказа.
     */
    Optional<OrderDto> cancelOrder(Integer id);

    /**
     * Добавление позиции заказа.
     * @param orderId Идентификатор заказа.
     * @param orderItemDto DTO с данными позиций заказа.
     * @return HTTP-ответ с DTO добавленной позицией заказа.
     */
    Optional<OrderItemDto> addItem(Integer orderId, OrderItemDto orderItemDto);

    /**
     * Удаление позиции заказа.
     * @param orderId Идентификатор заказа.
     * @param itemId Идентификатор позиции заказа.
     * @return Пустой элемент.
     */
   boolean deleteItem(Integer orderId, Integer itemId);

    /**
     * Изменение позиции заказа.
     * @param orderId Идентификатор заказа.
     * @param itemId Идентификатор позиции заказа.
     * @param orderItemDto DTO с данными позиций заказа.
     * @return HTTP-ответ с DTO измененной позицией заказа.
     */
    Optional<OrderItemDto> editItem(Integer orderId, Integer itemId, OrderItemDto orderItemDto);

    /**
     * Получение списка заказов.
     * @param pageable Страница списка заказов.
     * @return Страница заказов.
     */
    Page<OrderDto> listOrders(Pageable pageable);

    /**
     * Получение заказа.
     * @return Optional-объект DTO заказа.
     */
    Optional<OrderDto> getOrder(Integer id);

    /**
     * Получение позиций заказа по идентификатору заказа
     *
     * @param orderId Идентификатор заказа.
     * @param pageable Страница списка позиций заказов.
     * @return Страница позиций заказа.
     */
    Page<OrderItemDto> listOrderItems(Integer orderId, Pageable pageable);

    /**
     * Получение позиции заказа.
     *
     * @param orderId идентификатор заказа
     * @param itemId  идентификатор позиции заказа
     * @return Optional-объект DTO позиции заказа
     */
    Optional<OrderItemDto> getItem(Integer orderId, Integer itemId);
}
