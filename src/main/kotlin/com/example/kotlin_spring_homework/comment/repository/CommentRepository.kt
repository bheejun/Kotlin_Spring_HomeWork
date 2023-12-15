package com.example.kotlin_spring_homework.comment.repository

import com.example.kotlin_spring_homework.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>