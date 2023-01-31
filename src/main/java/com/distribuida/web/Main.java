package com.distribuida.web;

import com.distribuida.web.controller.AuthorsController;
import com.distribuida.web.controller.BooksController;
import spark.Spark;

public class Main {
    public static void main(String[] args) {
        BooksController booksController = new BooksController();
        Spark.get("/", booksController.init());
        Spark.get("/crudBooks", booksController.crudBooks());
        Spark.get("/booksList", booksController.booksList());
        Spark.get("/booksId", booksController.booksId());
        Spark.get("/booksIdFind", booksController.booksIdFind());
        Spark.get("/booksDeleteId", booksController.deleteId());
        Spark.get("/booksDeleteIdFind", booksController.deleteIdFind());
        Spark.get("/booksSave", booksController.save());
        Spark.get("/booksSaveOk", booksController.saveOk());
        Spark.get("/booksUpdate", booksController.update());
        Spark.get("/booksUpdateOk", booksController.updateOk());

        AuthorsController authorsController = new AuthorsController();
        Spark.get("/crudAuthors", authorsController.crudAuthors());
        Spark.get("/authorsList", authorsController.authorsList());
        Spark.get("/authorsId", authorsController.authorsId());
        Spark.get("/authorsIdFind", authorsController.authorsIdFind());
        Spark.get("/authorsDeleteId", authorsController.deleteId());
        Spark.get("/authorsDeleteIdFind", authorsController.deleteIdFind());
        Spark.get("/authorsSave", authorsController.save());
        Spark.get("/authorsSaveOk", authorsController.saveOk());
        Spark.get("/authorsUpdate", authorsController.update());
        Spark.get("/authorsUpdateOk", authorsController.updateOk());
    }
}
