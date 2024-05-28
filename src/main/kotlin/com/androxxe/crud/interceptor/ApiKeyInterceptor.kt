package com.androxxe.crud.interceptor

import com.androxxe.crud.exception.UnauthorizedException
import com.androxxe.crud.repository.ApiKeyRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class ApiKeyInterceptor(
    private val apiKeyRepository: ApiKeyRepository,
    keyRepository: ApiKeyRepository
): WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val headerApiKey = request.getHeader("X-Api-Key")
        if (headerApiKey == null) {
            throw UnauthorizedException()
        }

        apiKeyRepository.findByIdOrNull(headerApiKey) ?: throw UnauthorizedException()
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        // Nothing
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        // Nothing
    }
}