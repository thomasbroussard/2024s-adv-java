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

        assertThat(factorial).isEqualTo(120);
    }


    @Test
    public void testNegativeValue() {
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
        int testNumber = 0;
        Long factorial = MathsServices.factorial(testNumber);
        if (factorial != 1){
            throw new Exception("unexpected value returned by factorial");
        }
        System.out.println(factorial);
    }

}
