package com.example.kotlin_spring_homework.user.service

import com.example.kotlin_spring_homework.user.dto.LogInRequest
import com.example.kotlin_spring_homework.user.dto.SignUpRequest
import com.example.kotlin_spring_homework.user.dto.UserResponse
import com.example.kotlin_spring_homework.user.model.User
import com.example.kotlin_spring_homework.user.model.toResponse
import com.example.kotlin_spring_homework.user.repository.UserRepository
import com.example.kotlin_spring_homework.util.JwtUtil
import jakarta.transaction.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository,
                      private val jwtUtil: JwtUtil,
                      private val passwordEncoder: BCryptPasswordEncoder) :UserService{

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        if (userRepository.existsByUsername(signUpRequest.username)){
            throw IllegalStateException("Username is already exist")
        }
        return userRepository.save(
            User(
                username = signUpRequest.username,
                password = signUpRequest.password
            )
        ).toResponse()
    }

    @Transactional
    override fun login(logInRequest: LogInRequest): String {
            val username = logInRequest.username
            val password = logInRequest.password

            if(userRepository.existsByUsername(username)){
                val user = userRepository.findByUsername(username)
                if (!passwordEncoder.matches(password, user.password)) {
                    throw IllegalArgumentException("Invalid password")
                }
                return jwtUtil.generateToken(username)
            }else{
                throw IllegalArgumentException("Not exist username")
            }



        }
    }

}