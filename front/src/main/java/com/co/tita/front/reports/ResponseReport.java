package com.co.tita.front.reports;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseReport<T> {

    private T entity;
    private String message;

}
