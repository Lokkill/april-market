package ru.geekbrains.april.market.data_mapping;

import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    private final Connection connection;

    public ProductMapper(Connection connection) {
        this.connection = connection;
    }

    public Product findById(Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select id, title, price, category_id, created_at, updated_at from products where id = ?");
        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                Product product = IdentityMap.getProduct(resultSet.getLong(1));
                if (product != null){
                    return product;
                } else {
                    product = new Product();
                }
                product.setId(resultSet.getLong(1));
                product.setTitle(resultSet.getString(2));
                product.setPrice(resultSet.getBigDecimal(3));
                IdentityMap.addProduct(product);
                //product.setCreatedAt();
                //product.setUpdatedAt();
                return product;
            }
        }
        throw new ResourceNotFoundException("Product not found");
    }

    public List<Product> findAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select id, title, price, category_id, created_at, updated_at from products");
        try (ResultSet resultSet = statement.executeQuery()){
            List<Product> products = new ArrayList<>();
            while (resultSet.next()){
                Product product = IdentityMap.getProduct(resultSet.getLong(1));
                if (product != null){
                    products.add(product);
                } else {
                    product = new Product();
                }
                product.setId(resultSet.getLong(1));
                product.setTitle(resultSet.getString(2));
                product.setPrice(resultSet.getBigDecimal(3));
                //product.setCreatedAt();
                //product.setUpdatedAt();
                products.add(product);
            }
            return products;
        }
    }
}
