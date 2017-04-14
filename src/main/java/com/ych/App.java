package com.ych;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootApplication
@RestController
public class App {

	@Resource
	private JavaMailSender javaMailSender;
	@Resource
	private MailProperties mailProperties;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@RequestMapping("/send")
	public String sendEmail(@RequestParam(value="sendTo", required=false, defaultValue = "1245152606@qq.com") String sendTo) {
		try {
			MimeMessage mm = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mm, true);
			helper.setTo(sendTo);
			helper.setSubject("测试邮件");
			// send inline resources
			helper.setText("<html><body>Dear yangchao<br/><img src='cid:identifier1234'><br/>"
					+mailProperties.getProperties().get("sign")
					+"</body></html>", true);
			FileSystemResource res = new FileSystemResource(new File("C:\\Users\\Administrator\\b5483e6792244cc2f4c25503d0cd5e7.jpg"));
			helper.addInline("identifier1234", res);
			//send attachments
			helper.addAttachment("CoolImage.jpg", res);
			javaMailSender.send(mm);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "send Success";
	}
}
