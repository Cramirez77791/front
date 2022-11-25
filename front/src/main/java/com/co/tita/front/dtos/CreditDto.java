package com.co.tita.front.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditDto {
    private double amount;
    private double quotaQuantity;
    private double quotaAmount;
    private String creditDate;
    private Long bankId;
    private Long userId;
}
