package com.distribuida.web.cliente;

import com.distribuida.web.objects.Authors;
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

public class AuthorsClient {

    private static String url_app_authors = "http://distribuida.localhost/authors";

    public static List<Authors > findAll()  {
        try {
            HttpGet get = new HttpGet(url_app_authors);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(get);
            InputStream inputStream = response.getEntity().getContent();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, TypeFactory.defaultInstance().constructCollectionType(List.class, Authors.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Authors findById(Integer id) {
        try {
            HttpGet get = new HttpGet(url_app_authors + id);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(get);
            InputStream inputStream = response.getEntity().getContent();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, Authors.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(Integer id) {
        try {
            HttpDelete delete = new HttpDelete(url_app_authors + id);
            CloseableHttpClient client = HttpClients.createDefault();
            client.execute(delete);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void create(Authors authors) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url_app_authors);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(authors);
            StringEntity entity = new StringEntity(jsonString);
            entity.setContentType("application/json");
            post.setEntity(entity);
            client.execute(post);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Long id, Authors authors) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPut put = new HttpPut(url_app_authors + id);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(authors);
            StringEntity entity = new StringEntity(jsonString);
            entity.setContentType("application/json");
            put.setEntity(entity);
            client.execute(put);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


