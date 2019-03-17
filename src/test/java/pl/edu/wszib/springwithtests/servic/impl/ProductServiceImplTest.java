package pl.edu.wszib.springwithtests.servic.impl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import pl.edu.wszib.springwithtests.dao.ProductDao;
import pl.edu.wszib.springwithtests.dto.ProductDTO;
import pl.edu.wszib.springwithtests.model.Product;
import pl.edu.wszib.springwithtests.model.Vat;
import pl.edu.wszib.springwithtests.service.ProductService;
import pl.edu.wszib.springwithtests.service.impl.ProductServiceImpl;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductDao mockDao;

    @Spy
    Mapper mapper = new DozerBeanMapper();

    @Before
    public void setUp(){
      //  productService = new ProductServiceImpl();
       // mockDao = Mockito.mock(ProductDao.class);
        //mapper = new DozerBeanMapper();
        initMocks(this);
    }

    @Test
    public void testAdd(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("test product");
        productDTO.setCost(10d);
        productDTO.setVat(Vat.VALUE_23);

        Product product = Mockito.mock(Product.class);
        Mockito.when(mapper.map(productDTO, Product.class)).thenReturn(product);
        Mockito.when(mockDao.save(product)).thenReturn(product);


        productService.add(productDTO);

        Mockito.verify(mockDao).save(product);

    }

    @Test
    public void testremove(){
        int testid = 1;
       productService.remove(testid);
       Mockito.verify(mockDao).deleteById(testid);

    }



//    @Test
//    public void test(){
//        productService.add(null);
//    }

}
