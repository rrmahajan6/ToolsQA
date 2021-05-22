//package org.dolphin.rough;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.Markup;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//public class Source {
//    public static ExtentReports extentReports = new ExtentReports();
//    public static ExtentTest test;
//    ExtentSparkReporter sparkReporter = new ExtentSparkReporter("/Users/rahul.mahajan/Desktop/Dolphin/output/rahul");
//    WebDriver driver;
//
////    @BeforeClass
//    public void beforeClass() {
//        extentReports.attachReporter(sparkReporter);
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//    }
//
//    @Test
//    public void testing() {
//        driver.get("https://google.com");
//        test = extentReports.createTest("google test");
//        test.log(Status.INFO, "launching google site");
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        String s = ts.getScreenshotAs(OutputType.BASE64);
//        test.log(Status.PASS,"yeh hai screenshot",MediaEntityBuilder.createScreenCaptureFromBase64String(s,"yehi to image hai").build());
//      //  test.addScreenCaptureFromBase64String(s);
//        test.log(Status.INFO,"tu bhi kya yad rakhega");
//        extentReports.flush();
//    }
//
//    @Test
//    public void testing3() {
//        driver.get("https://google.com");
//        test = extentReports.createTest("google test2");
//        test.log(Status.INFO, "launching google site");
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        String s = ts.getScreenshotAs(OutputType.BASE64);
//        test.log(Status.PASS,"yeh hai screen shot",MediaEntityBuilder.createScreenCaptureFromBase64String(s,"yehi to image hai").build());
//       // test.addScreenCaptureFromBase64String(s);
//        test.log(Status.INFO,"tu bhi kya yad rakhega");
//        extentReports.flush();
//    }
//
//    @Test
//    public void testing2() {
//        driver.get("https://google.com");
//        test = extentReports.createTest("google test3");
//        test.log(Status.INFO, "launching google site");
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        String s = ts.getScreenshotAs(OutputType.BASE64);
//        test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(s).build());
//        test.log(Status.PASS, "yeh hai screen shot",MediaEntityBuilder.createScreenCaptureFromBase64String(s,"yehi to image hai").build());
//       // test.addScreenCaptureFromBase64String(s);
//        test.log(Status.INFO,"tu bhi kya yad rakhega");
//        extentReports.flush();
//    }
//    @Test
//    public void sendMail(){
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//
//        // This will handle the complete authentication
//        Session session = Session.getDefaultInstance(props,
//
//                new javax.mail.Authenticator() {
//
//                    protected PasswordAuthentication getPasswordAuthentication() {
//
//                        return new PasswordAuthentication("rrmahajan6@gmail.com", "Sspchp@0061");
//
//                    }
//
//                });
//
//        try {
//
//            // Create object of MimeMessage class
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("rrmahajan6@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("rrmahajan6@gmail.com"));
//            message.setSubject("Testing Subject");
//
//            // Create object to add multimedia type content
//            BodyPart messageBodyPart1 = new MimeBodyPart();
//            messageBodyPart1.setText("This is message body");
//
//            // Create another object to add another content
//            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
//            String filename = "/Users/rahul.mahajan/Desktop/Dolphin/output/Index.html";
//
//            // Create data source and pass the filename
//            DataSource source = new FileDataSource(filename);
//
//            // set the handler
//            messageBodyPart2.setDataHandler(new DataHandler(source));
//
//            // set the file
//            messageBodyPart2.setFileName(filename);
//
//            // Create object of MimeMultipart class
//            Multipart multipart = new MimeMultipart();
//
//            // add body part 1
//            multipart.addBodyPart(messageBodyPart2);
//
//            // add body part 2
//            multipart.addBodyPart(messageBodyPart1);
//
//            // set the content
//            message.setContent(multipart);
//
//            // finally send the email
//            Transport.send(message);
//
//            System.out.println("=====Email Sent=====");
//
//        } catch (MessagingException e) {
//
//            throw new RuntimeException(e);
//        }
//
//    }
//    }
//
//
////aim to check if screen shot is getting attached in extent report