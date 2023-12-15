package com.example.kotlin_spring_homework.post.model

import com.example.kotlin_spring_homework.comment.model.Comment
import com.example.kotlin_spring_homework.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "post")
class Post(
    @Column(nullable = false, name = "title")
    var title: String,

    @Column(nullable = false, name = "content")
    var content: String,

    @Column(nullable = false)
    var creationDate: LocalDateTime = LocalDateTime.now(),


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf()

}