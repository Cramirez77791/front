package com.co.tita.front.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreditReport {
    private Long id;
    private Long UserId;
    private double quantityQuotas;
    private Long creditId;
    private String paymentDate;
    private double amountPayment;
}
