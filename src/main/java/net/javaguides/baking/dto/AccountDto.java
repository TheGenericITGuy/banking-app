package net.javaguides.baking.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
}
