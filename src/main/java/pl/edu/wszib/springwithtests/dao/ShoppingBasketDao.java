package pl.edu.wszib.springwithtests.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.springwithtests.model.ShoppingBasket;

import java.util.List;

@Repository
public interface ShoppingBasketDao extends CrudRepository<ShoppingBasket, Integer> {

    List<ShoppingBasket> findAll();


}
