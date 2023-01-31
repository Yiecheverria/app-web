package com.distribuida.web.cliente;

import com.distribuida.web.objects.Books;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BooksClient {

    private static String url_app_books = "http://distribuida.localhost/books";

    public static List<Books> findAll() {
        try {
            HttpGet get = new HttpGet(url_app_books);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(get);
            InputStream inputStream = response.getEntity().getContent();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, TypeFactory.defaultInstance().constructCollectionType(List.class, Books.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Books findById(Integer id) {
        try {
            HttpGet get = new HttpGet(url_app_books + id);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(get);
            InputStream inputStream = response.getEntity().getContent();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, Books.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(Integer id) {
        try {
            HttpDelete delete = new HttpDelete(url_app_books + id);
            CloseableHttpClient client = HttpClients.createDefault();
            client.execute(delete);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void create(Books books) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url_app_books);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(books);
            StringEntity entity = new StringEntity(jsonString);
            entity.setContentType("application/json");
            post.setEntity(entity);
            client.execute(post);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Long id, Books books) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPut put = new HttpPut(url_app_books + id);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(books);
            StringEntity entity = new StringEntity(jsonString);
            entity.setContentType("application/json");
            put.setEntity(entity);
            client.execute(put);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

