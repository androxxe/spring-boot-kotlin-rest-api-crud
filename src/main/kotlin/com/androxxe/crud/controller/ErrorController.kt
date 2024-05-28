package com.androxxe.crud.controller

import com.androxxe.crud.exception.NotFoundException
import com.androxxe.crud.exception.UnauthorizedException
import com.androxxe.crud.model.WebResponse
import jakarta.validation.ConstraintViolationException
import jakarta.validation.constraints.Null
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController
{
    @ExceptionHandler(value = [ConstraintViolationException::class])
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun valiationHandler(exception: ConstraintViolationException): WebResponse<List<Map<String, String>>> {
        val errors = exception.constraintViolations.map {
            mapOf(it.propertyPath.toString() to it.message)
        }

        return WebResponse(
            code = 422,
            status = "PayloadInvalid",
            data = errors
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundException(exception: NotFoundException): WebResponse<Null> {
        return WebResponse(
            code = 404,
            status = "NotFound",
            data = null
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun unauthorizedException(exception: UnauthorizedException): WebResponse<Null> {
        return WebResponse(
            code = 401,
            status = "Unauthorized",
            data = null
        )
    }

}