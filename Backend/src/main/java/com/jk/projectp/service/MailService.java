package com.jk.projectp.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.Message;
import com.jk.projectp.model.User;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.*;

@Service
public class MailService {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static final String TOKENS_DIC_PATH = "tokens";

    private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_SEND, GmailScopes.GMAIL_LABELS);

    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = MailService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Credential file not found! path: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIC_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return credential;
    }

    public static void test() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName("Test")
                .build();

        String user = "me";
        ListLabelsResponse listLabelsResponse = service.users().labels().list(user).execute();
        List<Label> labels = listLabelsResponse.getLabels();
        if (labels.isEmpty()) {
            System.out.println("No label found!");
        } else {
            System.out.println("Labels: ");
            for (Label label :
                    labels) {
                System.out.printf("- %s\n", label.getName());
            }
        }
    }

    public static Message sendEmail(String fromEmailAddress, String toEmailAddress, String subject, String body)
            throws MessagingException, IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName("Test")
                .build();
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(fromEmailAddress));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(toEmailAddress));
        email.setSubject(subject);
        email.setText(body);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        try {
            message = service.users().messages().send("me", message).execute();
            System.out.println("Message Id  " + message.getId() + "Sent");
//            System.out.println(message.toPrettyString());
            return message;
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }
        return null;
    }

    public Message sendMailToUser(User user, String subject, String body) throws MessagingException, IOException, GeneralSecurityException, NullPointerException {
        if (user != null)
            return sendEmail("robomasterhkust@gmail.com", user.getEmail(), subject, body);
        else
            throw new NullPointerException();
    }

    // Send msg to users and return failed user
    public Set<User> sendMsgToGroup(Set<User> users, String subject, String body) {
        Set<User> result = new HashSet<>();
        for (User user :
                users) {
            try {
                sendMailToUser(user, subject, body);
            } catch (Exception e) {
                result.add(user);
            }
        }
        return result;
    }
}
