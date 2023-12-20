package com.example.kotlin_spring_homework.domain.user.repository

import com.example.kotlin_spring_homework.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {

    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String) : Optional<User>

}