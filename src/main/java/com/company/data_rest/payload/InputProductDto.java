package com.company.data_rest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDto {
    @NotNull
    private Date expireDate;
    @NotNull
    private Double price;
    @NotNull
    private Double amount;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer inputId;

}
