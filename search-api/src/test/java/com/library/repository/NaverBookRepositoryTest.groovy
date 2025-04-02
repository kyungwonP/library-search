package com.library.repository

import com.library.Item
import com.library.NaverBookResponse
import com.library.feign.NaverClient
import spock.lang.Specification

import java.time.LocalDate

class NaverBookRepositoryTest extends Specification {
    BookRepository bookRepository

    NaverClient naverClient = Mock()

    void setup() {
        bookRepository = new NaverBookRepository(naverClient)
    }

    def "search 호출 시 적절한 데이터 형식으로 변환한다."() {
        given:
        def items = [
                new Item(title: "제목", author: "author", publisher: "publisher", pubDate: "20250202", isbn: "isbn"),
                new Item(title: "제목2", author: "author2", publisher: "publisher2", pubDate: "20250206", isbn: "isbn2"),
        ]

        def response = new NaverBookResponse(
                lastBuildDate: "",
                total: 2,
                start: 1,
                display: 2,
                items: items
        )

        and:
        1 * naverClient.search("HTTP", 1, 2) >> response

        when:
        def result = bookRepository.search("HTTP", 1, 2)

        then:
        verifyAll {
            result.size() == 2
            result.page() == 1
            result.totalElements() == 2
            result.contents().size() == 2
            result.contents().get(0).pubdate() == LocalDate.of(2024, 1, 1)
        }

    }
}
