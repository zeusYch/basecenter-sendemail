使用spring-boot配置的方式发送邮件

注入的实现类
```go
@Resource
private JavaMailSender javaMailSender;
@Resource
private MailProperties mailProperties;
```

mail相关的配置内容
```application.properties
# Email (MailProperties)
# mail server host
spring.mail.host=smtp.263.net
# mail server port
spring.mail.port=25
spring.mail.username=xxx
spring.mail.password=yyy
# encoding to use for MimeMessages
spring.mail.default-encoding=UTF-8
# properties to set on the JavaMail session
#spring.mail.properties.*=
spring.mail.properties.sign=welcome to spring boot ych
```
