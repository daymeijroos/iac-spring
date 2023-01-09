package com.daymeijroos.iacspring.dao;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.model.Admin;
import com.daymeijroos.iacspring.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class AdminDAO implements DAO<Admin>{
    private final AdminRepository adminRepository;

    @Override
    public List<Admin> getAll() {
        return this.adminRepository.findAll();
    }

    @Override
    public Admin getById(String id) throws ResourceNotFoundException {
        Optional<Admin> result = this.adminRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Admin", id);
        }
    }

    public Admin getByUserId(String userId) throws ResourceNotFoundException {
        Optional<Admin> result = this.adminRepository.findByUserId(userId);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Admin", userId);
        }
    }

    @Override
    public Admin saveToDatabase(Admin admin) {
        return this.adminRepository.save(admin);
    }

    @Override
    public Admin update(String id, Admin adminRequest) throws ResourceNotFoundException {
        Admin admin = this.adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", id));
        admin.setUserId(adminRequest.getUserId());
        return adminRepository.save(admin);
    }

    @Override
    public void delete(String id) throws ResourceNotFoundException {
        this.adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", id));
        this.adminRepository.deleteById(id);
    }
}
