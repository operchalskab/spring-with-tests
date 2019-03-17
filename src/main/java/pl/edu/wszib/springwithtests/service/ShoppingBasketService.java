package pl.edu.wszib.springwithtests.service;

import pl.edu.wszib.springwithtests.dto.ProductDTO;
import pl.edu.wszib.springwithtests.dto.ShoppingBasketDTO;
import pl.edu.wszib.springwithtests.model.ShoppingBasket;

public interface ShoppingBasketService extends AbstractService<ShoppingBasketDTO, Integer>{

    ShoppingBasketDTO addProduct (Integer shoppingBasketID, ProductDTO productDTO);

}
