package aljebra.objects.numbers;

public class Real extends Complex {

    public Real() {
    }

    @Override
    public Real realPart() {
        return this;
    }

    @Override
    public Real imaginaryPart() {
        return NaturalNumber.ZERO;
    }
}
