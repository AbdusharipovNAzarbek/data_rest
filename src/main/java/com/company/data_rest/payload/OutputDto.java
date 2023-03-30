package com.company.data_rest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OutputDto {
    private Date date;
    private String facture_number;
    private Integer code;
    private Integer clientId;
    private Integer warehouseId;
    private Integer currencyId;
}
