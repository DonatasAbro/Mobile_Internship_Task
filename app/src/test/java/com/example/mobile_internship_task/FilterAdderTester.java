package com.example.mobile_internship_task;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FilterAdderTester {

    @Test
    public void testControl() {
        String url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1301.jpg";
        Integer trueSum = 2088094 + 1301;
        assertEquals(trueSum, MainActivity.filterAndAddDigits(url));
    }

    @Test
    public void testNotNearUnderscore() {
        String url = "https://images.dog.ceo/breeds/hound-afghan/n02088094asv_bgsfg1301.jpg";
        Integer trueSum = 2088094 + 1301;
        assertEquals(trueSum, MainActivity.filterAndAddDigits(url));
    }

    @Test
    public void testNoUnderscore() {
        String url = "https://images.dog.ceo/breeds/hound-afghan/n02088094-1301.jpg";
        Integer trueSum = 2088094 + 1301;
        assertEquals(trueSum, MainActivity.filterAndAddDigits(url));
    }

    @Test
    public void testOneNumber() {
        String url = "https://images.dog.ceo/breeds/hound-afghan/n02088094.jpg";
        Integer trueSum = 2088094 + 1301;
        assertEquals(trueSum, MainActivity.filterAndAddDigits(url));
    }

    @Test
    public void testReversedNumberOrder() {
        String url = "https://images.dog.ceo/breeds/hound-afghan/n1301_02088094.jpg";
        Integer trueSum = 2088094 + 1301;
        assertEquals(trueSum, MainActivity.filterAndAddDigits(url));
    }

    @Test
    public void testThirdNumberAtEnd() {
        String url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1301_7896.jpg";
        Integer trueSum = 2088094 + 1301;
        assertEquals(trueSum, MainActivity.filterAndAddDigits(url));
    }

    @Test
    public void testThirdNumberAtMiddle() {
        String url = "https://images.dog.ceo/breeds/hound-afghan/n02088094_7896_1301.jpg";
        Integer trueSum = 2088094 + 1301;
        assertEquals(trueSum, MainActivity.filterAndAddDigits(url));
    }

    @Test
    public void testThirdNumberAtStart() {
        String url = "https://images.dog.ceo/breeds/hound-afghan/n7896_02088094_1301.jpg";
        Integer trueSum = 2088094 + 1301;
        assertEquals(trueSum, MainActivity.filterAndAddDigits(url));
    }
}
