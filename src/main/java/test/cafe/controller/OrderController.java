package test.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.cafe.dto.OrderDto;
import test.cafe.dto.OrderItemDto;
import test.cafe.service.OrderService;

import javax.websocket.server.PathParam;
import java.util.List;

// TODO: 17.05.2022 Разобраться, как передаются данные с фронта в контроллеры
//  Аннотации!!!
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Получить список заказов.
     *
     * @return Список заказов.
     */
    @GetMapping
    public ResponseEntity<List<OrderDto>> list() {
        List<OrderDto> list = orderService.list();
        return ResponseEntity.ok(list);
    }

    /**
     * Создание нового заказа
     *
     * @param orderDto
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) {
        OrderDto newOrderDto = orderService.create(orderDto);
        return ResponseEntity.ok(newOrderDto);
    }

    /**
     * Изменение заказа
     * @param id
     * @param orderDto
     * @return
     */
    @PutMapping("/{id}/edit")
    public ResponseEntity<OrderDto> edit(@PathVariable Integer id, @RequestBody OrderDto orderDto) {
        return orderService.edit(id, orderDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

//        if (newOrderDto == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(newOrderDto);
//        }
    }

    /**
     * Подтверждение заказа.
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}/confirm")
    public ResponseEntity<OrderDto> confirm(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Отмена заказа.
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDto> cancel(@PathVariable Integer id) {
        // todo: Вызвать соответствующий метод сервиса
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
    }
}
