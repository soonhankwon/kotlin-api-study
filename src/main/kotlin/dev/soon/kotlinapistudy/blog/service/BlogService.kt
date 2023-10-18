package dev.soon.kotlinapistudy.blog.service

import dev.soon.kotlinapistudy.blog.dto.BlogDto
import dev.soon.kotlinapistudy.blog.entity.WordCount
import dev.soon.kotlinapistudy.blog.repository.WordCountRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class BlogService(
    val wordCountRepository : WordCountRepository
){

    @Value("\${kakao.rest-api.key}")
    lateinit var kakaoRestApiKey: String

    @Transactional
    fun searchKakao(blogDto: BlogDto): String? {
        val webClient = WebClient
            .builder()
            .baseUrl("https://dapi.kakao.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        val response = webClient
            .get()
            .uri {
                it.path("/v2/search/blog")
                    .queryParam("query", blogDto.query)
                    .queryParam("sort", blogDto.sort)
                    .queryParam("page", blogDto.page)
                    .queryParam("size", blogDto.size)
                    .build()
            }
            .header("Authorization", "KakaoAK $kakaoRestApiKey")
            .retrieve()
            .bodyToMono<String>()

        val lowQuery: String = blogDto.query.lowercase()
        val word: WordCount = wordCountRepository.findById(lowQuery)
            .orElse(WordCount(lowQuery))
        word.cnt++

        wordCountRepository.save(word)

        return response.block()
    }

    fun searchWordRank(): List<WordCount> = wordCountRepository.findTop10ByOrderByCntDesc()
}