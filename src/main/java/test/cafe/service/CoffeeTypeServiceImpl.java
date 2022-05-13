package test.cafe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import test.cafe.dto.CoffeeTypeDto;
import test.cafe.mapper.CoffeeTypeMapper;
import test.cafe.repository.CoffeeTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor // TODO: 11.05.2022 Понять и осознать эту магию
public class CoffeeTypeServiceImpl implements CoffeeTypeService {

    private final CoffeeTypeRepository coffeeTypeRepository;
    private final CoffeeTypeMapper coffeeTypeMapper;

    @Override
    public List<CoffeeTypeDto> list() {
        // TODO: 11.05.2022  Что такое лямбда-выражения... Осознать
        return coffeeTypeRepository.findAll().stream()
                .map(coffeeTypeMapper::toDto)
                .collect(Collectors.toList());
    }
}
