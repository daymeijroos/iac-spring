package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.shared.DAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class LineItemDAO implements DAO<LineItem> {
    private final LineItemRepository lineItemRepository;

    @Override
    public List<LineItem> getAll() {
        return this.lineItemRepository.findAll();
    }

    @Override
    public LineItem getById(String id) throws ResourceNotFoundException {
        Optional<LineItem> result = lineItemRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("LineItem", id);
        }
    }

    @Override
    public LineItem saveToDatabase(LineItem lineItem) {
        return this.lineItemRepository.save(lineItem);
    }

    @Override
    public LineItem update(String id, LineItem lineItemRequest) throws ResourceNotFoundException {
        LineItem lineItem = this.lineItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
        lineItem.setQuantity(lineItemRequest.getQuantity());
        return lineItemRepository.save(lineItem);
    }

    @Override
    public void delete(String id) throws ResourceNotFoundException {
        lineItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
        this.lineItemRepository.deleteById(id);
    }
}
