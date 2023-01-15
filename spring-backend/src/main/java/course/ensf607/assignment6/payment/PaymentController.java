package course.ensf607.assignment6.payment;

import java.util.Scanner;

import course.ensf607.assignment6.movie.Movie;
import course.ensf607.assignment6.registereduser.RegisteredUser;
import course.ensf607.assignment6.showtime.Showtime;
import course.ensf607.assignment6.theatre.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/payment")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/confirmPayment/{amount}")
    public ResponseEntity<String> confirmPayment(@PathVariable double amount) {
        paymentService.confirmPayment(amount);
        return ResponseEntity.ok("Confirmed for Payment.");

    }

    @PutMapping("/addPayment/{userId}/{price}")
    public ResponseEntity<String> registerNewPayment(@RequestBody Payment payment, @PathVariable long userId,
            @PathVariable double price) {
        paymentService.addPayment(payment, userId, price);
        return ResponseEntity.ok("Payment added.");
    }

    @PutMapping("/addRefundPayment/{refundAmount}/{userId}")
    public ResponseEntity<String> refundPayment(@PathVariable long userId, @PathVariable double refundAmount) {
        paymentService.createRefundPayment(userId, refundAmount);
        return ResponseEntity.ok("Refunded");
    }

    @PutMapping("/calculateRefundMultiplier/{userId}")
    public double calculateRefundAmount(@PathVariable long userId) {
        double refundMult = paymentService.calculateRefundMultiplier(userId);
        return refundMult;

    }

    @PostMapping("annualPayment")
    public void checkAnnualPayments() {
        paymentService.checkAnnualPayments();
    }

    /*
     * public void sendPayment(double amount){
     * requestInformation(amount);
     * confirmPayment(amount);
     * }
     * 
     * private void requestInformation(double amount){
     * Scanner object = new Scanner(System.in);
     * System.out.println("Please enter your name as it appear on your card: ");
     * String name = object.nextLine();
     * 
     * System.out.println("Please enter your card number: ");
     * int number = object.nextInt();
     * 
     * System.out.println("Please enter the ccv number on the back of your card: ");
     * int ccv = object.nextInt();
     * 
     * object.close();
     * 
     * payment = new Payment(name,number,ccv,amount);
     * }
     * 
     * public void sendPayment(RegisteredUser user, double amount){
     * payment = new Payment(user, amount);
     * confirmPayment(amount);
     * }
     * 
     * public void sendPayment(RegisteredUser user){
     * payment = new Payment(user);
     * confirmPayment(20);
     * }
     * 
     * public Boolean verifyInformation(double amount){
     * Boolean success = false;
     * Optional<Payment> paymentFound =
     * paymentRepository.findAccount(payment.getNumber());
     * if (paymentFound.isPresent()) {
     * if (paymentFound.get().getAccountBalance() >= amount){
     * payment.setAccountBalance(paymentFound.get().getAccountBalance()-amount);
     * success = true;
     * }
     * }
     * return success;
     * }
     * public void confirmPayment(double amount){
     * if (verifyInformation(amount) == false)
     * {
     * System.out.println("Invalid Account, please try again");
     * payment = null;
     * }
     * else{
     * System.out.println("Payment successfull, email reciept");
     * }
     * }
     * 
     */

}
