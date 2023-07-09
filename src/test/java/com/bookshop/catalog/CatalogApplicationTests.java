package com.bookshop.catalog;

import com.bookshop.catalog.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
		// Loads a full Spring web application context
		// and a Servlet container listening on a random port
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	void whenPostRequest_bookIsCreated() {

		var expectedBook = new Book(
				"1234567890",
				"Title",
				"Author",
				10.99
		);

		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class).value(actualBook -> {
					assertNotNull(actualBook);
					assertEquals( expectedBook.isbn(), actualBook.isbn());
				});

	}
}
