package pl.edu.wszib.springwithtests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.springwithtests.dto.ProductDTO;
import pl.edu.wszib.springwithtests.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<ProductDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO findOne(@PathVariable int id) {
        return service.find(id);
    }

    @PostMapping
    public ProductDTO add(@RequestBody ProductDTO dto) {
        return service.add(dto);
    }

    @PutMapping
    public ProductDTO update(@RequestBody ProductDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        service.remove(id);
    }

}
