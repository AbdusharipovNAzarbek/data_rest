package com.company.data_rest.projection;

import com.company.data_rest.entity.Supplier;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Supplier.class)
public interface SupplierProjection {
    Integer getId();

    String getName();

    String getPhone_number();

    boolean getActive();
}
