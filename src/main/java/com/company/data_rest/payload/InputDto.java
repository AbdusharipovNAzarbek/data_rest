package com.company.data_rest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InputDto {
    @NotNull
    private String facture_number;
    @NotNull
    private Integer code;
    @NotNull
    private Integer supplierId;
    @NotNull
    private Integer warehouseId;
    @NotNull
    private Integer currencyId;
}
