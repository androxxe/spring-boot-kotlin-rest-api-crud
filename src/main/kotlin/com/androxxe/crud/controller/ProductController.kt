package com.androxxe.crud.controller

import com.androxxe.crud.model.CreateProductRequest
import com.androxxe.crud.model.ProductResponse
import com.androxxe.crud.model.WebResponse
import com.androxxe.crud.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productService: ProductService) {

    @PostMapping(
        value = ["/api/product"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.create(body)

        return WebResponse(
            code = 201,
            status = "success",
            data = productResponse
        )
    }
}