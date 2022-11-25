package com.co.tita.front.entity.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles implements Serializable {

    private Long id;
    private User idUser;
    private Roles idRol;


}
