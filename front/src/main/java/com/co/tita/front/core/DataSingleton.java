package com.co.tita.front.core;
import com.co.tita.front.dtos.TokenDto;
import com.co.tita.front.dtos.UserDto;
import com.co.tita.front.reports.UserReport;

public class DataSingleton {

    static private DataSingleton data = null;

    private DataSingleton() { }

    static public DataSingleton getDataSingleton() {

        if (data == null) {
            data = new DataSingleton();
        }
        return data;
    }

    public TokenDto getTokenDto() {
        return tokenDto;
    }

    public void setTokenDto(TokenDto tokenDto) {
        this.tokenDto = tokenDto;
    }

    public UserReport getUserDto() {
        return userDto;
    }

    public void setUserDto(UserReport userDto) {
        this.userDto = userDto;
    }

    private TokenDto tokenDto;
    private UserReport userDto;


}
