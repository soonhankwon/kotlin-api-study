package dev.soon.kotlinapistudy.blog.repository

import dev.soon.kotlinapistudy.blog.entity.WordCount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WordCountRepository : JpaRepository<WordCount, String>