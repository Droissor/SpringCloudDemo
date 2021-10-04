package com.droissor.albumapi.vo

import com.droissor.albumapi.entity.AlbumEntity
import com.droissor.albumapi.response.AlbumResponse

data class AlbumDto(
        val albumId: String,
        val userId: String,
        val name: String,
        val description: String
) {
    companion object {
        fun fromAlbum(album: AlbumEntity) = AlbumDto(
                albumId = album.albumId,
                userId = album.userId,
                name = album.name,
                description = album.description
        )
    }
}

fun AlbumDto.toAlbumResponse() = AlbumResponse(
        albumId = this.albumId,
        userId = this.userId,
        name = this.name,
        description = this.description
)