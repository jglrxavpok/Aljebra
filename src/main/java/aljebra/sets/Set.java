package aljebra.sets;

import aljebra.MathObject;
import aljebra.exceptions.EmptySetException;
import aljebra.predicates.Predicate;

public class Set extends MathObject {

    public static final Set EMPTY_SET = new EmptySet();
    private final Predicate definitionPredicate;

    protected Set(Predicate definition) {
        this.definitionPredicate = definition;
    }

    public boolean isSubset(Set other) {
        try {
            MathObject any = takeAny();
            return other.contains(any);
        } catch (EmptySetException e) {
            return true;
        }
    }

    public boolean contains(MathObject element) {
        return element.satisfies(definitionPredicate);
    }

    public Set intersect(Set other) {
        if(isEmpty() || other.isEmpty())
            return EMPTY_SET;
        return new IntersectionSet(this, other);
    }

    public Set union(Set other) {
        return new UnionSet(this, other);
    }

    public MathObject takeAny() throws EmptySetException {
        MathObject any = new MathObject();
        any.addSatisfiedByDefinition(definitionPredicate);
        return any;
    }

    public boolean equals(Object other) {
        if(other instanceof Set) {
            return isSubset((Set) other) && ((Set) other).isSubset(this); // double-inclusion
        }
        return false;
    }

    public static Set fromPredicate(Predicate predicate) {
        return new Set(predicate);
    }

    public boolean isEmpty() {
        try {
            takeAny();
            return false;
        } catch (EmptySetException e) {
            return true;
        }
    }

    public Set complement(Set in) {
        return Set.fromPredicate(definitionPredicate.negate().and(in.definitionPredicate));
    }

    public Set complement() {
        return Set.fromPredicate(definitionPredicate.negate());
    }

    public Predicate getDefinitionPredicate() {
        return definitionPredicate;
    }

    public String toString() {
        return "{x;"+definitionPredicate+"}";
    }
}
