/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubesofttech.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.cubesofttech.util.ThaiUtil;

/**
 *
 * @author HP
 */
public class Mail {

    private static Logger log = Logger.getLogger(Mail.class);
    public static Session mailSession = null;
    public static final String SEPARATOR = ";|,| ";
    
	@Autowired
	private JavaMailSender mailSender;
   
    public static Session getMailSession() {
        return mailSession;
    }

    public static void initialize() throws ClassNotFoundException, NamingException, NoSuchProviderException, MessagingException, AddressException, UnsupportedEncodingException {
        if (mailSession == null) {

            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            mailSession = (Session) envCtx.lookup("mail/Session");
            //Mail.connect();

            // To send email when the server start
            String[] toAddresses = "ball_up@hotmail.com".split(SEPARATOR);
            //Mail.sendEmail("ballbonn@set-square.com", toAddresses, null, null, "Restart PRODUCTION Web Server with attachment", "The server Restart at " + DateUtil.getCurrentTime(), null);

        }
        try {
            log.info("session object: " + mailSession);

            Properties props = mailSession.getProperties();
            for (Object key : props.keySet()) {
                log.info("key:" + key + " value: " + props.getProperty(key.toString()));
            }
        } catch (Exception ex) {
            log.error(ex.toString());
        }

        log.info("INIT Mail Session Complete" + mailSession.toString());
    }

    public static void connect() throws ClassNotFoundException, NamingException, NoSuchProviderException, MessagingException {
        Properties props = mailSession.getProperties();
        Store store = mailSession.getStore("pop3");
        store.connect(props.getProperty("mail.smtp.host"), props.getProperty("username"), props.getProperty("password"));
        log.info("CONNECT MAIL SERVER COMPLETE");
    }

    public static void close() throws ClassNotFoundException, NamingException, NoSuchProviderException, MessagingException {
        Store store = mailSession.getStore("pop3");
        store.close();

        log.info("CLOSE MAIL SERVER COMPLETE");
    }

    public static void sendEmail(String from, String[] toAddresses, String[] ccAddresses, String[] bccAddresses, String subject, String content) throws AddressException, MessagingException, UnsupportedEncodingException , IOException{
        sendEmail(from, toAddresses, ccAddresses, bccAddresses, subject, content, null);
    }

    public static void sendEmail(String from, String[] toAddresses, String[] ccAddresses, String[] bccAddresses, String subject, String content, String[] attachments) throws AddressException, MessagingException,  IOException {

        boolean isTo = false;
        boolean isCC = false;
        boolean isBCC = false;

        log.info("######### Start sending email #########");
        Message message = new MimeMessage(Mail.getMailSession());

        try {
        	message.setHeader("Content_Transfert-Encoding","8bit");
            message.setHeader("charset", "UTF-8");
            subject = MimeUtility.encodeText(subject, "UTF-8", null);
            message.setSubject(subject);
            message.setFrom(new InternetAddress(from));

            log.info("SUBJECT: " + subject);
            log.info("From: " + from);
            if (toAddresses != null) {
                InternetAddress to[] = new InternetAddress[toAddresses.length];
                for (int i = 0; i < toAddresses.length; i++) {
                	if(toAddresses[i] != null){
	                    if (toAddresses[i].length() > 0) {
	                        
	                        to[i] = new InternetAddress(toAddresses[i]);
	                        log.info("TO[" + i + "] = " + to[i].toString());
	                        isTo = true;
	                    }
                	}
                }
                if (isTo) {
                    message.setRecipients(Message.RecipientType.TO, to);
                }
            }
            if (ccAddresses != null) {
                InternetAddress cc[] = new InternetAddress[ccAddresses.length];
                for (int i = 0; i < ccAddresses.length; i++) {
                    if (ccAddresses[i].length() > 0) {
                        cc[i] = new InternetAddress(ccAddresses[i]);
                        log.info("CC[" + i + "] = " + cc[i].toString());
                        isCC = true;
                    }

                }
                if (isCC) {
                    message.addRecipients(Message.RecipientType.CC, cc);
                }
            }
            if (bccAddresses != null) {
                InternetAddress bcc[] = new InternetAddress[bccAddresses.length];
                for (int i = 0; i < bccAddresses.length; i++) {
                    if (bccAddresses[i].length() > 0) {
                        bcc[i] = new InternetAddress(bccAddresses[i]);
                        log.info("BCC[" + i + "] = " + bcc[i].toString());
                        isBCC = true;
                    }
                }
                if (isBCC) {
                    message.addRecipients(Message.RecipientType.BCC, bcc);
                }
            }

            // create and fill the first message part
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setContent(content, "text/html;charset=UTF-8");
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            if (attachments != null) {
                for (int i = 0; i < attachments.length; i++) {
                    MimeBodyPart mbp = new MimeBodyPart();
                    // attach the file to the message
                    FileDataSource fileDataSource = new FileDataSource(attachments[i]);
                    mbp.setDataHandler(new DataHandler(fileDataSource));
                    //mbp.attachFile(fileDataSource.getFile());
                    String fileName = ThaiUtil.ASCII2Unicode(fileDataSource.getName());
                    mbp.setFileName(MimeUtility.encodeText(fileName, "UTF-8", null));
                    //mbp.setFileName(MimeUtility.encodeText(fileDataSource.getName(), "UTF-8", null));
                    
                    log.debug("file name: " + fileDataSource.getName());
                    log.debug("file name encode FILENAME 2: " +fileName);
                    mp.addBodyPart(mbp);
                }
            }

            // add the Multipart to the message
            message.setContent(mp);

            // set the Date: header
            //message.setSentDate(DateUtil.getCurrentTime());

            Transport.send(message);
            log.info("######### End sending email Complete #########");
        } catch (AddressException ex) {
            log.error(ex.getMessage());
            throw ex;
        } catch (MessagingException ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }
}
