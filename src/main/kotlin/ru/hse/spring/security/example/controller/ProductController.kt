package ru.hse.spring.security.example.controller

import org.springframework.web.bind.annotation.*
import ru.hse.spring.security.example.dto.Product
import ru.hse.spring.security.example.service.ProductService

@RestController
@RequestMapping("/products")
class ProductController(
    val productService: ProductService
) {

    @GetMapping
    fun getproducts() =
        productService.getProducts()

    @GetMapping("/add")
    fun addProduct() {
        productService.addProduct(Product("123", 123.0))
    }
}