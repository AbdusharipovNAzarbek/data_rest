package com.company.data_rest.projection;

import com.company.data_rest.entity.Currency;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Currency.class)
public interface CurrencyProjection {
    Integer getId();
    String getName();
    boolean getActive();
}
