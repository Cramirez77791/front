package com.co.tita.front.reports;

import com.co.tita.front.entity.credits.Credit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReport {
    private Long id;
    private Long UserId;
    private double quantityQuotas;
    private Credit creditId;
    private String paymentDate;
    private double amountPayment;
}
