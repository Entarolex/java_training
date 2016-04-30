package ru.stqa.pft.rest.tests;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by a.molodkin on 28.04.2016.
 */
public class RestTests extends TestBase {
    @Test
    public void testGetIssues() throws IOException, ServiceException {
        Set<Issue> iss = getIssues();
        System.out.println("Count issues: " + iss.size());
        for (Issue i : iss) {
            System.out.println("id " + i.getId());
            System.out.println("state " + i.getState_name());
            skipIfNotFixed(i.getId());
            System.out.println("--");
        }
    }


    //@Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssue = getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssue.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssue);
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject())
                        , new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
