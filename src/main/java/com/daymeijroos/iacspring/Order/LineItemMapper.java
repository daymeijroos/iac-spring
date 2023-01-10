package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.Product.ProductDAO;
import com.daymeijroos.iacspring.Product.ProductMapper;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.shared.Mapper;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LineItemMapper implements Mapper<LineItem, LineItemDTO> {
    private final ProductMapper productMapper;
    private final ProductDAO productDAO;

    @Autowired
    public LineItemMapper(ProductDAO productDAO, ProductMapper productMapper) {
        this.productDAO = productDAO;
        this.productMapper = productMapper;
    }

    public LineItem fromDTOToEntity(@Nonnull LineItemDTO lineItemDTO) throws ResourceNotFoundException {
        LineItem lineItem = new LineItem();
        lineItem.setProduct(productDAO.getById(lineItemDTO.getProduct().getId()));
        lineItem.setQuantity(lineItemDTO.getQuantity());
        return lineItem;
    }

    public LineItemDTO fromEntityToDTO(@Nonnull LineItem lineItem) {
        LineItemDTO lineItemDTO = new LineItemDTO();
        lineItemDTO.setProduct(productMapper.fromEntityToDTO(lineItem.getProduct()));
        lineItemDTO.setQuantity(lineItem.getQuantity());
        return lineItemDTO;
    }
}
