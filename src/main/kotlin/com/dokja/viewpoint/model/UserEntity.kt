package com.dokja.viewpoint.model

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import java.util.Date


enum class Role {
    USER, ADMIN
}

@Entity
@Table(name = "User")
data class User(
    @Id
    @Column(name = "id")
    val id: String,
    @Column(name = "username")
    val username: String,
    @Column(name = "passwordHash")
    val passwordHash: String,
    @Column(name = "createdAt")
    val createdAt: Date,
    @Column(name = "updatedAt")
    val updatedAt: Date,
    @Column(name = "avatar")
    val avatar: String?,
    @Column(name = "bio")
    val bio: String?,
    @OneToMany(mappedBy = "user")
    val userBooks: List<UserBook>?,
    @ManyToMany
    @JoinTable(
        name = "_UserBookCategory",
        joinColumns = [JoinColumn(name = "B")],
        inverseJoinColumns = [JoinColumn(name = "A")]
    )
    val bookCategories: List<BookCategory> = listOf(),

    // Work around the default string mapping.
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Basic(optional = false)
    val role: Role
)

@Entity
@Table(name = "UserBook")
data class UserBook(
    @Id
    @Column(name = "id")
    val id: String,
    @ManyToOne
    @JoinColumn(name = "userID")
    val user: User,
    @ManyToOne
    @JoinColumn(name = "bookID")
    val book: Book,
    @ManyToMany
    @JoinTable(
        name = "_BookCategory",
        joinColumns = [JoinColumn(name = "A")],
        inverseJoinColumns = [JoinColumn(name = "B")]
    )
    val bookCategories: List<BookCategory>,
    @Column(name = "pagesRead")
    val pagesRead: String?,
    @Column(name = "chaptersRead")
    val chaptersRead: String?,
    @Column(name = "rating")
    val rating: String?,
    @Column(name = "rereads")
    val rereads: String?,
    @Column(name = "notes")
    val notes: String?,
    @Column(name = "startedDate")
    val startedDate: LocalDateTime?,
    @Column(name = "completedDate")
    val completedDate: LocalDateTime?
)