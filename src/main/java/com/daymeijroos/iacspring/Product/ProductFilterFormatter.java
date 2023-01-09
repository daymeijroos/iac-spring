package com.daymeijroos.iacspring.Product;

import com.daymeijroos.iacspring.Product.ProductFilter;
import org.springframework.core.convert.converter.Converter;

public class ProductFilterFormatter implements Converter<String, ProductFilter> {
    @Override
    public ProductFilter convert(String filter) {
        return ProductFilter.valueOf(filter.toUpperCase());
    }
}
