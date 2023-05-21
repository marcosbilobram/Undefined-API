package br.com.undefined.api.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ApiRequestServiceClient {

    private final RestTemplate restTemplate;

    //Esse client será responsável pela conexão com o micro serviço(a ser criado) de requisições e
    //este método é responsável pelo retorno das requisições.
    public ApiRequestServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> void sendRequestResponse(List<T> request) {

        ResponseEntity<Boolean> response = restTemplate.postForEntity(
                "localhost:7579/undefined/resquestService/api/resp",
                request,
                Boolean.class);
    }

}
