package ru.stqa.pft.rest.tests;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * Created by a.molodkin on 28.04.2016.
 */
public class RestTests extends TestBase {
  @Test
  public void testGetIssues() throws IOException, ServiceException {
    Set<Project> projects = getProjects();
    Set<Issue> iss = getIssues();
    System.out.println("Count issues: "+iss.size());
    //List<Issue> issues = new Arrays.asList(iss.)
    Set<Issue> issue = (Set<Issue>) iss.iterator().next();
    for (Issue i: iss){

      System.out.println("id " + i.getId());
  //    System.out.println("Description " + i.getDescription());
//      System.out.println("Subject " + i.getSubject());
      System.out.println("state " + i.getState_name());//1- InProgress //2- Resolved
      skipIfNotFixed(i.getId());
      System.out.println("--");
    }
}


 /* @Test
  public void testCreateIssue() throws IOException {
  Set<Issue>  oldIssue = getIssues();
  Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
  int issueId = createIssue(newIssue);
  Set<Issue> newIssues = getIssues();
  oldIssue.add(newIssue.withId(issueId));
  assertEquals(newIssues,oldIssue);
}*/

  public Set<Project> getProjects() throws IOException {
    String projects = getExecutor().execute(Request.Get("http://demo.bugify.com/api/projects.json")).returnContent().asString();
    System.out.println("projects"+projects);JsonElement parsed = new JsonParser().parse(projects);
    JsonElement issues = parsed.getAsJsonObject().get("projects");
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
  }
  private Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
            .returnContent().asString();
    //System.out.println(json1);
//    String filters = getExecutor().execute(Request.Get("http://demo.bugify.com/api/filters/3/issues.json")).returnContent().asString();
    //System.out.println(filters);

    //String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/filters/0/issues.json")).returnContent().asString();
    System.out.println(json);
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    //JsonElement parsedF = new JsonParser().parse(filters);
    //JsonElement filtersF = parsed.getAsJsonObject().get("filters");
    //System.out.println("\nfiltersF "+new Gson().fromJson(filtersF,new TypeToken<Set<Issue>>(){}.getType()));
    /*return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());*/
    IssueData[] iss= new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
    return  Arrays.asList(iss).stream()
            .map((i) -> new Issue().withId(i.getId().intValue()).withStateName(i.getStatus().getName()))
            .collect(Collectors.toSet());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }

  private int createIssue(Issue newIssue) throws IOException {
    String json =  getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject())
            ,new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

}
