package test.cafe.mapper;

import org.mapstruct.Mapper;
import test.cafe.dto.CoffeeTypeDto;
import test.cafe.model.CoffeeType;

@Mapper(componentModel = "spring")
public interface CoffeeTypeMapper {

    CoffeeTypeDto toDto(CoffeeType coffeeType);
}
