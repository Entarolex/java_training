package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;



/**
 * Created by a.molodkin on 29.02.2016.
 */
public class SquareTests {
  @Test
  public void testArea(){
    Square s=new Square(5);
    System.out.println();
    Assert.assertEquals(s.area(),20.0);

  }
}
