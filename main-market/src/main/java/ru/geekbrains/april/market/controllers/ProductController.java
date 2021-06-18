package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.common.eureka.dtos.ProductDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final String URL_PRODUCTS_SERVICE = "http://products-service:19872";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") int page) {
        Page<ProductDto> productsPage = restTemplate.getForObject(URL_PRODUCTS_SERVICE + "/", Page.class);
        return productsPage;
//        Page<Product> productsPage = productService.findPage(page - 1, 10);
//        Page<ProductDto> dtoPage = new PageImpl<>(productsPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), productsPage.getPageable(), productsPage.getTotalElements());
//        return dtoPage;
    }

    @GetMapping("/{id}")
    public ProductDto getOneProductById(@PathVariable Long id) {
        return restTemplate.getForObject(URL_PRODUCTS_SERVICE + "/" +  id, ProductDto.class);
    }
//
    @PostMapping
    public ProductDto createNewProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
//        }
        return restTemplate.postForObject(URL_PRODUCTS_SERVICE + "/", productDto, ProductDto.class);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto) {
        restTemplate.put(URL_PRODUCTS_SERVICE + "/", productDto);
//        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        restTemplate.delete(URL_PRODUCTS_SERVICE + "/" + id);
//        productService.deleteById(id);
    }
}
