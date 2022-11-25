package com.co.tita.front.service;

import com.co.tita.front.core.DataSingleton;
import com.co.tita.front.reports.CreditDetailReport;
import com.co.tita.front.reports.CreditReport;
import com.co.tita.front.reports.ResponseReport;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.co.tita.front.utils.Constants.TOKEN;

@Service
public class CreditsService {

    @Value("${api.base.url}")
    private String baseUrl;
    private DataSingleton dataSingleton = DataSingleton.getDataSingleton();

    @Autowired
    private ObjectMapper objectMapper;
    public List<CreditReport> getCreditsByUserId(Long bankid,Long userid)  {
        List<CreditReport> creditsList = new ArrayList<>();

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

        ResponseEntity<ResponseReport> responserest = restTemplate.getForEntity(baseUrl+"credits/get.all?bankid="+bankid+"&userid="+userid, ResponseReport.class);
        ResponseReport<List<CreditReport>> responseReport = responserest.getBody();
        creditsList =responseReport.getEntity();
        return creditsList;
    }


    public CreditDetailReport getCreditDetailById(Long crediId) {


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

        ResponseEntity<CreditDetailReport> responserest = restTemplate.getForEntity(baseUrl+"credits/get.details?creditid="+crediId, CreditDetailReport.class);
        CreditDetailReport responseReport = responserest.getBody();
        return responseReport;
    }

    public CreditReport getCreditById(Long crediId)  {
        CreditReport creditReport = new CreditReport();

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

        ResponseEntity<CreditReport> responserest = restTemplate.getForEntity(baseUrl+"credits/get?creditid="+crediId, CreditReport.class);
        creditReport =responserest.getBody();
        return creditReport;
    }



}
