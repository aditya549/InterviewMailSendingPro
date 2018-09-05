

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.io.IOException;
import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
@WebServlet("/ForgotServlet")
public class ForgotServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
			String targetmailid=req.getParameter("email");
			String to=targetmailid;//change accordingly
			String from="cubicitsolutioninterview@gmail.com";
			String password="1100reddy";
			String subject=req.getParameter("sub");
			String body=req.getParameter("body");
			String bodyF="";
			String test="";
			for(int i=0;i<body.length();i++) {
				if((int)body.charAt(i)==13) {
				test+="<br><br>";
				}
				test+=body.charAt(i);
			}
			System.out.println("test:"+test);
			String sub=subject;
			String msg = "<i>Greetings!</i><br><br><br>";
		        msg += "<b>Hi...!!There We are From Cubic IT Solution!</b><br><br><br>";
		        msg += "<b>Thank you For U'r Association With Cubic,</b><br><br><br>";
		        msg += "<b>Here is The Surprise To you</b><br><br><br>";
		        msg += test;
		        msg +="<br><br><br><br><b>Thanks&Regards</b><br><br><br>";
		        msg +="<b>HR-Manger(Cubic It Solution Pvt Ltd)</b><br><br><br>";
		 	Properties props = new Properties();    
	          props.put("mail.smtp.host", "smtp.gmail.com");    
	          props.put("mail.smtp.socketFactory.port", "465");    
	          props.put("mail.smtp.socketFactory.class",    
	                    "javax.net.ssl.SSLSocketFactory");    
	          props.put("mail.smtp.auth", "true");    
	          props.put("mail.smtp.port", "465");    
	          //get Session   
	          Session session = Session.getDefaultInstance(props,    
	           new javax.mail.Authenticator() {    
	           protected PasswordAuthentication getPasswordAuthentication() {    
	           return new PasswordAuthentication(from,password);  
	           }    
	          });    
	          //compose message    
	          try {    
	           MimeMessage mess = new MimeMessage(session);
	           
	           /*mess.setSender(new InternetAddress("no-reply@domain.com", "Sender Name"));*/
	          
	           mess.setFrom(new InternetAddress("Cubic It Solution Pvt Ltd" + "<" + "no-reply@domain.com" + ">"));
		 	   mess.setReplyTo(InternetAddress.parse("no_reply@gmail.com", false));
	           mess.addRecipient(Message.RecipientType.TO,new InternetAddress(to,false));    
	           mess.setSubject(sub);    
	           mess.setText(msg); 
	           mess.setSentDate(new Date());
	           
	           mess.setContent(msg, "text/html");
	           //send message  
	           Transport.send(mess);    
	           //System.out.println("message sent successfully");  
	           //System.out.println("message sent successfully"); 
	           resp.getWriter().print("<script>window.alert('Mail Sent To Given Mail id Successfully')</script>");
				RequestDispatcher rd=req.getRequestDispatcher("sendingmail.jsp");
				rd.include(req, resp);
	          /*resp.getWriter().print("<script>window.location.href='http://localhost:8085/DemoTemplate/index.jsp'</script>");*/
	          } catch (MessagingException e) 
	          {    		
			resp.getWriter().print("<script>window.alert('Something Went Wrong try again later')</script>");
			RequestDispatcher rd=req.getRequestDispatcher("sendingmail.jsp");
			rd.include(req, resp);
			/*resp.getWriter().println("<script>window.location.href='http://localhost:8085/DemoTemplate_Task/forgotpassword.jsp'");*/
		}
	}
}
