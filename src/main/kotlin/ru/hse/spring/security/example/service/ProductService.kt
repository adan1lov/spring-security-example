package ru.hse.spring.security.example.service

import org.springframework.stereotype.Service
import ru.hse.spring.security.example.dto.Product

@Service
class ProductService(
    val productList: MutableList<Product> = mutableListOf(
        Product("Яблоко", 10.0),
        Product("Апельсин", 20.0),
    )
) {

    fun getProducts() = productList

    fun addProduct(product: Product) {
        productList.add(product)
    }
}