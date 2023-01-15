package course.ensf607.assignment6.registereduser;

// import course.ensf607.assignment6.student.Student;
// import course.ensf607.assignment6.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import course.ensf607.assignment6.ticket.Ticket;
import course.ensf607.assignment6.ticket.TicketService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/registereduser/")
public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;
    private final TicketService ticketService;

    @Autowired
    public RegisteredUserController(RegisteredUserService registeredUserService, TicketService ticketService) {
        this.registeredUserService = registeredUserService;
        this.ticketService = ticketService;
    }

    @GetMapping("all")
    public List<RegisteredUser> getAllUsers() {
        return registeredUserService.getAllRegisteredUsers();
    }

    @GetMapping("getUser/{id}")
    public double getUser(@PathVariable long id) {
        return registeredUserService.getRegisteredUser(id);
    }

    @PostMapping("add")
    public void addNewUser(@RequestBody RegisteredUser registeredUser) {
        registeredUserService.addNewUser(registeredUser);
    }

    @GetMapping("{email}")
    public RegisteredUser getByEmail(@PathVariable String email) {
        return registeredUserService.getUserbyEmail(email);
    }

    @GetMapping("tickets{email}")
    public Set<Ticket> getTickets(@PathVariable String email) {
        RegisteredUser ru = registeredUserService.getUserbyEmail(email);
        Set<Ticket> tickets = ru.getTickets();
        return tickets;
    }

    @PutMapping("{ticketid}/add/{email}")
    public RegisteredUser enrollAddTicketToRu(@PathVariable Long ticketid,
            @PathVariable String email) {
        Ticket ticket = ticketService.FindById(ticketid);
        RegisteredUser ru = registeredUserService.getUserbyEmail(email);
        ru.addTickets(ticket);
        // ticketService.updateTicket(ticket);
        registeredUserService.updateRegisteredUser(ru);
        return ru;
    }

    @PutMapping("{id}/setDate")
    public void setNewDateBasedOnAnnualPayment(@PathVariable Long id) {
        registeredUserService.setDateRegisteredBasedOnAnnualFee(id);
    }
}
