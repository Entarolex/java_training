package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertTrue;

/**
 * Created by a.molodkin on 22.04.2016.
 */
public class ResetPasswordTests extends TestBase{
  private Properties properties;

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {

    app.adminHelper().resetPassword();
    String user = app.adminHelper().getUserName();
    String email = app.adminHelper().getUserEmail();
    app.adminHelper().resertPasswordButtonClick();

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    String newPassword = "newpassword";
    app.adminHelper().completeResetPassword(confirmationLink, newPassword);
    assertTrue(app.newSession().login(user, newPassword));
  }

   private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();

  }

}
