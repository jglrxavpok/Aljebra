package aljebra.sets;

import aljebra.MathObject;
import aljebra.exceptions.EmptySetException;
import aljebra.predicates.Predicate;

public class IntersectionSet extends Set {
    private final Set left;
    private final Set right;
    private boolean isEmpty;

    public IntersectionSet(Set a, Set b) {
        super(a.getDefinitionPredicate().and(b.getDefinitionPredicate()));
        this.left = a;
        this.right = b;

        isEmpty = !(a.isSubset(b) || b.isSubset(a));
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public MathObject takeAny() throws EmptySetException {
        if(isEmpty)
            throw new EmptySetException();
        return super.takeAny();
    }
}
