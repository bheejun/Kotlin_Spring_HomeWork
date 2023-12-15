package com.example.kotlin_spring_homework.comment.dto

data class CommentRequest(
    val postId: Long,
    val content: String,
    val username: String
)