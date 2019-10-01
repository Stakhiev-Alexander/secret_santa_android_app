package com.alexander.secretsanta;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//Class is extending AsyncTask because this class is going to perform a networking operation
public class SendMail extends AsyncTask<Void, Void, Void> {

  //Declaring Variables
  private Context context;
  private Session session;

  private String email;
  private String subject;
  private String message;

  private ProgressDialog progressDialog;

  public SendMail(Context context, String email, String subject, String message) {
    this.context = context;
    this.email = email;
    this.subject = subject;
    this.message = message;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    progressDialog = ProgressDialog
        .show(context, "Sending message", "Please wait...", false, false);
  }

  @Override
  protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
    progressDialog.dismiss();
    Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show();
  }

  @Override
  protected Void doInBackground(Void... params) {
    Properties props = new Properties();

    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
          }
        });

    try {
      MimeMessage mm = new MimeMessage(session);

      //Setting sender address
      mm.setFrom(new InternetAddress(Config.EMAIL));
      //Adding receiver
      mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
      //Adding subject
      mm.setSubject(subject);
      //Adding message
      mm.setText(message);

      //Sending email
      Transport.send(mm);

    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return null;
  }
}