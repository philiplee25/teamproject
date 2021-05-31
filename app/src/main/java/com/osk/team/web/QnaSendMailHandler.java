package com.osk.team.web;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/qna/send")
public class QnaSendMailHandler {

  @RequestMapping("/qna/send")
  public String execute(HttpServletRequest request, HttpServletResponse response)
          throws Exception {

    System.out.println("이메일 발송 시작");

    Properties props = System.getProperties();
    props.put("mail.smtp.host", "smtp.naver.com");
    props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
    props.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
    props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
    props.put("mail.smtp.port", "465"); //SMTP Port


    Session session = Session.getDefaultInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("cc-_-@naver.com", "qlxmqlxm1");
      }
    });
    System.out.println("Session 생성");
    System.out.println(request.getParameter("email"));

    sendEmail(session, request.getParameter("email"),
            "[JoinJoy] 고객님의 문의사항에 대해 답변 드립니다. ",
            request.getParameter("content") + "\n" + request.getParameter("answer"));

    return "redirect:list";
  }

  public static void sendEmail(Session session, String toEmail, String subject, String body){
    try {
      MimeMessage msg = new MimeMessage(session);
      //set message headers
      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
      msg.addHeader("format", "flowed");
      msg.addHeader("Content-Transfer-Encoding", "8bit");

      msg.setFrom(new InternetAddress("cc-_-@naver.com", "JoinJoy"));

      msg.setSubject(subject, "UTF-8");

      msg.setText(body, "UTF-8");

      msg.setSentDate(new Date());

      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
      System.out.println("이메일 발송 준비");
      Transport.send(msg);

      System.out.println("이메일 발송 성공!!");

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}