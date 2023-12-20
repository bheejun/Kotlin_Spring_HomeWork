package com.example.kotlin_spring_homework.domain.post.service

import com.example.kotlin_spring_homework.domain.post.dto.PostRequest
import com.example.kotlin_spring_homework.domain.post.dto.PostResponse
import com.example.kotlin_spring_homework.domain.post.model.Post

interface PostService {
    fun createPost(postRequest: PostRequest): PostResponse
    fun getPost(id: Long): PostResponse
    fun updatePost(id: Long, postRequest: PostRequest): PostResponse
    fun deletePost(id: Long)
    fun compareUser(post: Post) : Boolean
}