package com.company.data_rest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OutputProductDto {
    private Double price;
    private Double amount;
    private Integer productId;
    private Integer outputId;
}
