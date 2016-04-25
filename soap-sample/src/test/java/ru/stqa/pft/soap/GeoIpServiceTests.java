package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by a.molodkin on 25.04.2016.
 */
public class GeoIpServiceTests {

@Test
  public void testMyIp(){
  GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.28.29.152");
  assertEquals(geoIP.getCountryCode(), "RUS");
  }
}
