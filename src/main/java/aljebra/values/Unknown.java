package aljebra.values;

import aljebra.MathObject;
import aljebra.exceptions.EmptySetException;
import aljebra.sets.Set;

public class Unknown extends Value {

    private final Set domain;

    public Unknown(String name, Set domain) {
        super(name, safeTakeAny(domain));
        this.domain = domain;
    }

    private static MathObject safeTakeAny(Set domain) {
        if(domain.isEmpty())
            return null;
        try {
            return domain.takeAny();
        } catch (EmptySetException e) {
            ;
            return null;
        }
    }

    public Set getDomain() {
        return domain;
    }
}
