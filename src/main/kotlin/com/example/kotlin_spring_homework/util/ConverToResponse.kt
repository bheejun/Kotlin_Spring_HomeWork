package com.example.kotlin_spring_homework.util

import com.example.kotlin_spring_homework.comment.dto.CommentResponse
import com.example.kotlin_spring_homework.comment.model.Comment
import com.example.kotlin_spring_homework.post.dto.PostResponse
import com.example.kotlin_spring_homework.post.model.Post
import org.springframework.security.core.context.SecurityContextHolder

fun Comment.toResponse(): CommentResponse =
    CommentResponse(id = this.id!!, content = this.content, username = this.user?.username?: "Unknown User")

fun Post.toResponse(): PostResponse {

    val comments = this.comments.map { it.toResponse() }
    return PostResponse(
        id = this.id!!,
        username = SecurityContextHolder.getContext().authentication.name,
        title = this.title,
        content = this.content,
        comments = comments
    )
}