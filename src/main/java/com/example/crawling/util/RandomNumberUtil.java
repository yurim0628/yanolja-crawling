package com.example.crawling.util;

import com.example.crawling.constants.ReservationConstants;

import java.util.Random;

public class RandomNumberUtil {
    private static final Random random = new Random();


    public static int getRandomNumber(int min, int max) { return random.nextInt(max - min + 1) + min; }

    public static double getRandomDoubleNumber(double min, double max) { return min + (max - min) * random.nextDouble(); }

    public static int getRandomReservationNumber() {
        return getRandomNumber(ReservationConstants.MIN_RESERVATIONS, ReservationConstants.MAX_RESERVATIONS);
    }

    public static int getRandomCheckInDateDuration() {
        return getRandomNumber(ReservationConstants.MIN_CHECK_IN_DATE_DURATION, ReservationConstants.MAX_CHECK_IN_DATE_DURATION);
    }

    public static int getRandomStayCheckOutDateDuration() {
        return getRandomNumber(ReservationConstants.MIN_STAY_CHECK_OUT_DATE_DURATION, ReservationConstants.MAX_STAY_CHECK_OUT_DATE_DURATION);
    }

    public static int getRandomReservationDateDuration() {
        return getRandomNumber(ReservationConstants.MIN_RESERVATION_DAYS_DURATION, ReservationConstants.MAX_RESERVATION_DAYS_DURATION);
    }

    public static int getRandomDayUseCheckInTimeDuration() {
        return getRandomNumber(0, ReservationConstants.DAY_USE_CHECK_IN_TIME_DURATION);
    }

    public static double getRandomSellingPriceMultiplier() {
        return getRandomDoubleNumber(ReservationConstants.MIN_SELLING_PRICE_MULTIPLIER, ReservationConstants.MAX_SELLING_PRICE_MULTIPLIER);
    }

    public static String generateRandomDigits() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            builder.append(getRandomNumber(0, ReservationConstants.MAX_RANDOM_DIGIT));
        }
        return builder.toString();
    }
}
