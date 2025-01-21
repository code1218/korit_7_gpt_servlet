package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/book")
public class BookRestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = new Author(1, "테스트저자");
        Publisher publisher = new Publisher(10, "테스트출판사");
        BookCategory bookCategory = new BookCategory(100, "테스트카테고리");

        Book book = Book.builder()
                .bookId(200)
                .bookName("테스트도서명")
                .isbn("abcd1234")
                .bookImgUrl("http://test.com/1234")

                .authorId(author.getAuthorId())
                .publisherId(publisher.getPublisherId())
                .categoryId(bookCategory.getCategoryId())

                .author(author)
                .publisher(publisher)
                .bookCategory(bookCategory)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);

        resp.setContentType("application/json");
        resp.getWriter().println(json);
    }

}
