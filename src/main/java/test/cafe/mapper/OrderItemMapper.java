package test.cafe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import test.cafe.dto.OrderItemDto;
import test.cafe.model.OrderItem;
import test.cafe.service.CoffeeTypeServiceInternal;

@Mapper(
        componentModel = "spring",
        uses = {CoffeeTypeServiceInternal.class} // TODO Разобраться
)
public interface OrderItemMapper {

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "coffeeTypeId", source = "coffeeType.id")
        // todo Разобраться, как это работает
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sum", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "coffeeType", source = "coffeeTypeId")
    OrderItem toModel(OrderItemDto orderItemDto);
}
