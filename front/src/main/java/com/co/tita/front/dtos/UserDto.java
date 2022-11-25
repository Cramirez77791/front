package com.co.tita.front.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String userName;
    private String passWord;
    private String fullName;
    private boolean isActive;
}
