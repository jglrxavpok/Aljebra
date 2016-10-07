package aljebra.objects.numbers;

public class NaturalNumber extends IntegerNumber {

    public static final NaturalNumber ZERO = new NaturalNumber() {
        @Override
        public String toString() {
            return "0";
        }
    };

    public NaturalNumber() {

    }
}
