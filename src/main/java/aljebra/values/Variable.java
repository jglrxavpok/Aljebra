package aljebra.values;

import aljebra.MathObject;

public abstract class Variable extends Value {

    public Variable(String name, MathObject object) {
        super(name, object);
    }
}
