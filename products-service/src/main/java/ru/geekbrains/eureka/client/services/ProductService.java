package ru.geekbrains.eureka.client.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.common.eureka.dtos.ProductDto;
import ru.geekbrains.eureka.client.models.Category;
import ru.geekbrains.eureka.client.models.Product;
import ru.geekbrains.eureka.client.repository.ProductRepository;
import ru.geekbrains.april.market.error_handing.*;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements Serializable {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findPage(int page, int pageSize) {
        return productRepository.findAllBy(PageRequest.of(page, pageSize));
    }


//    public static final Function<Product, ProductSoap> functionEntityToSoap = se -> {
//        ProductSoap productSoap = new ProductSoap();
//        productSoap.setId(se.getId());
//        productSoap.setTitle(se.getTitle());
//        productSoap.setPrice(se.getPrice());
//        productSoap.setCategoryTitle(se.getCategory().getTitle());
//        productSoap.setCreatedAt(se.getCreatedAt().toString());
//        productSoap.setUpdatedAt(se.getUpdatedAt().toString());
//        return productSoap;
//    };

//    public List<ProductSoap> getAllProducts(){return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());}

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public ProductDto createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category doesn't exists product.categoryTitle = " + productDto.getCategoryTitle() + " (Product creation)"));
        product.setCategory(category);
        productRepository.save(product);
        return createProductDto(product);
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + productDto.getId() + " (for update)"));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category doesn't exists product.categoryTitle = " + productDto.getCategoryTitle() + " (Product creation)"));
        product.setCategory(category);
        return createProductDto(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDto createProductDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getTitle());
    }
}
