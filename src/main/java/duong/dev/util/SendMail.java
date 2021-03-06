package duong.dev.util;

import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

import duong.dev.common.Common;
import duong.dev.common.EmailConfig;
import duong.dev.common.ResponeCustom;
import duong.dev.exception.AppException;

@Component
public class SendMail {
	public static void senMaiChaoMung(String username , String password, String email) {
		String noiDung = "Bạn đã đăng kí tài khoản thành công với "
				+"Username: "+username+ " Password: "+password
				+" .Cảm ơn bạn đã lựa chọn chúng tôi !";
		SenMail(noiDung, email);
		
	}
	
	public static void forgetPassword(String email , String endUrl) {
		String conten = "Bạn vui lòng vào đường link này để đổi lại mật khẩu : " + Common.URL+"/login/restart/password/"+endUrl;
		SenMail(conten, email);
	}
	
	public static boolean SenMail(String noiDung, String email) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			String username = EmailConfig.EMAIL_USER;
			String password = EmailConfig.EMAIL_PASSWORD;
			return new PasswordAuthentication(username, password);
		}
		});
		
		MimeMessage message = new MimeMessage(session);
		
		try {
			
			message.setFrom(new InternetAddress(EmailConfig.EMAIL_USER));
			message.setRecipients(Message.RecipientType.TO, email);
			message.setSubject("BeeBook", "utf-8");
			message.setText(noiDung , "utf-8","html");
			message.setReplyTo(message.getFrom()); 
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG,"Đã xảy ra lỗi trong quá trình gửi email cho bạn !");
		}
		return true;
	}
}
