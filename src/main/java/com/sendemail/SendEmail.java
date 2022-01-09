package com.sendemail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    public static void SendEmail(String status, String patFirstName, String patLastName, String patLoc, ArrayList<String> detail) {

        // CODE FROM https://netcorecloud.com/tutorials/send-email-in-java-using-gmail-smtp/

        // Recipient's email ID needs to be mentioned.
        //String to = "sam.oliveira.1331@gmail.com";
        String to = "dorcheng1192@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "rpmimperial@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("rpmimperial@gmail.com", "vfcvzcljlhxlkwrz");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(status + " Status of " + patFirstName + " " + patLastName);

            // Now set the actual message
            message.setText(patFirstName + " " + patLastName + " is having " + detail + "\nPatient Location " + patLoc);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    /*
    public static void main(String[] args) throws SQLException {
        SendEmail();
    }

     */

}
