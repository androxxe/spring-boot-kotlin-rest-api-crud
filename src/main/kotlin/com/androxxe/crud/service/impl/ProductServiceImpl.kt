package com.androxxe.crud.service.impl

import com.androxxe.crud.entity.Product
import com.androxxe.crud.exception.NotFoundException
import com.androxxe.crud.model.CreateProductRequest
import com.androxxe.crud.model.ProductResponse
import com.androxxe.crud.model.UpdateProductRequest
import com.androxxe.crud.repository.ProductRepository
import com.androxxe.crud.service.ProductService
import com.androxxe.crud.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Date

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
): ProductService {
    override fun create(data: CreateProductRequest): ProductResponse {
        validationUtil.validate(data)

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

    override fun get(id: String): ProductResponse {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw NotFoundException()
        }

        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }

    override fun update(id: String, data: UpdateProductRequest): ProductResponse {
        validationUtil.validate(data)

        val product = productRepository.findByIdOrNull(id);
        if (product == null){
            throw NotFoundException()
        }

        product.apply {
            name = data.name!!
            price = data.price!!
            quantity = data.quantity!!
            updatedAt = Date()
        }

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

    override fun delete(id: String) {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw NotFoundException()
        }

        productRepository.delete(product)
    }
}