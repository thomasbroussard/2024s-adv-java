package fr.epita;

import fr.epita.services.MathsServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class MathsServiceTest {

    private static final Logger LOGGER = LogManager.getLogger(MathsServiceTest.class);


    @BeforeEach
    public void setup(){
        LOGGER.info("before");
    }

    @AfterEach
    public void teardown(){
        LOGGER.info("after");
    }

    @Test
    public void testNominal() throws Exception {
        LOGGER.info("testNominal");
        int testNumber = 5;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement el: stackTrace) {
            LOGGER.trace(el.getClassName() + ":" +el.getLineNumber());
        }

        Long factorial = MathsServices.factorial(testNumber);

        assertThat(factorial).isEqualTo(120);
    }


    @Test
    @DisplayName("US-006 - when a negative value is passed to the factorial, then it should throw an exception")
    public void testNegativeValue() {
        LOGGER.info("testNegativeValue");
        //given
        int testNumber = -5;

        //when
        Exception exception = null;
        try {
            MathsServices.factorial(testNumber);
        }catch (Exception e){
            exception = e;
        }
        //then
        assertThat(exception).isNotNull();
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testZero() throws Exception {
        LOGGER.warn("testZero");
        int testNumber = 0;
        Long factorial = MathsServices.factorial(testNumber);
        if (factorial != 1){
            throw new Exception("unexpected value returned by factorial");
        }
        LOGGER.info(factorial);
    }


    @Test
    @DisplayName("when I input zero in the factorial method, it should return 1")
    public void testZeroEmpty() throws Exception {
        Assertions.fail("not implemented");
    }

//    @ParameterizedTest
//    @CsvInput(
//            "firstName, lastName" +
//             "joe,souaid" +
//             "thomas,broussard"
//
//    )
//    @DisplayName("when I input zero in the factorial method, it should return 1")
//    public void testZeroEmpty(String firstName, String lastName) throws Exception {
//        Assertions.fail("not implemented");
//    }

}
