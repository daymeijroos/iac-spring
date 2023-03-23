package com.daymeijroos.iacspring.Admin;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminDAO adminDAO;

    @Autowired
    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public Boolean checkForUserId(String userId) {
        try {
            adminDAO.getByUserId(userId);
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }
}
