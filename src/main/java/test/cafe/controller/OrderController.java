package test.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.cafe.dto.OrderDto;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    /**
     * Создание нового заказа
     * @param orderDto
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok().build();
    }

    /**
     * Изменение заказа
     * @param id
     * @param orderDto
     * @return
     */
    @PutMapping("/{id}/edit")
    public ResponseEntity<OrderDto> edit(@PathParam("id") Integer id, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok().build();
    }

    /**
     * Подтверждение заказа
     * @param id
     * @return
     */
    @PutMapping("/{id}/confirm")
    public ResponseEntity<OrderDto> confirm(@PathParam("id") Integer id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Отмена заказа
     * @param id
     * @return
     */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDto> cancel(@PathParam("id") Integer id) {
        return ResponseEntity.ok().build();
    }
    // TODO: 13.05.2022 Добавление позиции заказа

    // TODO: 13.05.2022 Удаление позиции заказа

    // TODO: 13.05.2022 Изменение позиции заказа


}
