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
public class Roles implements Serializable {


    private Long id;

    private String nameRol;

    private boolean isActive;

}
