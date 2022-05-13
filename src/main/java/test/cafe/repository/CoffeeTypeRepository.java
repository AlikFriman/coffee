package test.cafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.cafe.model.CoffeeType;

import java.util.List;

@Repository
public interface CoffeeTypeRepository extends CrudRepository<CoffeeType, Integer> {

    @Override
    List<CoffeeType> findAll();
}
