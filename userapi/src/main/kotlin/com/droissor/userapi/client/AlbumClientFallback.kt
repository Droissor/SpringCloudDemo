package com.droissor.userapi.client

import com.droissor.userapi.response.Album
import feign.FeignException
import org.springframework.cloud.openfeign.FallbackFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class AlbumClientFallbackFactory : FallbackFactory<AlbumClient> {
    override fun create(cause: Throwable): AlbumClient = AlbumClientFallback(cause)
}

class AlbumClientFallback(private val cause: Throwable) : AlbumClient {
    override fun getUserAlbums(id: String): List<Album> {
        if(cause is FeignException && cause.status() == HttpStatus.NOT_FOUND.value()) {
            return listOf()
        }
        throw cause
    }
}