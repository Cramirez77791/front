package com.co.tita.front.service;

import com.co.tita.front.core.DataSingleton;
import com.co.tita.front.entity.bank.BanksUsers;
import com.co.tita.front.reports.ResponseReport;
import com.co.tita.front.reports.UserReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.co.tita.front.utils.Constants.TOKEN;

@Service
public class BanksService {

    @Value("${api.base.url}")
    private String baseUrl;
    private DataSingleton dataSingleton = DataSingleton.getDataSingleton();

    public List<BanksUsers> getBankByUserId(Long userid)  {
        List<BanksUsers> banksUsersList = new ArrayList<>();

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

        ResponseEntity<ResponseReport> responserest = restTemplate.getForEntity(baseUrl+"bank/get.all?userid="+userid, ResponseReport.class);
        ResponseReport<List<BanksUsers>> responseReport = responserest.getBody();
        banksUsersList =responseReport.getEntity();
        return banksUsersList;
    }
}
