package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by a.molodkin on 15.03.2016.
 */
public class GroupModifacationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
   if (app.db().groups().size()==0){
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test3"));
    }
  }

  @Test
  public void testGroupModifacatoin(){
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group= new GroupData()
            .withId(modifiedGroup.getId()).withName("test3").withHeader("test3").witnFooter("test3");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }




}
