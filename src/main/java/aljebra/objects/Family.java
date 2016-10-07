package aljebra.objects;

import aljebra.MathObject;

public class Family extends MathObject {

    private final Function supplier;

    public Family(Function supplier) {
        this.supplier = supplier;
    }

    public MathObject elementAt(MathObject index) {
        return supplier.apply(index);
    }
}
