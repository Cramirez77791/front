package com.co.tita.front.entity.bank;

import com.co.tita.front.entity.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BanksUsers {

    private Long id;
    private User idUser;
    private Bank idBank;

}
