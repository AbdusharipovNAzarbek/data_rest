package com.company.data_rest.projection;

import com.company.data_rest.entity.Measure;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Measure.class)
public interface MeasureProjection {
    Integer getId();

    String getName();

    boolean getActive();
}
