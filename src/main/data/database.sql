CREATE DATABASE IF NOT EXISTS product_management;
USE product_management;

-- Drop table if exists
DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    category VARCHAR(50) NOT NULL,
    description TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO products (product_code, name, price, quantity, category, description, created_at) VALUES 
('P001', 'iPhone 15 Pro', 999.99, 50, 'Electronics', 'Latest Apple smartphone with titanium finish', NOW()),
('P002', 'Samsung Galaxy S24', 899.99, 45, 'Electronics', 'AI-powered Android flagship', NOW()),
('P003', 'MacBook Air M2', 1199.00, 20, 'Electronics', 'Lightweight laptop with powerful M2 chip', NOW()),
('P004', 'Sony WH-1000XM5', 348.00, 15, 'Electronics', 'Noise cancelling wireless headphones', NOW()),
('P005', 'Levi''s 501 Jeans', 69.50, 100, 'Clothing', 'Classic straight leg jeans', NOW()),
('P006', 'Nike Air Max 90', 120.00, 8, 'Footwear', 'Retro style running shoes', NOW()),
('P007', 'Adidas Ultraboost', 180.00, 12, 'Footwear', 'High performance running shoes', NOW()),
('P008', 'Cotton T-Shirt', 15.99, 200, 'Clothing', 'Basic white cotton t-shirt', NOW()),
('P009', 'Leather Wallet', 45.00, 30, 'Accessories', 'Genuine leather bi-fold wallet', NOW()),
('P010', 'Gaming Mouse', 59.99, 5, 'Electronics', 'RGB gaming mouse with high DPI', NOW()),
('P011', 'Mechanical Keyboard', 129.99, 4, 'Electronics', 'Tactile switches with RGB backlight', NOW()),
('P012', '4K Monitor', 299.99, 7, 'Electronics', '27-inch IPS display', NOW());
