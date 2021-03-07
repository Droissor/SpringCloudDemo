package com.droissor.apigateway.configuration.filter

import io.jsonwebtoken.Jwts
import java.lang.Exception
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class AuthorizationHeaderFilter(@Value("\${token.secret}") private val tokenSecret: String) :
    AbstractGatewayFilterFactory<AuthorizationHeaderFilter.HeaderFilterConfiguration>(HeaderFilterConfiguration::class.java) {

    override fun apply(config: HeaderFilterConfiguration): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->

            exchange.request.headers[HttpHeaders.AUTHORIZATION]?.let { authHeaders ->

                val authToken = authHeaders[0]

                if (authToken.isNotValidJWT())
                    return@GatewayFilter applyUnauthorizedError(exchange)

                chain.filter(exchange)

            } ?: return@GatewayFilter applyUnauthorizedError(exchange)
        }
    }

    private fun applyUnauthorizedError(exchange: ServerWebExchange): Mono<Void> =
        exchange.response.let {
            it.statusCode = HttpStatus.UNAUTHORIZED
            return it.setComplete()
        }

    private fun String.isNotValidJWT(): Boolean {
        try {
            Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(this).body.subject.let {
                if (it.isNullOrEmpty())
                    return true
                return false
            }
        } catch (exception: Exception) {
            return true
        }
    }

    class HeaderFilterConfiguration
}



