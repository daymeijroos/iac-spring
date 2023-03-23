package com.daymeijroos.iacspring.ShippingDetails;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingDetailsService {
    private final ShippingDetailsDAO shippingDetailsDAO;
    private final ShippingDetailsMapper shippingDetailsMapper;

    @Autowired
    public ShippingDetailsService(ShippingDetailsDAO shippingDetailsDAO, ShippingDetailsMapper shippingDetailsMapper) {
        this.shippingDetailsDAO = shippingDetailsDAO;
        this.shippingDetailsMapper = shippingDetailsMapper;
    }

    public List<ShippingDetailsDTO> get() {
        return shippingDetailsDAO.getAll().stream().map(shippingDetailsMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<ShippingDetailsDTO> getById(String id) throws ResourceNotFoundException {
        List<ShippingDetailsDTO> shippingDetailsList = new ArrayList<>();
        ShippingDetails shippingDetails = shippingDetailsDAO.getById(id);
        shippingDetailsList.add(shippingDetailsMapper.fromEntityToDTO(shippingDetails));
        return shippingDetailsList;
    }

    public ShippingDetailsDTO getByUserId(String id) throws ResourceNotFoundException {
        ShippingDetails shippingDetails = shippingDetailsDAO.getByUserId(id);
        return shippingDetailsMapper.fromEntityToDTO(shippingDetails);
    }

    public ShippingDetailsDTO post(ShippingDetailsDTO shippingDetailsDTO) {
        ShippingDetails shippingDetails = shippingDetailsMapper.fromDTOToEntity(shippingDetailsDTO);
        ShippingDetails savedShippingDetails = shippingDetailsDAO.saveToDatabase(shippingDetails);
        return shippingDetailsMapper.fromEntityToDTO(savedShippingDetails);
    }

    public ShippingDetailsDTO update(ShippingDetailsDTO shippingDetailsDTO) throws ResourceNotFoundException {
        ShippingDetails ShippingDetails = shippingDetailsMapper.fromDTOToEntity(shippingDetailsDTO);
        ShippingDetails updatedShippingDetails = shippingDetailsDAO.update(ShippingDetails.getId(), ShippingDetails);
        return shippingDetailsMapper.fromEntityToDTO(updatedShippingDetails);
    }

    public ShippingDetailsDTO patch(ShippingDetailsDTO shippingDetailsDTO) throws ResourceNotFoundException {
        ShippingDetails shippingDetails = shippingDetailsMapper.fromDTOToEntity(shippingDetailsDTO);
        ShippingDetails updatedShippingDetails = shippingDetailsDAO.update(shippingDetails.getId(), shippingDetails);
        return shippingDetailsMapper.fromEntityToDTO(updatedShippingDetails);
    }

    public void delete(String id) throws ResourceNotFoundException {
        shippingDetailsDAO.delete(id);
    }
}
