package course.ensf607.assignment6.admin;

import course.ensf607.assignment6.registereduser.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdminUsers() {
        return adminRepository.findAll();
    }

    public String addAdminStaff(Admin staff) {
        Optional<Admin> existStaff = adminRepository.findByEmail(staff.getEmail());
        if (existStaff.isPresent()) {
            return "That E-Mail already exists!";
        }
        adminRepository.save(staff);
        return "Staff added.";
    }

    public String removeAdminStaff(Admin staff) {
        Optional<Admin> existStaff = adminRepository.findByEmail(staff.getEmail());
        if (!existStaff.isPresent()) {
            return "Staff doesn't exist!";
        }
        adminRepository.deleteById(existStaff.get().getId());
        return "Staff removed.";
    }

    public Admin getAdminUserByEmail(String email) {
        Optional<Admin> staff = adminRepository.findByEmail(email);
        if (!staff.isPresent()) {
            throw new IllegalStateException("ID doesn't exist");
        }
        return staff.get();
    }

}