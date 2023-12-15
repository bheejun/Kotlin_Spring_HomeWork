package com.example.kotlin_spring_homework.post.service

import com.example.kotlin_spring_homework.post.dto.PostRequest
import com.example.kotlin_spring_homework.post.dto.PostResponse

interface PostService {
    fun createPost(postRequest: PostRequest): PostResponse
    fun getPost(id: Long): PostResponse
    fun updatePost(id: Long, postRequest: PostRequest): PostResponse
    fun deletePost(id: Long)
}