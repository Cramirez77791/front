package com.co.tita.front.service;

import com.co.tita.front.core.DataSingleton;
import com.co.tita.front.reports.UserReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.Arrays;

import static com.co.tita.front.utils.Constants.TOKEN;

@Service
public class UserService {

    @Value("${api.base.url}")
    private String baseUrl;

    DataSingleton dataSingleton = DataSingleton.getDataSingleton();

    public UserReport getUserByUserName(String userName)  {
        UserReport userReport = new UserReport();

        RestTemplate restTemplate = new RestTemplate();

            restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                request.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                request.getHeaders().add(TOKEN,dataSingleton.getTokenDto().getAuthorization());
                return execution.execute(request, body);
            }
        });

            ResponseEntity<UserReport> responserest = restTemplate.getForEntity(baseUrl+"users/get?username="+userName,UserReport.class);
            userReport = responserest.getBody();

        return userReport;
    }






}
