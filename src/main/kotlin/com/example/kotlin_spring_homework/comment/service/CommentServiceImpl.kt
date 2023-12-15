package com.example.kotlin_spring_homework.comment.service.impl

import com.example.kotlin_spring_homework.comment.dto.CommentRequest
import com.example.kotlin_spring_homework.comment.dto.CommentResponse
import com.example.kotlin_spring_homework.comment.model.Comment
import com.example.kotlin_spring_homework.comment.repository.CommentRepository
import com.example.kotlin_spring_homework.comment.service.CommentService
import com.example.kotlin_spring_homework.post.repository.PostRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository
) : CommentService {

    override fun createComment(commentRequest: CommentRequest): CommentResponse {
        val post = postRepository.findById(commentRequest.postId)
            .orElseThrow { IllegalArgumentException ("Post not found") }
        val comment = Comment(
            content = commentRequest.content,
            post = post,
            username = commentRequest.username
        )
        commentRepository.save(comment)
        return comment.toResponse()
    }

    override fun getComment(id: Long): CommentResponse {
        val comment = commentRepository.findById(id)
            .orElseThrow { RuntimeException("Comment not found") }
        return comment.toResponse()
    }

    override fun updateComment(id: Long, commentRequest: CommentRequest): CommentResponse {
        val comment = commentRepository.findById(id)
            .orElseThrow { RuntimeException("Comment not found") }
        comment.content = commentRequest.content
        commentRepository.save(comment)
        return comment.toResponse()
    }

    override fun deleteComment(id: Long) {
        commentRepository.deleteById(id)
    }

    private fun Comment.toResponse(): CommentResponse {
        return CommentResponse(
            id = this.id!!,
            content = this.content,
            username = this.user.username,
            creationDate = this.creationDate
        )
    }
}