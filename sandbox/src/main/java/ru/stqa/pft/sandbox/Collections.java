package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by a.molodkin on 23.03.2016.
 */
public class Collections {
  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "Php"};

    List<String> languages = Arrays.asList("Java", "C#", "Python", "Php");
    

    for (String l : languages) {
      System.out.println("Я хочу выучить " + l);
    }
  }
}
