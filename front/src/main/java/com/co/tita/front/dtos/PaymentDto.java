package com.co.tita.front.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long UserId;
    private double quantityQuotas;
    private Long creditId;
    private Date paymentDate;
    private double amountPayment;
}
