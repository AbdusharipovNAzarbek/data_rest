package com.company.data_rest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {
    private String name;
    private String code;
    private Integer categoryId;
    private Integer attachmentId;
    private Integer measureId;
    private boolean active;
}
