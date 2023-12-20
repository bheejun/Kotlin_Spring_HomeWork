package com.example.kotlin_spring_homework.domain.post.repository

import com.example.kotlin_spring_homework.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>