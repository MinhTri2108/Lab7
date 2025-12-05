package com.example.product_management.service;

import com.example.product_management.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

public interface ProductService {
    
    long getCountByCategory(String category);

    long getTotalProducts();
    List<Object[]> getCategoryStatistics();

    BigDecimal getTotalInventoryValue();

    BigDecimal getAveragePrice();

    List<Product> getLowStockProducts(int threshold);

    List<Product> getRecentProducts();
    
    List<Product> getAllProducts();
    
    Optional<Product> getProductById(Long id);
    
    Product saveProduct(Product product);
    
    void deleteProduct(Long id);

    List<Product> getAllProducts(Sort sort);
    
    List<Product> searchProducts(String keyword);
    
    List<Product> getProductsByCategory(String category);

    List<Product> searchProducts(String name, String category, BigDecimal minPrice, BigDecimal maxPrice, Sort sort);

    Page<Product> searchProducts(String keyword, Pageable pageable);

    List<String> getAllCategories();

    BigDecimal getAverageProductPrice();
    
}
