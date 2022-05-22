package test.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.cafe.dto.OrderDto;
import test.cafe.dto.OrderItemDto;
import test.cafe.service.OrderService;

import java.util.List;

// TODO: 17.05.2022 Разобраться, как передаются данные с фронта в контроллеры
//  Аннотации!!!
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // TODO: 19.05.2022 Добавить метод для получения информации о заказе по его id

    /**
     * Получить позицию заказа.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}/get")
    public ResponseEntity<OrderDto> get(@PathVariable Integer id) {

        return orderService.getOrder(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * Получить список заказов.
     *
     * @return Список заказов.
     */
    @GetMapping
    public ResponseEntity<List<OrderDto>> list() {
        List<OrderDto> list = orderService.listOrders();
        return ResponseEntity.ok(list);
    }

    /**
     * Создание нового заказа.
     *
     * @param orderDto
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) {
        OrderDto newOrderDto = orderService.createOrder(orderDto);
        return ResponseEntity.ok(newOrderDto);
    }

    /**
     * Изменение заказа.
     *
     * @param id
     * @param orderDto
     * @return
     */
    @PutMapping("/{id}/edit")
    public ResponseEntity<OrderDto> edit(@PathVariable Integer id, @RequestBody OrderDto orderDto) {
        return orderService.editOder(id, orderDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Подтверждение заказа.
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}/confirm")
    public ResponseEntity<OrderDto> confirm(@PathVariable Integer id) {
        return orderService.confirmOrder(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Отмена заказа.
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDto> cancel(@PathVariable Integer id) {
        return orderService.cancelOrder(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

//// 1 Способ, через if
//        OrderDto newOrderDto = orderService.cancel(id, orderDto);
//        if (newOrderDto == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(newOrderDto);
//        }

//        2 Способ, через тернарный оператор
//        OrderDto newOrderDto = orderService.cancel(id, orderDto);
//        return newOrderDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(newOrderDto);

//        3 Способ,

//        orderService.cancel(id, orderDto)
//                .map(orderDto1 -> ResponseEntity.ok(orderDto1))   // До

//        orderService.cancel(id, orderDto)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());   // После
//        Итог в начале
    }

    /**
     * Добавление позиции заказа.
     *
     * @param orderId
     * @param orderItemDto
     * @return
     */
    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderItemDto> addItem(@PathVariable Integer orderId,
                                                @RequestBody OrderItemDto orderItemDto) {
        return orderService.addItem(orderId, orderItemDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Удаление позиции заказа.
     *
     * @param orderId
     * @return
     */
    @DeleteMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<OrderItemDto> deleteItem(@PathVariable Integer orderId,
                                                   @PathVariable Integer itemId) {
        return orderService.deleteItem(orderId, itemId)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * Изменение позиции заказа.
     *
     * @param orderId
     * @param itemId
     * @param orderItemDto
     * @return
     */
    @PutMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<OrderItemDto> editItem(@PathVariable Integer orderId,
                                                 @PathVariable Integer itemId,
                                                 @RequestBody OrderItemDto orderItemDto) {
        return orderService.editItem(orderId, itemId, orderItemDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
