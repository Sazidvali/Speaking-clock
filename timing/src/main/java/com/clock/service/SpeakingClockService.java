package com.clock.service;

import com.clock.exception.InvalidTimeFormatException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Service
public class SpeakingClockService {

    private static final String[] NUMBER_WORDS = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] TENS_WORDS = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public String convertToWords(String time) throws InvalidTimeFormatException  {
        // Validate the time format
        if (!isValidTimeFormat(time)) {
            throw new InvalidTimeFormatException("Invalid time format");
        }

        // Extract hours and minutes from the time string
        String[] timeParts = time.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        // Convert hours and minutes to words
        String hourWords = convertNumberToWords(hours);
        String minuteWords = convertNumberToWords(minutes);

        // Construct the final output string
        String output = "It's " + hourWords + " " + getHourDescriptor(hours) + " " +
                minuteWords + " " + getMinuteDescriptor(minutes);
        return output.trim();
    }

    private boolean isValidTimeFormat(String time) {
        // Validate time format (HH:MM)
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale .ENGLISH) ;
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String convertNumberToWords(int number) {
        if (number >= 0 && number < 20) {
            return NUMBER_WORDS[number];
        } else if (number >= 20 && number < 100) {
            int tens = number / 10;
            int ones = number % 10;
            return TENS_WORDS[tens] + " " + NUMBER_WORDS[ones];
        }
        return ""; // Placeholder for numbers beyond 99 (to be implemented as needed)
    }

    private String getHourDescriptor(int hours) {
        if (hours == 0 || hours == 24) {
            return "Midnight";
        } else if (hours == 12) {
            return "Midday";
        } else {
            return "hour";
        }
    }

    private String getMinuteDescriptor(int minutes) {
        if (minutes == 1) {
            return "minute";
        } else {
            return "minutes";
        }
    }
}
