package dev.soon.kotlinapistudy.blog.controller

import dev.soon.kotlinapistudy.blog.dto.BlogDto
import dev.soon.kotlinapistudy.blog.service.BlogService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/blog")
@RestController
class BlogController(
    val blogService: BlogService
) {
    @GetMapping
    fun searchBlog(@RequestBody @Valid blogDto: BlogDto): String? {
        return blogService.searchKakao(blogDto)
    }
}