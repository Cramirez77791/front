package com.co.tita.front.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankUserDto {
    private Long userId;
    private Long bankId;
}
