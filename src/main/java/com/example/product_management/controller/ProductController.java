package com.example.product_management.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.product_management.entity.Product;
import com.example.product_management.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    //List all products
    @GetMapping
    public String listProducts(@RequestParam(required = false) String name, 
                               @RequestParam(required = false) String category, 
                               @RequestParam(required = false) BigDecimal minPrice, 
                               @RequestParam(required = false) BigDecimal maxPrice, 
                               @RequestParam(required = false, defaultValue = "id") String sortBy, 
                               @RequestParam(required = false, defaultValue = "asc") String sortDir, 
                               Model model) {
        
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        
        List<Product> products = productService.searchProducts(name, category, minPrice, maxPrice, sort);
        
        model.addAttribute("products", products);
        
        model.addAttribute("searchName", name);
        model.addAttribute("searchCategory", category);
        model.addAttribute("searchMinPrice", minPrice);
        model.addAttribute("searchMaxPrice", maxPrice);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        
        return "product-list";
    }
    
    //Show form for new product 
    @GetMapping("/new")
    public String showNewForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-form";
    }
    
    //Show form for editing product 
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return productService.getProductById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    return "product-form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Product not found");
                    return "redirect:/products";
                });
    }
    
    //Save product
    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "product-form";
        }
        
        try {
            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("message", "Product saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
            return "product-form";
        }
        
        return "redirect:/products";
    }
    
    //Delete product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("message", "Product deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting product: " + e.getMessage());
        }
        return "redirect:/products";
    }
    
    //Search products
    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, 
                                 @RequestParam(defaultValue = "0") int page, 
                                 @RequestParam(defaultValue = "5") int size, 
                                 Model model) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.searchProducts(keyword, pageable);
        
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        
        return "product-list";
    }

    //Advanced search 
    @GetMapping("/advanced-search")
    public String advancedSearch(@RequestParam(required = false) String name, 
                                 @RequestParam(required = false) String category, 
                                 @RequestParam(required = false) BigDecimal minPrice, 
                                 @RequestParam(required = false) BigDecimal maxPrice, 
                                 Model model, 
                                 Sort sort) { 
        List<Product> products = productService.searchProducts(name, category, minPrice, maxPrice, sort);
        model.addAttribute("products", products);
        model.addAttribute("searchName", name);
        model.addAttribute("searchCategory", category);
        model.addAttribute("searchMinPrice", minPrice);
        model.addAttribute("searchMaxPrice", maxPrice);
        
        return "product-list";
    }
}