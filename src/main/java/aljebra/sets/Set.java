package aljebra.sets;

import aljebra.MathObject;
import aljebra.exceptions.EmptySetException;
import aljebra.predicates.Predicate;
import aljebra.predicates.Predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Set extends MathObject {

    public static final Set EMPTY_SET = new EmptySet();
    private final Predicate definitionPredicate;
    private Supplier<MathObject> objectFactory;

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
        MathObject any = objectFactory == null ? new MathObject() : objectFactory.get();
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

    public static Set isInstanceOf(Class<? extends MathObject> aClass) {
        return Set.fromPredicate(new Predicate() {
            @Override
            public boolean satisfies(MathObject element) {
                return aClass.isAssignableFrom(element.getClass());
            }

            @Override
            public boolean alwaysTrue() {
                return MathObject.class.equals(aClass);
            }

            @Override
            public boolean alwaysFalse() {
                return false;
            }

            @Override
            public String toString() {
                return "x is instance of "+aClass.getSimpleName();
            }
        });
    }

    public Set setObjectFactory(Supplier<MathObject> objectFactory) {
        this.objectFactory = objectFactory;
        return this;
    }
}
