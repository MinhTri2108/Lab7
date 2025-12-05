package com.example.product_management.controller;

import com.example.product_management.entity.Product;
import com.example.product_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ProductService productService;

    @Autowired
    public DashboardController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showDashboard(Model model) {
        long totalProducts = productService.getTotalProducts();
        BigDecimal totalValue = productService.getTotalInventoryValue();
        BigDecimal avgPrice = productService.getAveragePrice();
        List<Product> lowStock = productService.getLowStockProducts(10); 
        List<Product> recentProducts = productService.getRecentProducts();
        List<Object[]> categoryStats = productService.getCategoryStatistics();
        List<String> catLabels = categoryStats.stream()
                .map(obj -> (String) obj[0]).collect(Collectors.toList());
        List<Long> catCounts = categoryStats.stream()
                .map(obj -> (Long) obj[1]).collect(Collectors.toList());

        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalValue", totalValue);
        model.addAttribute("avgPrice", avgPrice);
        model.addAttribute("lowStock", lowStock);
        model.addAttribute("recentProducts", recentProducts);
        
        model.addAttribute("catLabels", catLabels);
        model.addAttribute("catCounts", catCounts);

        return "dashboard";
    }
}