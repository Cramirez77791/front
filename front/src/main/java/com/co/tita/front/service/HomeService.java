package com.co.tita.front.service;

import com.co.tita.front.core.DataSingleton;
import com.co.tita.front.dtos.TokenDto;
import com.co.tita.front.dtos.UserDto;
import com.co.tita.front.reports.UserReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

import static com.co.tita.front.utils.Constants.TOKEN;

@Service
public class HomeService {
    @Value("${api.base.url}")
    private String baseUrl;

    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;

    public boolean Init(String username,String pass) throws IOException {
      TokenDto auth = logIn(username,pass);
        DataSingleton dataSingleton = DataSingleton.getDataSingleton();
       if(null != auth) {
           dataSingleton.setTokenDto(auth);
         UserReport userReport = userService.getUserByUserName(username);
         dataSingleton.setUserDto(userReport);
         return true;
       }
       return false;
    }


    public TokenDto logIn(String username, String pass) throws IOException {
      TokenDto tokenDto = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI(baseUrl+"login"))
                    .addParameter("userName", username)
                    .addParameter("passWord", pass)
                    .build();

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
                   return tokenDto;
                }
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                tokenDto = gson.fromJson(content, TokenDto.class);

            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                response.close();
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpclient.close();
        }
        return tokenDto;
    }


}
