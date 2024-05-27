package com.androxxe.crud.service

import com.androxxe.crud.entity.Product
import com.androxxe.crud.model.CreateProductRequest
import com.androxxe.crud.model.ProductResponse
import com.androxxe.crud.model.UpdateProductRequest

interface ProductService {
    fun create(data: CreateProductRequest): ProductResponse

    fun get(id: String): ProductResponse

    fun update(id: String, data: UpdateProductRequest): ProductResponse

    fun delete(id: String)
}