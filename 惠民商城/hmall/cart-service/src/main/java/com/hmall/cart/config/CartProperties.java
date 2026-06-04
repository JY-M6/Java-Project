package com.hmall.cart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "hm.cart")
public class CartProperties {
    private Integer maxItems;
}
