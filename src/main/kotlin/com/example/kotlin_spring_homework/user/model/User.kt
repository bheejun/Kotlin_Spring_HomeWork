package com.example.kotlin_spring_homework.user.model

import com.example.kotlin_spring_homework.post.model.Post
import com.example.kotlin_spring_homework.user.dto.UserResponse
import jakarta.persistence.*


@Entity
@Table(name = "blog_user")
class User(
    @Column (name = "username", nullable = false)
    val username: String = "username",

    @Column (name = "password", nullable = false)
    val password: String = "password"


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? =null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var posts: MutableList<Post> = mutableListOf()
}

fun User.toResponse():UserResponse{
    return UserResponse(
        username = username,
        password = password
    )
}