package com.example.kotlin_spring_homework.domain.comment.controller

import com.example.kotlin_spring_homework.domain.comment.dto.CommentRequest
import com.example.kotlin_spring_homework.domain.comment.dto.CommentResponse
import com.example.kotlin_spring_homework.domain.comment.service.CommentService
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
@RequestMapping("/comments")
class CommentController(private val commentService: CommentService) {

    @PostMapping
    fun createComment(@RequestBody commentRequest: CommentRequest): ResponseEntity<CommentResponse> {
        val comment = commentService.createComment(commentRequest)
        return ResponseEntity.ok(comment)
    }

    @GetMapping("/{id}")
    fun getComment(@PathVariable id: Long): ResponseEntity<CommentResponse> {
        val comment = commentService.getComment(id)
        return ResponseEntity.ok(comment)
    }

    @PutMapping("/{id}")
    fun updateComment(@PathVariable id: Long, @RequestBody commentRequest: CommentRequest): ResponseEntity<CommentResponse> {
        val updatedComment = commentService.updateComment(id, commentRequest)
        return ResponseEntity.ok(updatedComment)
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<Void> {
        commentService.deleteComment(id)
        return ResponseEntity.noContent().build()
    }
}