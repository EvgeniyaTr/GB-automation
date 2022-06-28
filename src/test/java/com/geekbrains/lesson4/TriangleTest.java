package com.geekbrains.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.geekbrains.lesson4.Triangle.triangleArea;

public class TriangleTest {

    private static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @BeforeAll
    static void beforeAll() {
        logger.info("info");
    }


    @Test
    void checkTriangleAreaFormula() throws Exception {
        Assertions.assertEquals(4.472, triangleArea(3, 3, 4), 0.001);
    }

    @Test
    void exceptionWhenBadTriangle() {
        Assertions.assertThrows(Exception.class, () -> triangleArea(-1, -1, 1));
    }

}




