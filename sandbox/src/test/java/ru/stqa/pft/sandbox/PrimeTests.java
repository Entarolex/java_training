package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by a.molodkin on 23.03.2016.
 */
public class PrimeTests {
  @Test
  public void testPrimes(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }
  //@Test
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
  @Test
  public void testNonPrime(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }
}
