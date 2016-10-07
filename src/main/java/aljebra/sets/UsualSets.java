package aljebra.sets;

import aljebra.MathObject;
import aljebra.objects.numbers.Complex;
import aljebra.objects.numbers.IntegerNumber;
import aljebra.objects.numbers.NaturalNumber;
import aljebra.objects.numbers.Real;
import aljebra.predicates.Predicate;

public final class UsualSets {

    public static final Set EMPTY_SET = Set.EMPTY_SET;
    public static final Set COMPLEX_NUMBERS = Set.isInstanceOf(Complex.class).setObjectFactory(() -> new Complex() {

        public final Real realPart;
        public final Real imaginaryPart;

        {
            realPart = new Real(); // any real
            imaginaryPart = new Real(); // any real
        }

        @Override
        public Real realPart() {
            return realPart;
        }

        @Override
        public Real imaginaryPart() {
            return imaginaryPart;
        }
    });

    public static final Set REAL_NUMBERS = Set.fromPredicate(new Predicate() {
        @Override
        public boolean satisfies(MathObject element) {
            if(element.satisfies(COMPLEX_NUMBERS.getDefinitionPredicate()))
                return ((Complex) element).imaginaryPart().equals(NaturalNumber.ZERO);
            return false;
        }

        @Override
        public boolean alwaysTrue() {
            return false;
        }

        @Override
        public boolean alwaysFalse() {
            return false;
        }

        @Override
        public String toString() {
            return "is real";
        }
    });

    // TODO
    public static final Set INTEGER_NUMBERS = Set.isInstanceOf(IntegerNumber.class);
    public static final Set NATURAL_NUMBERS = Set.isInstanceOf(NaturalNumber.class);
    public static final Set ALL_MATH_OBJECTS = Set.isInstanceOf(MathObject.class);
}
