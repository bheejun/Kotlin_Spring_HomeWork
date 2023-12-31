package com.example.kotlin_spring_homework.domain.comment.service

import com.example.kotlin_spring_homework.domain.comment.dto.CommentRequest
import com.example.kotlin_spring_homework.domain.comment.dto.CommentResponse
import com.example.kotlin_spring_homework.domain.comment.model.Comment
import com.example.kotlin_spring_homework.domain.comment.repository.CommentRepository
import com.example.kotlin_spring_homework.domain.post.repository.PostRepository
import com.example.kotlin_spring_homework.domain.user.model.User
import com.example.kotlin_spring_homework.domain.user.repository.UserRepository
import com.example.kotlin_spring_homework.exception.CommentNotFoundException
import com.example.kotlin_spring_homework.exception.UnauthorizedUserException
import com.example.kotlin_spring_homework.util.toResponse
import jakarta.transaction.Transactional
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : CommentService {

    @Transactional
    override fun createComment(commentRequest: CommentRequest): CommentResponse {
        val post = postRepository.findById(commentRequest.postId)
            .orElseThrow {  CommentNotFoundException("Comment not found") }

        val username = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByUsername(username).orElseThrow{
            UsernameNotFoundException("Not exist username") }

        val comment = Comment(
            content = commentRequest.content,
            post = post,
            user = user
        )
        commentRepository.save(comment)
        return comment.toResponse()
    }

    @Transactional
    override fun getComment(id: Long): CommentResponse {
        val comment = commentRepository.findById(id)
            .orElseThrow { CommentNotFoundException("Comment not found") }
        return comment.toResponse()
    }

    @Transactional
    override fun updateComment(id: Long, commentRequest: CommentRequest): CommentResponse {
        val comment = commentRepository.findById(id)
            .orElseThrow { CommentNotFoundException("Comment not found") }

        if (compareUser(comment.user)) {
            comment.content = commentRequest.content
            commentRepository.save(comment)
            return comment.toResponse()
        } else {
            throw UnauthorizedUserException("Not authorized to update this comment")
        }
    }

    @Transactional
    override fun deleteComment(id: Long) {
        val comment = commentRepository.findById(id)
            .orElseThrow { CommentNotFoundException("Comment not found") }

        if (compareUser(comment.user)) {
            commentRepository.deleteById(id)
        } else {
            throw UnauthorizedUserException("Not authorized to delete this comment")
        }
    }

    override fun compareUser(commentUser: User?): Boolean {
        val currentUsername = SecurityContextHolder.getContext().authentication.name
        val commentUsername = commentUser?.username

        return currentUsername.equals(commentUsername)
    }
}