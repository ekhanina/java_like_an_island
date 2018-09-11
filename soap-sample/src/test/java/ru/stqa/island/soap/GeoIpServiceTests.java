package ru.stqa.island.soap;

import com.buzzbuzhome.BBHLocation;
import com.buzzbuzhome.GeoIP;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        BBHLocation userLocation = new GeoIP().getGeoIPSoap12().getUserLocation("46.46.144.20");
        assertEquals(userLocation.getCountryCode(), "US");
    }
}
