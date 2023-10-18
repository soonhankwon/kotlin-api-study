package dev.soon.kotlinapistudy.blog.service

import dev.soon.kotlinapistudy.blog.dto.BlogDto
import org.springframework.stereotype.Service

@Service
class BlogService {

    fun searchKakao(blogDto: BlogDto): String? {
        println(blogDto.toString())
        return "SearchKakao"
    }
}