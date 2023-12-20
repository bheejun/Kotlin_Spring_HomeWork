package com.example.kotlin_spring_homework.domain.post.controller

import com.example.kotlin_spring_homework.domain.post.dto.PostRequest
import com.example.kotlin_spring_homework.domain.post.dto.PostResponse
import com.example.kotlin_spring_homework.domain.post.service.PostService
import com.example.kotlin_spring_homework.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    @PostMapping
    fun createPost(@RequestBody postRequest: PostRequest): ResponseEntity<PostResponse> {
        val post = postService.createPost(postRequest)
        return ResponseEntity.ok(post)
    }

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long): ResponseEntity<PostResponse> {
        val post = postService.getPost(id)
        return ResponseEntity.ok(post)
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody postRequest: PostRequest): ResponseEntity<PostResponse> {
        val updatedPost = postService.updatePost(id, postRequest)
        return ResponseEntity.ok(updatedPost)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Void> {
        postService.deletePost(id)
        return ResponseEntity.noContent().build()
    }
}