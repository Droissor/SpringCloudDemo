package com.droissor.photoapi.vo

import com.droissor.photoapi.entity.AlbumEntity
import com.droissor.photoapi.response.AlbumResponse

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