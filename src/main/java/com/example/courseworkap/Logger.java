package com.example.courseworkap;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Logger {
    private static final StringBuilder typicalAct = new StringBuilder();
    private static final StringBuilder criticalMistake = new StringBuilder();
    private static final String to = "fasadgiven@gmail.com";
    private static final String from = "liubomyr.antonyk.knm.2020@lpnu.ua";
    private static final String host = "smtp.gmail.com";

    public static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        try(FileInputStream fis = new FileInputStream("app.properties")){
            props.load(fis);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        String password = props.getProperty("password");

        return Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
    }

    public static void saveLogs(){
        try(FileWriter fw = new FileWriter("savedLogs.txt")) {
            fw.write(typicalAct.toString());
        } catch (Exception e) {
            System.out.println("Помилка запису логів.");
        }
    }

    public static void sendMessage(){
        try {
            MimeMessage message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Program Errors");
            message.setText(criticalMistake.toString());

            //send the message
            Transport.send(message);

        } catch (MessagingException e) {e.printStackTrace();}
    }

    public static void log(String text){
        typicalAct.append(text);
    }

    public static void logMistake(String text){
        criticalMistake.append(text);
    }

    public static boolean haveMistakes(){
        return !criticalMistake.toString().isEmpty();
    }
}

