package com.company.data_rest.projection;

import com.company.data_rest.entity.Category;
import com.company.data_rest.entity.Client;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Client.class)
public interface ClientProjection {
    Integer getId();

    String getName();

    String getPhone_number();
}
