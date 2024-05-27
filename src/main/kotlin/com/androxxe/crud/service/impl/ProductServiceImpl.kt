package com.androxxe.crud.service.impl

import com.androxxe.crud.entity.Product
import com.androxxe.crud.model.CreateProductRequest
import com.androxxe.crud.model.ProductResponse
import com.androxxe.crud.repository.ProductRepository
import com.androxxe.crud.service.ProductService
import org.springframework.stereotype.Service
import java.util.Date

@Service
class ProductServiceImpl(val productRepository: ProductRepository): ProductService {
    override fun create(data: CreateProductRequest): ProductResponse {
        val product = Product(
            id = data.id!!,
            name = data.name!!,
            price = data.price!!,
            quantity = data.quantity!!,
            createdAt = Date(),
            updatedAt = null
        )

        productRepository.save(product)

        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }
}