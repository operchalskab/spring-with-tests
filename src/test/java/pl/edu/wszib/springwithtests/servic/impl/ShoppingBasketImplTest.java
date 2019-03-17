package pl.edu.wszib.springwithtests.servic.impl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.*;
import pl.edu.wszib.springwithtests.NotFoundException;
import pl.edu.wszib.springwithtests.dao.ProductDao;
import pl.edu.wszib.springwithtests.dao.ShoppingBasketDao;
import pl.edu.wszib.springwithtests.dao.ShoppingBasketItemDao;
import pl.edu.wszib.springwithtests.dto.ProductDTO;
import pl.edu.wszib.springwithtests.dto.ShoppingBasketDTO;
import pl.edu.wszib.springwithtests.model.Product;
import pl.edu.wszib.springwithtests.model.ShoppingBasket;
import pl.edu.wszib.springwithtests.model.ShoppingBasketItem;
import pl.edu.wszib.springwithtests.model.Vat;
import pl.edu.wszib.springwithtests.service.impl.ShoppingBasketItemServiceImpl;
import pl.edu.wszib.springwithtests.service.impl.ShoppingBasketServiceImpl;

import java.util.Collections;
import java.util.Optional;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;

@RunWith(JUnit4.class)
public class ShoppingBasketImplTest {

    @InjectMocks
    ShoppingBasketServiceImpl basketService;

    @Mock
    ProductDao productDao;

    @Mock
    ShoppingBasketDao shoppingBasketDao;

    @Mock
    ShoppingBasketItemDao shoppingBasketItemDao;

    @Spy
    Mapper mapper = new DozerBeanMapper();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShoppingBasketIdNotExist(){
        int testShoppingId = 67;

        ProductDTO productDTO = Mockito.mock(ProductDTO.class);

        expectedException.expect(NotFoundException.class);
        basketService.addProduct(testShoppingId, productDTO);
    }

    @Test
    public void testShoppingBasketExistProductNotExist(){
        int testShoppingBasketId = 99;

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(2432);
        productDTO.setName("cos");
        productDTO.setCost(52525d);
        productDTO.setVat(Vat.VALUE_8);

        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.setId(testShoppingBasketId);

        Mockito.when(shoppingBasketDao.findById(testShoppingBasketId))
                .thenReturn(Optional.of(shoppingBasket));
        expectedException.expect(NotFoundException.class);

        basketService.addProduct(testShoppingBasketId,productDTO);
    }

    @Test
    public void testShoppingBasketExistProductExistShoppingBasketItemNotExist(){
        int testShoppingBasketId = 99;

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(2432);
        productDTO.setName("cos");
        productDTO.setCost(52525d);
        productDTO.setVat(Vat.VALUE_8);

        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.setId(testShoppingBasketId);

        Mockito.when(shoppingBasketDao.findById(testShoppingBasketId))
                .thenReturn(Optional.of(shoppingBasket));

        Mockito.when(productDao.existsById(productDTO.getId())).thenReturn(true);

        ShoppingBasketItem item = new ShoppingBasketItem();
        item.setId(545);
        item.setShoppingBasket(shoppingBasket);
        item.setAmount(3);
        item.setProduct(mapper.map(productDTO, Product.class));

        Mockito.when(shoppingBasketItemDao.findByProductIdAndShoppingBasketId(productDTO.getId(), testShoppingBasketId))
                .thenReturn(item);

        Mockito.when(shoppingBasketItemDao.findAllByShoppingBasketId(testShoppingBasketId))
                .thenReturn(Collections.singletonList(item));

        ShoppingBasketDTO result = basketService.addProduct(testShoppingBasketId, productDTO);

        Mockito.verify(shoppingBasketItemDao.save(item));

        Assert.assertEquals(testShoppingBasketId, result.getId().intValue());
        Assert.assertEquals(1, result.getItems().size());
        Assert.assertTrue(result.getItems().stream().anyMatch(i -> i.getProduct().getId().equals(productDTO.getId())));
        Assert.assertTrue(result.getItems()
            .stream().filter(i -> i.getProduct().getId().equals(productDTO.getId())).findFirst()
                .map(i -> i.getAmount() == 4)
                .orElse(false));
    }
}
