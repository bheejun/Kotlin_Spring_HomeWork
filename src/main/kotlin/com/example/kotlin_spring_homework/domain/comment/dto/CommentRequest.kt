package com.example.kotlin_spring_homework.domain.comment.dto

data class CommentRequest(
    val postId: Long,
    val content: String
)