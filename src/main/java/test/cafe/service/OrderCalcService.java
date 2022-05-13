package test.cafe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.cafe.model.CoffeeType;
import test.cafe.model.OrderItem;
import test.cafe.repository.CoffeeTypeRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderCalcService {
    @Value("${coffee.delivery}")
    private Integer delivery;
    @Value("${coffee.free.cup}")
    private Integer freeCup;
    @Value("${coffee.free.summ.delivery}")
    private Integer summDelivery;

    private CoffeeTypeRepository coffeeTypeRepository;

// Fetch

    /**
     * Сумма заказа за каждый сорт кофе с учетом скидки по количеству кружек
     */
    // TODO: 12.05.2022 В сервис нужно передавать идентификатор заказа, по которому нужно сделать расчет
    //  Заказ вытащить из БД.
//    public Integer calc(List<OrderItem> orderItems) {
//        Integer fullPrice = 0;
//        List<CoffeeType> types = coffeeTypeRepository.findAll();
//        HashMap<Integer, BigDecimal> idPrice = idPrice(types);
//
//        for (OrderItemDto orderItemDto : orderItemsDtos) {
//            Integer possiblePrice = (orderItemDto.getCupCounter() - orderItemDto.getCupCounter() / freeCup) * idPrice.get(orderItemDto.getCoffeeType().getId());
//            fullPrice += possiblePrice;
//
//            Integer freeCups = orderItemDto.getCupCounter() / freeCup;
//            Integer totalCups = orderItemDto.getCupCounter() - freeCups;
//            BigDecimal price = idPrice
//            BigDecimal sum = BigDecimal.valueOf(totalCups).multiply()
//        }
//        if (fullPrice < summDelivery) {
//            fullPrice = fullPrice + delivery;
//        }
//
//        int a = 1;
//        Integer b = 2;
//
//        BigDecimal d = BigDecimal.valueOf(a).add(BigDecimal.valueOf(b));
//
//        Integer c = a + b;
//
//
//        return fullPrice;
//    }

    /**
     * Таблица цен по сортам кофе
     *
     * @param types
     */
    private HashMap<Integer, BigDecimal> idPrice(List<CoffeeType> types) {
        HashMap<Integer, BigDecimal> idPrice = new HashMap<>();
        for (CoffeeType coffeeType : types) {
            idPrice.put(coffeeType.getId(), coffeeType.getPrice());
        }
        return idPrice;
    }
}
