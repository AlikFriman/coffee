package test.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test.cafe.dto.CoffeeTypeDto;
import test.cafe.mapper.CoffeeTypeMapper;
import test.cafe.model.CoffeeType;
import test.cafe.repository.CoffeeTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

import static test.cafe.config.CacheConfig.COFFEE_TYPE_SERVICE_GET;
import static test.cafe.config.CacheConfig.COFFEE_TYPE_SERVICE_LIST;

@Service
@AllArgsConstructor // TODO: 11.05.2022 Понять и осознать эту магию
public class CoffeeTypeServiceImpl implements CoffeeTypeService, CoffeeTypeServiceInternal {

    private final CoffeeTypeRepository coffeeTypeRepository;
    private final CoffeeTypeMapper coffeeTypeMapper;

    @Override
    @Cacheable(COFFEE_TYPE_SERVICE_LIST)
    public Page<CoffeeTypeDto> list(Pageable pageable) {
        // TODO: 11.05.2022  Что такое лямбда-выражения... Осознать
        // Реализация кэширования.
        return coffeeTypeRepository.findAll(pageable)
                .map(coffeeTypeMapper::toDto);
    }

    @Override
    @Cacheable(COFFEE_TYPE_SERVICE_GET)
    public CoffeeType getById(Integer id) {
        return coffeeTypeRepository.findById(id).orElse(null);
    }
}
