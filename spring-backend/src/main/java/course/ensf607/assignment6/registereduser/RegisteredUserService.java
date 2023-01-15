package course.ensf607.assignment6.registereduser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.ensf607.assignment6.ticket.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository registeredUserRepository;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    public List<RegisteredUser> getAllRegisteredUsers() {
        return registeredUserRepository.findAll();
    }

    public void addNewUser(RegisteredUser registeredUser) {
        Optional<RegisteredUser> user = registeredUserRepository.findByEmail(registeredUser.getEmail());
        if (user.isPresent()) {
            throw new IllegalStateException("User already exists!");
        }
        registeredUserRepository.save(registeredUser);
    }

    public RegisteredUser getUserbyEmail(String email) {
        Optional<RegisteredUser> user = registeredUserRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new IllegalStateException("email does not exist");
        }

        return user.get();
    }

    public void updateRegisteredUser(RegisteredUser ru) {
        registeredUserRepository.save(ru);
    }

    public void removeNewUser(RegisteredUser registeredUser) {
        Optional<RegisteredUser> user = registeredUserRepository.findByEmail(registeredUser.getEmail());
        if (!user.isPresent()) {
            throw new IllegalStateException("User doesn't exist!");
        }
        // registeredUserRepository.deleteUserById(registeredUser.getId());
        // registeredUserRepository.deleteByEmail(registeredUser.getEmail());
        registeredUserRepository.delete(registeredUser);
    }

    public double getRegisteredUser(long id) {

        RegisteredUser updatedUser = registeredUserRepository.getReferenceById(id);
        return updatedUser.getAccountBalance();

    }

    public void setDateRegisteredBasedOnAnnualFee(long id) {
        RegisteredUser user = registeredUserRepository.findById(id).get();
        user.setDateRegistered(LocalDate.now());
        registeredUserRepository.save(user);

    }
}
