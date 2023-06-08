package com.clock.service;

import com.clock.exception.InvalidTimeFormatException;
import com.sun.xml.internal.ws.policy.AssertionSet;

public class SpeakingClockServiceTest {
    private SpeakingClockService speakingClockService;

    @BeforeEach
    void setUp() {
        speakingClockService = new SpeakingClockService();
    }

    @Test
    void convertToWords_ValidTime_ReturnsWords() throws InvalidTimeFormatException {
        String time = "08:34";
        String expectedWords = "It's eight thirty four";
        String actualWords = speakingClockService.convertToWords(time);
        Assertions.assertEquals(expectedWords, actualWords);
    }

    @Test
    void convertToWords_InvalidTime_ThrowsInvalidTimeFormatException() {
        String time = "25:00";
        Assertion.assertThrows(InvalidTimeFormatException .class, () -> {
            speakingClockService.convertToWords(time);
        });
    }
}
