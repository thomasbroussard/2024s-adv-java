package fr.epita;

import fr.epita.services.MathsServices;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;



public class MathsServiceTest {

    @Test
    public void testNominal() throws Exception {
        int testNumber = 5;

        Long factorial = MathsServices.factorial(testNumber);

        if (factorial != 120){
            throw new Exception("unexpected value returned by factorial");
        }
        System.out.println(factorial);
    }


    @Test
    public void testNegativeValue() {
        //given
        int testNumber = -5;

        //when
        Long factorial = MathsServices.factorial(testNumber);

        //then
        assertThat(factorial).isEqualTo(120L);
    }

    @Test
    public void testZero() throws Exception {
        int testNumber = 0;
        Long factorial = MathsServices.factorial(testNumber);
        if (factorial != 1){
            throw new Exception("unexpected value returned by factorial");
        }
        System.out.println(factorial);
    }

}
