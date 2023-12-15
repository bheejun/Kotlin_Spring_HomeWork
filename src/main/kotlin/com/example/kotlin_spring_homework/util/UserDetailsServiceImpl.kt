package com.example.kotlin_spring_homework.util

import com.example.kotlin_spring_homework.user.model.User
import com.example.kotlin_spring_homework.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository): UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw IllegalArgumentException("")

    }

    fun findUserByUsername(username: String): User ? {
        return userRepository.findByUsername(username)
    }
}