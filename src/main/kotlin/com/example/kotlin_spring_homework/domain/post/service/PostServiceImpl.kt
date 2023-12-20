package com.example.kotlin_spring_homework.domain.post.service

import com.example.kotlin_spring_homework.domain.post.dto.PostRequest
import com.example.kotlin_spring_homework.domain.post.dto.PostResponse
import com.example.kotlin_spring_homework.domain.post.model.Post
import com.example.kotlin_spring_homework.domain.post.repository.PostRepository
import com.example.kotlin_spring_homework.domain.user.repository.UserRepository
import com.example.kotlin_spring_homework.exception.PostNotFoundException
import com.example.kotlin_spring_homework.exception.UnauthorizedUserException
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
        val post = postRepository.findById(id).orElseThrow { PostNotFoundException("Post not found") }
        return post.toResponse()
    }

    @Transactional
    override fun updatePost(id: Long, postRequest: PostRequest): PostResponse {
        val post = postRepository.findById(id).orElseThrow { PostNotFoundException("Post not found") }

        if(compareUser(post)){
            post.title = postRequest.title
            post.content = postRequest.content
            postRepository.save(post)
            return post.toResponse()
        }else{
            throw UnauthorizedUserException("Not authorized to update this post")

        }
    }

    @Transactional
    override fun deletePost(id: Long) {
        val post = postRepository.findById(id).orElseThrow { PostNotFoundException("Post not found") }
        if(compareUser(post)){
            return postRepository.deleteById(id)
        }else{
            throw UnauthorizedUserException("Not authorized to delete this post")

        }

    }

    override fun compareUser(post: Post): Boolean {
        val currentUsername = SecurityContextHolder.getContext().authentication.name
        val postUsername = post.user?.username

        return currentUsername.equals(postUsername)
    }

}