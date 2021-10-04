package com.droissor.albumapi.controller

import com.droissor.albumapi.response.AlbumResponse
import com.droissor.albumapi.service.AlbumService
import com.droissor.albumapi.vo.toAlbumResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users/{id}/albums")
class AlbumController(
        private val albumService: AlbumService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun userAlbums(@PathVariable id: String): List<AlbumResponse> {
        val albumResponseList = albumService.getAlbums(id).map { it.toAlbumResponse() }
        logger.info("Returning " + albumResponseList.size + " albums")
        return albumResponseList
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}