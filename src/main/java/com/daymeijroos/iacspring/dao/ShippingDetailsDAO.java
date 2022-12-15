package com.daymeijroos.iacspring.dao;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.model.ShippingDetails;
import com.daymeijroos.iacspring.repository.ShippingDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ShippingDetailsDAO implements DAO<ShippingDetails>{
    private final ShippingDetailsRepository shippingDetailsRepository;

    @Override
    public List<ShippingDetails> getAll() {
        return this.shippingDetailsRepository.findAll();
    }

    public ShippingDetails getByUserId(String id) throws ResourceNotFoundException {
        Optional<ShippingDetails> result = shippingDetailsRepository.findByUserId(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("ShippingDetails", id);
        }
    }

    @Override
    public ShippingDetails getById(String id) throws ResourceNotFoundException {
        Optional<ShippingDetails> result = shippingDetailsRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("ShippingDetails", id);
        }
    }

    @Override
    public ShippingDetails saveToDatabase(ShippingDetails shippingDetails) {
        return this.shippingDetailsRepository.save(shippingDetails);
    }

    @Override
    public ShippingDetails update(String id, ShippingDetails shippingDetailsRequest) throws ResourceNotFoundException {
        ShippingDetails shippingDetails = this.shippingDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShippingDetails", id));
        shippingDetails.setFirstName(shippingDetailsRequest.getFirstName());
        shippingDetails.setLastName(shippingDetailsRequest.getLastName());
        shippingDetails.setPhone(shippingDetailsRequest.getPhone());
        shippingDetails.setCountry(shippingDetailsRequest.getCountry());
        shippingDetails.setCity(shippingDetailsRequest.getCity());
        shippingDetails.setPostalCode(shippingDetailsRequest.getPostalCode());
        shippingDetails.setAddress(shippingDetailsRequest.getAddress());

        return shippingDetailsRepository.save(shippingDetails);
    }

    @Override
    public void delete(String id) throws ResourceNotFoundException {
        shippingDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShippingDetails", id));
        this.shippingDetailsRepository.deleteById(id);
    }
}
