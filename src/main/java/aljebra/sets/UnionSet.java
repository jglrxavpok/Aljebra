package aljebra.sets;

import aljebra.MathObject;
import aljebra.predicates.Predicate;

public class UnionSet extends Set {
    private final Set left;
    private final Set right;

    public UnionSet(Set a, Set b) {
        super(a.getDefinitionPredicate().or(b.getDefinitionPredicate()));
        this.left = a;
        this.right = b;
    }
}
