package test.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.cafe.dto.CoffeeTypeDto;
import test.cafe.service.CoffeeTypeService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/coffee-types")
@AllArgsConstructor
public class CoffeeTypeController {

    private final CoffeeTypeService coffeeTypeService;

    /**
     * Получить все сорта кофе.
     *
     * @return Список сортов кофе.
     */
    @GetMapping("list")
    public ResponseEntity<List<CoffeeTypeDto>> list() {
        List<CoffeeTypeDto> list = coffeeTypeService.list();
        return ResponseEntity.ok(list);
    }

}
