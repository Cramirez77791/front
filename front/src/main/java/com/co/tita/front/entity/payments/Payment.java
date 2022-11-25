package com.co.tita.front.entity.payments;

import com.co.tita.front.entity.credits.Credit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {

    private Long id;

    private Long UserId;

    private double quantityQuotas;

    private Credit creditId;

    private Date paymentDate;

    private double amountPayment;


}
