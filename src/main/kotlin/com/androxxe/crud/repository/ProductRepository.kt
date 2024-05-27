package com.androxxe.crud.repository

import com.androxxe.crud.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, String> {
    
}