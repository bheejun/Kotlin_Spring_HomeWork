package com.example.kotlin_spring_homework.comment.service

import com.example.kotlin_spring_homework.comment.dto.CommentRequest
import com.example.kotlin_spring_homework.comment.dto.CommentResponse

interface CommentService {
    fun createComment(commentRequest: CommentRequest): CommentResponse
    fun getComment(id: Long): CommentResponse
    fun updateComment(id: Long, commentRequest: CommentRequest): CommentResponse
    fun deleteComment(id: Long)

}