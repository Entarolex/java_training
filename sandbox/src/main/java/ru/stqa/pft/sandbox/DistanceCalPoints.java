package ru.stqa.pft.sandbox;

/**
 * Created by a.molodkin on 25.02.2016.
 */
public class DistanceCalPoints {
  public static void main(String[] args) {
    Point p1 =new Point(1,4);
    Point p2 =new Point(5,10);
    System.out.println("Расстояние между точками: "+"p1("+p1.x+","+p1.y+") и  p2("+p2.x+","+p2.y+")"+" = "+Point.distance(p1,p2));

  }


}

