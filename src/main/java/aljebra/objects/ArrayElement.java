package aljebra.objects;

import aljebra.MathObject;
import aljebra.objects.numbers.IntegerNumber;

public class ArrayElement extends MathObject {
    private final MathObject[] array;
    private final IntegerNumber index;

    public ArrayElement(MathObject[] array, IntegerNumber index) {
        this.array = array;
        this.index = index;
    }

    public MathObject[] getArray() {
        return array;
    }

    public IntegerNumber getIndex() {
        return index;
    }
}
