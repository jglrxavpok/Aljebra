package aljebra.values;

import aljebra.MathObject;

public abstract class Value {

    private final String name;
    private MathObject object;

    public Value(String name, MathObject object) {
        this.name = name;
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public MathObject getObject() {
        return object;
    }

    public void setObject(MathObject object) {
        this.object = object;
    }
}
