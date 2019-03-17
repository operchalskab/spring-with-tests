package pl.edu.wszib.springwithtests.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.springwithtests.dao.ProductDao;
import pl.edu.wszib.springwithtests.dao.ShoppingBasketItemDao;
import pl.edu.wszib.springwithtests.dto.ProductDTO;
import pl.edu.wszib.springwithtests.dto.ShoppingBasketDTO;
import pl.edu.wszib.springwithtests.dto.ShoppingBasketItemDTO;
import pl.edu.wszib.springwithtests.model.Product;
import pl.edu.wszib.springwithtests.model.ShoppingBasketItem;
import pl.edu.wszib.springwithtests.service.ProductService;
import pl.edu.wszib.springwithtests.service.ShoppingBasketItemService;
import pl.edu.wszib.springwithtests.service.ShoppingBasketService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingBasketItemServiceImpl implements ShoppingBasketItemService {

    @Autowired
    private ShoppingBasketItemDao dao;

    @Autowired
    private Mapper mapper;

    @Override
    public ShoppingBasketItemDTO add(ShoppingBasketItemDTO shoppingBasketItemDTO) {
        return mapper.map(
                dao.save(
                        mapper.map(shoppingBasketItemDTO, ShoppingBasketItem.class)
                ), ShoppingBasketItemDTO.class);
    }

    @Override
    public ShoppingBasketItemDTO update(ShoppingBasketItemDTO shoppingBasketItemDTO) {
        return mapper.map(
                dao.save(
                        mapper.map(shoppingBasketItemDTO, ShoppingBasketItem.class)
                ), ShoppingBasketItemDTO.class);
    }

    @Override
    public void remove(Integer integer) {
        dao.deleteById(integer);
    }

    @Override
    public ShoppingBasketItemDTO find(Integer integer) {
        return dao.findById(integer)
                .map(p -> mapper.map(p, ShoppingBasketItemDTO.class))
                .orElse(null);
    }

    @Override
    public List<ShoppingBasketItemDTO> findAll() {
        return  dao.findAll().stream()
                .map(p -> mapper.map(p, ShoppingBasketItemDTO.class))
                .collect(Collectors.toList());
    }
}
