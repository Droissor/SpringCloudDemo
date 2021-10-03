package com.droissor.photoapi.service

import com.droissor.photoapi.entity.AlbumEntity
import com.droissor.photoapi.vo.AlbumDto
import org.springframework.stereotype.Service

@Service
class AlbumService {

    fun getAlbums(userId: String): List<AlbumDto> = findAlbumsByUserId(userId).map { AlbumDto.fromAlbum(it) }

    private fun findAlbumsByUserId(userId: String): List<AlbumEntity> = listOf(
            AlbumEntity(
                    albumId = "1",
                    userId = userId,
                    name = "My first album",
                    description = "Holidays in Valfenda"
            ),
            AlbumEntity(
                    albumId = "2",
                    userId = userId,
                    name = "My second album",
                    description = "Lord of The Rings in Concert"
            )
    )
}