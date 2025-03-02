package com.androxxe.crud.controller

import com.androxxe.crud.model.*
import com.androxxe.crud.service.ProductService
import org.springframework.data.jpa.repository.Query
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productService: ProductService) {

    @PostMapping(
        value = ["/api/product"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val product = productService.create(body)

        return WebResponse(
            code = 201,
            status = "success",
            data = product
        )
    }

    @GetMapping(
        value = ["api/product/{product_id}"],
        produces = ["application/json"]
    )
    fun show(@PathVariable("product_id") id: String): WebResponse<ProductResponse> {
        val product = productService.get(id)

        return WebResponse(
            code = 200,
            status = "Success",
            data = product
        )
    }

    @PutMapping(
        value = ["api/product/{product_id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun update(@PathVariable("product_id") id: String, @RequestBody data: UpdateProductRequest): WebResponse<ProductResponse> {
        val product = productService.update(id, data)

        return WebResponse(
            code = 200,
            status = "Success",
            data = product
        )
    }

    @DeleteMapping(
        value = ["api/product/{product_id}"],
        produces = ["application/json"]
    )
    fun destroy(@PathVariable("product_id") id: String): WebResponse<String> {
        productService.delete(id)

        return WebResponse(
            code = 200,
            status = "Success",
            data = null
        )
    }

    @GetMapping(
        value = ["api/products"],
        produces = ["application/json"]
    )
    fun list(@RequestParam(value = "size", defaultValue = "10") size: Int, @RequestParam(value = "page", defaultValue = "0") page: Int): WebResponse<List<ProductResponse>> {
        val request = ListProductRequest(page = page, size = size)
        val products = productService.list((request))

        return WebResponse(
            code = 200,
            status = "Success",
            data = products
        )
    }

}