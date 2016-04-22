package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by a.molodkin on 22.04.2016.
 */
public class ResetPassword extends TestBase{
  private Properties properties;

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {
    String password = "password";
    String email = "user10@localhost.localdomain";
    app.resetPassword().loginAdmin();
    app.resetPassword().goToManageUsersPage();

    //app.newSession().login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));


   // app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    //app.newSession().login(user, password);
    //assertTrue(app.newSession().login(user, password));
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
