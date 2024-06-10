package com.iroxit.front.end.desktop.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpService {

    private static final String URL = "http://localhost:8081/api/";
    private String service;
    private ObjectMapper mapper;

    HttpClient client = HttpClient.newHttpClient();

    public List<Map<String, Object>> obtenerVentas() throws IOException, InterruptedException {
        service = "ventas";
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + service))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            mapper = new ObjectMapper();
            return mapper.readValue(response.body(), new TypeReference<List<Map<String, Object>>>() {       
            });
        } else {
            throw new IOException("Error: " + response.statusCode());
        }

    }
    
        public List<Map<String, Object>> obtenerProductos() throws IOException, InterruptedException {
        service = "productos";
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + service))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            mapper = new ObjectMapper();
            return mapper.readValue(response.body(), new TypeReference<List<Map<String, Object>>>() {           
            });
        } else {
            throw new IOException("Error: " + response.statusCode());
        }

    }

    public List<Map<String, Object>> obtenerVentasGlobales() throws IOException, InterruptedException {
        service = "ventas/findReport";
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + service))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            mapper = new ObjectMapper();
            return mapper.readValue(response.body(), new TypeReference<List<Map<String, Object>>>() {
            });
        } else {
            throw new IOException("Error: " + response.statusCode());
        }
    }

    public List<String> obtieneExistencias() throws IOException, InterruptedException {
        List<String> existencias = new ArrayList<>();
        service = "productos/existencias";
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + service))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            String[] mensajes = responseBody.split("\n");
            for (String mensaje : mensajes) {
                existencias.add(mensaje);
            }
        } else {
            throw new IOException("Error: " + response.statusCode());
        }
        return existencias;
    }
}
