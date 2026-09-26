package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public Admin createAdmin(String name, String email) {

        Admin admin = new Admin();

        admin.setName(name);
        admin.setEmail(email);

        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Admin updateAdmin(Long id, String name, String email) {

        Admin admin = adminRepository.findById(id).orElseThrow();

        admin.setName(name);
        admin.setEmail(email);

        return adminRepository.save(admin);
    }

    @Transactional
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}