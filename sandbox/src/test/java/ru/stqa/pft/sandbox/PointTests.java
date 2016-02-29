package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by a.molodkin on 29.02.2016.
 */
public class PointTests {

  public double disResult;
  @Test
  public  void testDistance(){
   Point p1 = new Point(1, 4);
   Point p2 = new Point(5, 10);
   disResult=Point.distance(p1,p2);
   System.out.println("Расстояние между точками: "+"p1("+p1.x+","+p1.y+") и  p2("+p2.x+","+p2.y+")"+" = "+disResult);
   Assert.assertEquals(disResult,7.211102550927978);
  }

  @Test
  public void testDistance2() {
    Point p3 = new Point(6, 54);
    Point p4 = new Point(-20, 110);
    disResult=Point.distance(p3,p4);
    System.out.println("Расстояние между точками: "+"p3("+p3.x+","+p3.y+") и  p4("+p4.x+","+p4.y+")"+" = "+disResult);
    Assert.assertEquals(disResult,61.741396161732524);

  }

}
