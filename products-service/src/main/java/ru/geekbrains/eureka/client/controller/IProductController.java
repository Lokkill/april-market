package ru.geekbrains.eureka.client.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.common.eureka.dtos.ProductDto;

@FeignClient("products-eureka-client")
public interface IProductController {
    @GetMapping
    Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") int page);

    @GetMapping("/{id}")
    ProductDto getOneProductById(@PathVariable Long id);

    @PostMapping
    ProductDto createNewProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult);

    @PutMapping
    ProductDto updateProduct(@RequestBody ProductDto productDto);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id);
}
