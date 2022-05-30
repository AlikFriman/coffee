package test.cafe.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.cafe.dto.CoffeeTypeDto;
import test.cafe.service.CoffeeTypeService;

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
    // TODO: 25.05.2022 Переделать на Pageable
    @PostMapping("list")
    public ResponseEntity<Page<CoffeeTypeDto>> list(Pageable pageable) {
        Page<CoffeeTypeDto> page = coffeeTypeService.list(pageable);
        return ResponseEntity.ok(page);
    }

}
