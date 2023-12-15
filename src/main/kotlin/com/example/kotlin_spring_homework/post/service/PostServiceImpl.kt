package com.example.kotlin_spring_homework.post.service

import com.example.kotlin_spring_homework.post.dto.PostRequest
import com.example.kotlin_spring_homework.post.dto.PostResponse
import com.example.kotlin_spring_homework.post.model.Post
import com.example.kotlin_spring_homework.post.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(private val postRepository: PostRepository) : PostService {

    override fun createPost(postRequest: PostRequest): PostResponse {
        val post = Post(title = postRequest.title, content = postRequest.content)
        postRepository.save(post)
        return post.toResponse()
    }

    override fun getPost(id: Long): PostResponse {
        val post = postRepository.findById(id).orElseThrow { RuntimeException("Post not found") }
        return post.toResponse()
    }

    override fun updatePost(id: Long, postRequest: PostRequest): PostResponse {
        val post = postRepository.findById(id).orElseThrow { RuntimeException("Post not found") }
        post.title = postRequest.title
        post.content = postRequest.content
        postRepository.save(post)
        return post.toResponse()
    }

    override fun deletePost(id: Long) {
        postRepository.deleteById(id)
    }

    private fun Post.toResponse(): PostResponse = PostResponse(id = this.id!!, title = this.title, content = this.content)
}