package com.example.kotlin_spring_homework.post.dto

import com.example.kotlin_spring_homework.comment.dto.CommentResponse

data class PostResponse(
    val id: Long,
    val username: String,
    val title: String,
    val content: String,
    val comments: List<CommentResponse>
)
