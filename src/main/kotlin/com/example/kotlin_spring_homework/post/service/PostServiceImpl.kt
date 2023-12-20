package com.example.kotlin_spring_homework.post.service

import com.example.kotlin_spring_homework.post.dto.PostRequest
import com.example.kotlin_spring_homework.post.dto.PostResponse
import com.example.kotlin_spring_homework.post.model.Post
import com.example.kotlin_spring_homework.post.repository.PostRepository
import com.example.kotlin_spring_homework.user.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import com.example.kotlin_spring_homework.util.toResponse
import jakarta.transaction.Transactional

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : PostService {

    @Transactional
    override fun createPost(postRequest: PostRequest): PostResponse {
        val authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name

        val user = userRepository.findByUsername(username).orElseThrow{
            IllegalArgumentException("Not exist username") }

        val post = Post(title = postRequest.title, content = postRequest.content, user = user)
        postRepository.save(post)
        return post.toResponse()
    }

    @Transactional
    override fun getPost(id: Long): PostResponse {
        val post = postRepository.findById(id).orElseThrow { RuntimeException("Post not found") }
        val commentResponses = post.comments.map { it.toResponse()}
        return post.toResponse()
    }

    @Transactional
    override fun updatePost(id: Long, postRequest: PostRequest): PostResponse {
        val post = postRepository.findById(id).orElseThrow { RuntimeException("Post not found") }

        if(compareUser(post)){
            post.title = postRequest.title
            post.content = postRequest.content
            postRepository.save(post)
            return post.toResponse()
        }else{
            throw IllegalArgumentException("Not authorized to update this post")

        }
    }

    @Transactional
    override fun deletePost(id: Long) {
        val post = postRepository.findById(id).orElseThrow { RuntimeException("Post not found") }
        if(compareUser(post)){
            return postRepository.deleteById(id)
        }else{
            throw IllegalArgumentException("Not authorized to delete this post")

        }

    }

    override fun compareUser(post: Post): Boolean {
        val currentUsername = SecurityContextHolder.getContext().authentication.name
        val postUsername = post.user?.username

        return currentUsername.equals(postUsername)
    }

}