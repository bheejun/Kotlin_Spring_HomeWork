package com.example.kotlin_spring_homework.comment.dto

import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val content: String,
    val username: String
)