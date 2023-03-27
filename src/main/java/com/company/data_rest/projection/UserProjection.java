package com.company.data_rest.projection;

import com.company.data_rest.entity.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = User.class)
public interface UserProjection {
    Integer getId();

    String getFirst_name();

    String getLast_name();

    String getPhone_number();


}
