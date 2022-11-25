package com.co.tita.front.reports;


import com.co.tita.front.entity.bank.Bank;
import com.co.tita.front.entity.users.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BanksUsersReport implements Serializable {
    private Long id;
    private User idUser;
    private Bank idBank;
    private String message;
}
