package com.spa.ecommerce.notificationService;

import com.spa.ecommerce.order.Order;
import com.spa.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
    private MockServiceEmail emailService;

    public void sendOrderConfirmationEmail(User user, Order order) {
        String subject = "Order Confirmation";
        String text = "Dear " + user.getFullName() + ",\n\nYour order has been placed successfully.\nOrder ID: " + order.getId() + "\nTotal Amount: $" + order.getAmount() + "\n\nThank you for shopping with us!";
        emailService.sendSimpleMessage(user.getEmail(), subject, text);
    }

    public void sendOrderCancellationEmail(User user, Order order) {
        String subject = "Order Cancellation";
        String text = "Dear " + user.getFullName() + ",\n\nYour order with ID " + order.getId() + " has been cancelled.\n\nThank you.";
        emailService.sendSimpleMessage(user.getEmail(), subject, text);
    }

    public void sendOrderStatusUpdateEmail(User user, Order order) {
        String subject = "Order Status Update";
        String text = "Dear " + user.getFullName() + ",\n\nYour order with ID " + order.getId() + " has been updated from  to " + order.getStatus() + ".\n\nThank you.";
        emailService.sendSimpleMessage(user.getEmail(), subject, text);
    }
}
