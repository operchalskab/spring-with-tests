package pl.edu.wszib.springwithtests.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.springwithtests.dao.ShoppingBasketDao;
import pl.edu.wszib.springwithtests.dao.ShoppingBasketItemDao;
import pl.edu.wszib.springwithtests.dto.ShoppingBasketDTO;
import pl.edu.wszib.springwithtests.dto.ShoppingBasketItemDTO;
import pl.edu.wszib.springwithtests.model.ShoppingBasket;
import pl.edu.wszib.springwithtests.model.ShoppingBasketItem;
import pl.edu.wszib.springwithtests.service.ShoppingBasketService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingBasketServiceImpl implements ShoppingBasketService {


    @Autowired
    private ShoppingBasketDao dao;

    @Autowired
    private Mapper mapper;

    @Override
    public ShoppingBasketDTO add(ShoppingBasketDTO shoppingBasketItemDTO) {
        return mapper.map(
                dao.save(
                        mapper.map(shoppingBasketItemDTO, ShoppingBasket.class)
                ), ShoppingBasketDTO.class);
    }

    @Override
    public ShoppingBasketDTO update(ShoppingBasketDTO shoppingBasketItemDTO) {
        return mapper.map(
                dao.save(
                        mapper.map(shoppingBasketItemDTO, ShoppingBasket.class)
                ), ShoppingBasketDTO.class);
    }

    @Override
    public void remove(Integer integer) {
        dao.deleteById(integer);
    }

    @Override
    public ShoppingBasketDTO find(Integer integer) {
        return dao.findById(integer)
                .map(p -> mapper.map(p, ShoppingBasketDTO.class))
                .orElse(null);
    }

    @Override
    public List<ShoppingBasketDTO> findAll() {
        return  dao.findAll().stream()
                .map(p -> mapper.map(p, ShoppingBasketDTO.class))
                .collect(Collectors.toList());
    }
}
