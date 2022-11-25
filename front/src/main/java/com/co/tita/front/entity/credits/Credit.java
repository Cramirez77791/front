package com.co.tita.front.entity.credits;


import com.co.tita.front.entity.bank.Bank;
import com.co.tita.front.entity.payments.Payment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credit implements Serializable {

    private Long id;
    private double amount;
    private double quotaQuantity;
    private double quotaAmount;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date creditDate;
    private Bank bankId;
    private Long userId;
    private List<Payment> payments;

}
