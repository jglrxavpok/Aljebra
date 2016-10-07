package aljebra.sets;

import aljebra.MathObject;
import aljebra.exceptions.EmptySetException;
import aljebra.predicates.Predicate;
import aljebra.predicates.Predicates;

import java.util.Arrays;
import java.util.List;

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

    public boolean isEmpty() {
        if(definitionPredicate.alwaysFalse())
            return true;
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

    public static Set fromPredicate(Predicate predicate) {
        return new Set(predicate);
    }

    public static Set singleton(MathObject object) {
        return new Set(Predicates.equality(object));
    }

    public static Set fromList(List<MathObject> objects) {
        return new Set(Predicates.isInList(objects));
    }

    public static Set fromValues(MathObject... objects) {
        return new Set(Predicates.isInList(Arrays.asList(objects)));
    }
}
