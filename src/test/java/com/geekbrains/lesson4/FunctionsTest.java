package com.geekbrains.lesson4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class FunctionsTest {
    private static Logger logger = LoggerFactory.getLogger(FunctionsTest.class);


    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        logger.error("error");
        logger.info("info");
        logger.trace("trace");
        System.out.println("Метод выполнется 1 раз перед всеми тестами данного класса");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Метод будет выполнен перед каждым тестом");
    }

    @Test
    @Disabled
    @DisplayName("Тест проверяет метод IsPalindrome с палиндромом")
    void givenPalindromeWhenCallIsPalindromeMethodThenTrue() {
        boolean result = Functions.isPalindrome("1234321");
        Assertions.assertTrue(result);
        Assertions.assertEquals(true, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12321", "1"})
    void checkWordOneCharacterIsPalindrome(String word) {
        boolean result = Functions.isPalindrome(word);
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"12321, true", "12, false", "1, true"})
    void checkWordIsPalindrome(String word, boolean expectedResult) {
        boolean testResult = Functions.isPalindrome(word);
        Assertions.assertEquals(testResult, expectedResult);
    }

    @ParameterizedTest
    @MethodSource("catsDataProvider")
    void catTest(Cat cat, boolean isVaccinated) {
        Assertions.assertEquals(cat.isVaccinated(), isVaccinated);
    }

    private static Stream<Arguments> catsDataProvider() {
        return Stream.of(
                Arguments.of(new Cat("Moris", true), true),
                Arguments.of(new Cat("Barsik", false), true)
        );

    }


    @Test
    void assumeTest() {
        Assumptions.assumeTrue(1 == 2);
        Assertions.assertTrue(true);
    }

    @Test
    void assertAllTest() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(true),
                () -> Assertions.assertEquals(1, 2),
                () -> Assertions.assertEquals(1, 3)
        );
    }


    @AfterEach
    void afterEach() {
        System.out.println("Метод будет выполнен после каждого теста");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Метод будет выполнен 1 раз после всех тестов");
    }
}
