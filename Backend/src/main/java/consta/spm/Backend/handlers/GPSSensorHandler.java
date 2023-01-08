package consta.spm.Backend.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static consta.spm.Backend.configuration.AppConfig.END_COORDINATES;
import static consta.spm.Backend.configuration.AppConfig.SOS_COORDINATES;
import static consta.spm.Backend.configuration.AppConfig.START_COORDINATES;

public class GPSSensorHandler {

    private static final Logger LOGGER = LogManager.getLogger(GPSSensorHandler.class);

    private static GPSSensorHandler sensor;

    private final List<String> route = new ArrayList<>();
    private LocalDateTime lastCoordinatesDateTime = LocalDateTime.now();

    private int sosCounter = 0;


    public static GPSSensorHandler getInstance() {
        if (sensor == null) {
            sensor = new GPSSensorHandler();
        }
        return sensor;
    }

    public void handleGPSSensorData(String coordinates) {
        switch (coordinates) {
            case START_COORDINATES:
                LOGGER.info("Starting Route");
                break;
            case END_COORDINATES:
                LOGGER.info("Arrived at destination");
                break;
            case SOS_COORDINATES:
                sosCounter++;
                LOGGER.info("SOS counter: {} out of 10", sosCounter);
                if (sosCounter == 10) {
                    sendSOSMail();
                }
                break;
            default:
                addToRoute(coordinates);
                sosCounter = 0;
        }

    }

    private void addToRoute(String coordinates) {
        lastCoordinatesDateTime = LocalDateTime.now();
        route.add(coordinates);
    }

    private void sendSOSMail() {
        String to = "andrei24constantin@gmail.com";
        String from = "fromaddress4@gmail.com";
        String from_passwd = "xbgjrwoikvyjtnwx";
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, from_passwd);
            }
        });

        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Car requires URGENT assistance!");
            message.setContent("<p>Hello,</p><p>Your car requires urgent assistance.</p><p>Last known location:"
                    + route.get(route.size() - 1) + "</p><p>Last report received at: " + lastCoordinatesDateTime
                    + "</p><p>Kind regards,</p><p>Car</p>", "text/html");

            Transport.send(message);
        } catch (MessagingException mex) {
            LOGGER.error(mex.getMessage());
        }
    }
}
