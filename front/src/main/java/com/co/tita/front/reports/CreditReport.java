package com.co.tita.front.reports;

import com.co.tita.front.entity.bank.Bank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditReport {
    private Long id;
    private double amount;
    private double quotaQuantity;
    private double quotaAmount;
    private String creditDate;
    private Bank bankId;
    private Long userId;

}
