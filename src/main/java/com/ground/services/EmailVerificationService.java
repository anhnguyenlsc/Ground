package com.ground.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

@Service
public class EmailVerificationService {
    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender emailSender;

    public Timestamp expTimeInSec = calculateExpiryDate(100);

    // Generate verification code
    private static final int CODE_LENGTH = 6;
    private static final Random RANDOM = new Random();

    public String generateCode() {
        long timestamp = System.currentTimeMillis();
        int randomNumber = RANDOM.nextInt((int) Math.pow(10, CODE_LENGTH));
        return String.format("%0" + CODE_LENGTH + "d", (timestamp + randomNumber) % (int) Math.pow(10, CODE_LENGTH));
    }

    public void emailVerification(String toEmail) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String code = generateCode();

        try {
            helper.setFrom(sender);
            helper.setTo(toEmail);
            helper.setSubject("[GROUND EMAIL VERIFICATION]");
            helper.setText("Thank you for your registration. This is your verification code: <strong>" + code
                    + "</strong> <br>Hope you enjoy your time with us.", true);
            emailSender.send(message);

        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setExpTime(boolean isSetExpTime) {
        if (isSetExpTime)
        {
            expTimeInSec = calculateExpiryDate(100);
        }
        else
        {
            System.out.println("Error set expiry time!!!!!!");
        }
    }

    private Timestamp calculateExpiryDate(int expiryTimeInSecs) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, expiryTimeInSecs);
        return new Timestamp(cal.getTime().getTime());
    }
}
