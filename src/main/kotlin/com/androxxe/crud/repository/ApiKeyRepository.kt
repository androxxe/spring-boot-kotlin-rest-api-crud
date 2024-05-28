package com.androxxe.crud.repository

import com.androxxe.crud.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository: JpaRepository<ApiKey, String> {

}