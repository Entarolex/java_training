package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by a.molodkin on 15.03.2016.
 */
public class GroupModifacationTests extends TestBase {

  @Test
  public void testGroupModifacatoin(){
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test10", null, null),true);
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test10",null, null),false);
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }
}
