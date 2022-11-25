package com.co.tita.front.service;

import com.co.tita.front.core.DataSingleton;
import com.co.tita.front.dtos.PaymentDto;
import com.co.tita.front.entity.payments.Payment;
import com.co.tita.front.reports.CreditReport;
import com.co.tita.front.reports.PaymentReport;
import com.co.tita.front.reports.ResponseReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.co.tita.front.utils.Constants.TOKEN;

@Service
public class PaymentService {

    @Value("${api.base.url}")
    private String baseUrl;
    private DataSingleton dataSingleton = DataSingleton.getDataSingleton();

    @Autowired
    private ObjectMapper objectMapper;

    public void createPayment(PaymentDto payment) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String json = objectMapper.writeValueAsString(payment);
        HttpEntity<PaymentDto> request = new HttpEntity(json);
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

        ResponseEntity responserest = restTemplate.postForEntity(baseUrl+"payments/create", request, PaymentReport.class);

    }


}
