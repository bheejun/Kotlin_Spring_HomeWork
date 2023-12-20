package com.example.kotlin_spring_homework.user.service

import com.example.kotlin_spring_homework.user.dto.LogInRequest
import com.example.kotlin_spring_homework.user.dto.SignUpRequest
import com.example.kotlin_spring_homework.user.dto.UserResponse
import com.example.kotlin_spring_homework.user.model.User
import com.example.kotlin_spring_homework.user.model.toResponse
import com.example.kotlin_spring_homework.user.repository.UserRepository
import com.example.kotlin_spring_homework.util.JwtUtil
import jakarta.servlet.http.HttpServletResponse
import jakarta.transaction.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val response: HttpServletResponse
) : UserService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        if (userRepository.existsByUsername(signUpRequest.username)) {
            throw IllegalArgumentException("Username is already exist")
        }
        val encodedPassword = passwordEncoder.encode(signUpRequest.password)
        return userRepository.save(
            User(
                username = signUpRequest.username,
                password = encodedPassword
            )
        ).toResponse()
    }

    @Transactional
    override fun login(logInRequest: LogInRequest): String {
        val username = logInRequest.username
        val password = logInRequest.password

        val token = jwtUtil.generateToken(username)

        val user = userRepository.findByUsername(username)
            .orElseThrow { IllegalArgumentException("Not exist username") }

        if (!passwordEncoder.matches(password, user.password)) {
            throw IllegalArgumentException("Invalid password")
        }

        response.addHeader("Authorization", "Bearer $token")

        return token


    }
}

