package com.example.kotlin_spring_homework.domain.post.dto

import com.example.kotlin_spring_homework.domain.comment.dto.CommentResponse

data class PostResponse(
    val id: Long,
    val username: String,
    val title: String,
    val content: String,
    val comments: List<CommentResponse>
)
