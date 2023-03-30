package com.company.data_rest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.IntegralDataTypeHolder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WarehouseDto {
    private String name;
    private Integer userId;
}
