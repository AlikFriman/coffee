package test.cafe.mapper;

import org.mapstruct.Mapper;
import test.cafe.dto.type.DeliveryTypeDto;
import test.cafe.model.type.DeliveryType;

@Mapper(componentModel = "spring")
public interface DeliveryTypeMapper {

    DeliveryTypeDto toDto(DeliveryType deliveryType);

    DeliveryType toModel(DeliveryTypeDto deliveryTypeDto);
}
