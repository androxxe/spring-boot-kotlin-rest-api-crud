package com.androxxe.crud.service

import com.androxxe.crud.entity.Product
import com.androxxe.crud.model.CreateProductRequest
import com.androxxe.crud.model.ProductResponse

interface ProductService {
    fun create(data: CreateProductRequest): ProductResponse
}