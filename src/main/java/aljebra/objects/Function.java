package aljebra.objects;

import aljebra.MathObject;
import aljebra.exceptions.UndefinedValueException;
import aljebra.sets.Set;

public abstract class Function extends MathObject {

    private final Set domain;
    private final Set range;

    public Function(Set domain, Set range) {
        this.domain = domain;
        this.range = range;
    }

    public Set getDomain() {
        return domain;
    }

    public Set getRange() {
        return range;
    }

    public MathObject apply(MathObject object) {
        if(!domain.contains(object))
            throw new UndefinedValueException(object.toString()+" is in "+domain.toString());
        return calculateImage(object);
    }

    protected abstract MathObject calculateImage(MathObject o);
}
