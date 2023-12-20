package com.example.kotlin_spring_homework.domain.comment.repository

import com.example.kotlin_spring_homework.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>