package test.cafe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import test.cafe.dto.OrderItemDto;
import test.cafe.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "orderId", source = "order.id") // todo Разобраться, как это работает
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sum", ignore = true)
    OrderItemDto toDto(OrderItem orderItem);
}
