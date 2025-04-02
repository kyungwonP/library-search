package com.library.controller.response

import spock.lang.Specification

import java.time.LocalDate

class PageResultTest extends Specification {
    def "pageResult 객체 생성된다."() {
        given:
        def givenTitle = "HTTP 완벽가이드"
        def givenAuthor = "데이빗 고울리"
        def givenPublisher = "인사이트"
        def givenPubDate = LocalDate.of(2014, 12, 14)
        def givenIsbn = "98989898"
        def givenDescription = "굳"

        when:
        def result = SearchResponse.builder()
                .title(givenTitle)
                .author(givenAuthor)
                .publisher(givenPublisher)
                .pubdate(givenPubDate)
                .description(givenDescription)
                .isbn(givenIsbn)
                .build()


        then:
        verifyAll {
            result.title() == givenTitle
            result.author() == givenAuthor
            result.publisher() == givenPublisher
            result.pubdate() == givenPubDate
            result.description() == givenDescription
            result.isbn() == givenIsbn
        }

    }
}
