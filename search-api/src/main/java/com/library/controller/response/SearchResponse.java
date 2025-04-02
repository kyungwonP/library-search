package com.library.controller.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SearchResponse(String title, String author, String publisher, LocalDate pubdate, String description, String isbn) {

}