package com.distribuida.web.controller;


import com.distribuida.web.cliente.BooksClient;
import com.distribuida.web.config.ThymeleafUtil;

import com.distribuida.web.objects.Books;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import spark.Route;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BooksController {

    public Route init() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("index", context);
        };
    }

    public Route crudBooks() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("crudBooks", context);
        };
    }

    public Route booksList() {
        return  (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("books", BooksClient.findAll().stream()
                    .map(b -> Map.of("id", b.getId(), "isbn", b.getIsbn(), "title", b.getTitle(), "author_id", b.getAuthor_id(), "price", b.getPrice()))
                    .collect(Collectors.toList()));
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("booksList", context);
        };
    }

    public Route booksId() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("booksId", context);
        };
    }

    public Route booksIdFind() {
        return (request, response) -> {
            Integer idBook = Integer.valueOf(request.queryParams("idBooks"));
            Books book = BooksClient.findById(idBook);
            Map<String, Object> map = new HashMap<>();
            map.put("id", book.getId());
            map.put("isbn", book.getIsbn());
            map.put("title", book.getTitle());
            map.put("author_id", book.getAuthor_id());
            map.put("price", book.getPrice());
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("booksIdFind", context);
        };
    }

    public Route deleteId() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("booksDeleteId", context);
        };
    }

    public Route deleteIdFind() {
        return (request, response) -> {
            Integer idBook = Integer.valueOf(request.queryParams("idBooks"));
            BooksClient.deleteById(idBook);
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("booksDeleteIdFind", context);
        };
    }

    public Route save() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("booksSave", context);
        };
    }

    public Route saveOk() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            String isbn = request.queryParams("isbn");
            String title = request.queryParams("title");
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.queryParams("price")));
            Integer author_id = Integer.valueOf(request.queryParams("authorId"));
            Books books = new Books(isbn, title, price, author_id);
            BooksClient.create(books);
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("booksSaveOk", context);
        };
    }

    public Route update() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("booksUpdate", context);
        };
    }

    public Route updateOk() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            Integer id = Integer.valueOf(request.queryParams("id"));
            String isbn = request.queryParams("isbn");
            String title = request.queryParams("title");
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.queryParams("price")));
            Integer author_id = Integer.valueOf(request.queryParams("authorId"));
            Books books = new Books(isbn, title, price, author_id);
            BooksClient.update(Long.valueOf(id), books);
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("booksUpdateOk", context);
        };
    }

}
