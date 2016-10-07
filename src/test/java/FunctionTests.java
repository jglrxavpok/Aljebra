import aljebra.MathObject;
import aljebra.exceptions.UndefinedValueException;
import aljebra.objects.numbers.Complex;
import aljebra.objects.Function;
import aljebra.sets.Set;
import aljebra.sets.UsualSets;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionTests {

    @Test(expected = UndefinedValueException.class)
    public void argumentChecking() {
        Set domain = UsualSets.REAL_NUMBERS;
        Set range = domain;
        Function identity = new Function(domain, range) {

            @Override
            protected MathObject calculateImage(MathObject o) {
                return o;
            }
        };

        Complex complex = (Complex) UsualSets.COMPLEX_NUMBERS.takeAny();
        identity.apply(complex);
    }

    @Test
    public void identity() {
        Set domain = UsualSets.COMPLEX_NUMBERS;
        Set range = domain;
        Function identity = new Function(domain, range) {

            @Override
            protected MathObject calculateImage(MathObject o) {
                return o;
            }
        };

        Complex complex = (Complex) UsualSets.COMPLEX_NUMBERS.takeAny();
        assertEquals(identity.apply(complex), complex);
    }
}
