package test.cafe.mapper;

import org.mapstruct.Mapper;
import test.cafe.dto.OrderItemDto;
import test.cafe.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemDto toDto(OrderItem orderItem);
}
