package aljebra.objects.numbers;

import aljebra.MathObject;

public abstract class Complex extends MathObject {

    public Complex() {

    }

    public abstract Real realPart();

    public abstract Real imaginaryPart();
}
