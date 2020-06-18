package com.example.library.controller;

import com.example.library.model.Books;
import com.example.library.model.BorrowedBooks;
import com.example.library.model.UserInfo;
import com.example.library.service.BookService;
import com.example.library.service.BorrowedBooksService;
import com.example.library.service.UserInfoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookControllerTests {

    @Mock
    private BookService bookService;

    @Mock
    private BorrowedBooksService borrowedBooksService;

    @Mock
    private UserInfoService userInfoService;

    @InjectMocks
    private BookController bookController;

    private static Books book1;

    private static Books book2;

    private static Books book3;

    private static Books book4;

    private static BorrowedBooks bbook1;

    private static BorrowedBooks bbook2;

    private static UserInfo user1;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        book1 = Books.builder().id(1).title("Book1").author("author1").description("fiction")
                .available(1).build();
        book2 = Books.builder().id(2).title("Book2").author("author2").description("non-fiction")
                .available(1).build();
        book3 = Books.builder().id(3).title("Book3").author("author3").description("fiction")
                .available(1).build();
        book4 = Books.builder().id(4).title("Book4").author("author4").description("non-fiction")
                .available(1).build();

        user1 = UserInfo.builder().userId(1).username("user1").password("pswd")
                .emailId("user1@domain.com").build();

        bbook1 = BorrowedBooks.builder().book(book1).user(user1).borrowedDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(14)).returnDate(null).build();
        bbook2 = BorrowedBooks.builder().book(book2).user(user1).borrowedDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(14)).returnDate(null).build();
    }

    @Test
    public void getBooksTest() {
        List<Books> listOfBooks =  Arrays.asList(book1, book2, book3, book4);
        Mockito.when(bookService.findAll()).thenReturn(listOfBooks);
        assertEquals(bookController.getBooks().size(), 4);
        assertEquals(bookController.getBooks().get(0).getTitle(), "Book1");
    }

    @Test
    public void getBorrowedBooksTest() {
        List<BorrowedBooks> listOfBooks =  Arrays.asList(bbook1, bbook2);
        Mockito.when(borrowedBooksService.getBorrowedBooksByUser(1)).thenReturn(listOfBooks);
        assertEquals(bookController.getBooksBorrowedByUser(1).size(), 2);
        assertEquals(bookController.getBooksBorrowedByUser(1).get(0).getBook().getTitle(), "Book1");
    }
}
