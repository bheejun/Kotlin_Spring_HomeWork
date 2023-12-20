package com.example.kotlin_spring_homework.comment.model

import com.example.kotlin_spring_homework.post.model.Post
import com.example.kotlin_spring_homework.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comments")
class Comment(
    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String ="",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,

    @Column(nullable = false)
    var creationDate: LocalDateTime = LocalDateTime.now()


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}