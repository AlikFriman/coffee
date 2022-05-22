package test.cafe.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import test.cafe.dto.OrderDto;
import test.cafe.model.Order;

@Mapper(
        componentModel = "spring",
        uses = {OrderItemMapper.class}
)
public interface OrderMapper {

    OrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "sum", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    Order toModel(OrderDto orderDto);
}
