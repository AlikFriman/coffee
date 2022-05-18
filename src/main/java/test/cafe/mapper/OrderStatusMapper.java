package test.cafe.mapper;

import org.mapstruct.Mapper;
import test.cafe.dto.type.DeliveryTypeDto;
import test.cafe.dto.type.OrderStatusDto;
import test.cafe.model.type.DeliveryType;
import test.cafe.model.type.OrderStatus;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {

    OrderStatusDto toDto(OrderStatus orderStatus);

    OrderStatus toModel(OrderStatusDto orderStatusDto);
}
