package com.bookshop.catalog.domain;

public record Book(
        String isbn,
        String title,
        String author,
        int price
) { }
