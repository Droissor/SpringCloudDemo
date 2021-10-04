package com.droissor.userapi.client

import com.droissor.userapi.response.Album
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "album-ws", fallbackFactory = AlbumClientFallback::class)
interface AlbumClient {

    @GetMapping("/users/{id}/albums")
    fun getUserAlbums(@PathVariable id: String): List<Album>

}