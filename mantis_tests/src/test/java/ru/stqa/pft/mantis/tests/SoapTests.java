package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.*;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by a.molodkin on 25.04.2016.
 */
public class SoapTests extends TestBase{

 // @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  //@Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }
@Test
  public void testStatusIssue() throws RemoteException, ServiceException, MalformedURLException {
  Set<Project> projects = app.soap().getProjects();
  /*  Issue issue=new Issue().withSummary("Test issue").withDescription("Test issue description").withStatus("resolved")
            .withProject(projects.iterator().next());*/
    Set<Issue> issues = app.soap().getIssues(projects.iterator().next());
    System.out.println(issues.size());
    for (Issue iss : issues) {
      System.out.println(iss.getId());
      System.out.println(iss.getStatus().toString());
    }
  }

}