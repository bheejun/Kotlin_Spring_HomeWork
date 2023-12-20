package com.example.kotlin_spring_homework.comment.service

import com.example.kotlin_spring_homework.comment.dto.CommentRequest
import com.example.kotlin_spring_homework.comment.dto.CommentResponse
import com.example.kotlin_spring_homework.user.model.User

interface CommentService {
    fun createComment(commentRequest: CommentRequest): CommentResponse
    fun getComment(id: Long): CommentResponse
    fun updateComment(id: Long, commentRequest: CommentRequest): CommentResponse
    fun deleteComment(id: Long)
    fun compareUser(commentUser: User?): Boolean

}