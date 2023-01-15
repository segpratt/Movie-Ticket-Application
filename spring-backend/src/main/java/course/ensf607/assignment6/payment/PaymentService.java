package course.ensf607.assignment6.payment;

import course.ensf607.assignment6.registereduser.RegisteredUser;
import course.ensf607.assignment6.registereduser.RegisteredUserRepository;
import course.ensf607.assignment6.ticket.Ticket;
import course.ensf607.assignment6.ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RegisteredUserRepository registeredUserRepository;

    private final TicketRepository ticketRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, RegisteredUserRepository registeredUserRepository,
            TicketRepository ticketRepository) {
        this.paymentRepository = paymentRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.ticketRepository = ticketRepository;
    }

    public void updatePayment(Payment payment) {

        paymentRepository.save(payment);
    }

    public void addPayment(Payment payment, long userId, double totalPrice) {
        Optional<Payment> paymentByName = paymentRepository.findPaymentBypId(payment.getId());
        if (paymentByName.isPresent()) {
            throw new IllegalStateException("Payment already exist!");
        }
        RegisteredUser user = registeredUserRepository.getReferenceById(userId);
        payment.setType("Ticket Purchase");
        payment.setCreationDate(LocalDate.now());
        payment.setUser(user);
        payment.setAccountBalance(user.getAccountBalance() - totalPrice);
        user.setAccountBalance(user.getAccountBalance() - totalPrice);
        paymentRepository.save(payment);
    }

    public void checkAnnualPayments() {
        List<RegisteredUser> users = registeredUserRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            LocalDate tempdate = users.get(i).getDateRegistered().plusYears(1);
            if (tempdate.isAfter(LocalDate.now())) {
                Payment pay = new Payment(users.get(i));
                addPayment(pay, users.get(i).getId(), 20);
                users.get(i).setDateRegistered(LocalDate.now());
            }
        }
    }

    public void confirmPayment(double amount) {
    }

    public void createRefundPayment(long userId, double refundAmount) {
        Optional<RegisteredUser> userCheck = registeredUserRepository.findById(userId);
        if (userCheck.isPresent() == false) {
            throw new IllegalStateException("There is no user with that id");
        }

        RegisteredUser user = registeredUserRepository.getReferenceById(userId);

        double newBalance = user.getAccountBalance() + refundAmount;
        user.setAccountBalance(newBalance);
        Payment refundPayment = new Payment(user);
        refundPayment.setType("Refund");
        refundPayment.setUser(user);
        paymentRepository.save(refundPayment);

    }

    public double calculateRefundMultiplier(long userId) {
        Optional<RegisteredUser> userCheck = registeredUserRepository.findById(userId);
        if (userCheck.isPresent() == false) {
            throw new IllegalStateException("There is no user with that id");
        }
        double refundMult;
        List<RegisteredUser> user = (List<RegisteredUser>) registeredUserRepository.getReferenceById(userId);

        if (user.get(0).getName() == "guest")
            refundMult = 0.85;

        else
            refundMult = 1;

        return refundMult;
    }
}