package com.distribuida.web.controller;



import com.distribuida.web.cliente.AuthorsClient;
import com.distribuida.web.config.ThymeleafUtil;
import com.distribuida.web.objects.Authors;

import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import spark.Route;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthorsController {


    public Route crudAuthors() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("crudAuthors", context);
        };
    }

    public Route authorsList() {
        return  (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("authors", AuthorsClient.findAll().stream()
                    .map(b -> Map.of("id", b.getId(), "firstName", b.getFirstName(), "lastName", b.getLastName()))
                    .collect(Collectors.toList()));
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("authorsList", context);
        };
    }

    public Route authorsId() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("authorsId", context);
        };
    }

    public Route authorsIdFind() {
        return (request, response) -> {
            Integer idAuthors = Integer.valueOf(request.queryParams("idAuthors"));
            Authors authors = AuthorsClient.findById(idAuthors);
            Map<String, Object> map = new HashMap<>();
            map.put("id", authors.getId());
            map.put("firstName", authors.getFirstName());
            map.put("lastName", authors.getLastName());
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("authorsIdFind", context);
        };
    }

    public Route deleteId() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("authorsDeleteId", context);
        };
    }

    public Route deleteIdFind() {
        return (request, response) -> {
            Integer idAuthors = Integer.valueOf(request.queryParams("idAuthors"));
            AuthorsClient.deleteById(idAuthors);
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("authorsDeleteIdFind", context);
        };
    }

    public Route save() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("authorsSave", context);
        };
    }

    public Route saveOk() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            Authors authors = new Authors(firstName, lastName);
            AuthorsClient.create(authors);
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("authorsSaveOk", context);
        };
    }

    public Route update() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("authorsUpdate", context);
        };
    }

    public Route updateOk() {
        return (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            Integer id = Integer.valueOf(request.queryParams("id"));
            String firstName = request.queryParams("firstName");
            String lastName = request.queryParams("lastName");
            Authors authors = new Authors(Long.valueOf(id), firstName, lastName);
            AuthorsClient.update(Long.valueOf(id), authors);
            IContext context = new Context(request.raw().getLocale(), map);
            return ThymeleafUtil.getTemplateEngine().process("authorsUpdateOk", context);
        };
    }


}