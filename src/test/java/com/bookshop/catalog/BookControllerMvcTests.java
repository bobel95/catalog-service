package com.bookshop.catalog;

import com.bookshop.catalog.domain.BookService;
import com.bookshop.catalog.domain.exceptions.BookNotFoundException;
import com.bookshop.catalog.web.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


// Identifies a test class that focuses on Spring MVC components,
// explicitly targeting BookController
@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenGetBookNotExisting_returns404() throws Exception {

        String isbn = "123457890";

        given(bookService.viewDetails(isbn))
            .willThrow(BookNotFoundException.class);

        mockMvc
            .perform(get("/books/" + isbn))
            .andExpect(status().isNotFound());

    }


}
