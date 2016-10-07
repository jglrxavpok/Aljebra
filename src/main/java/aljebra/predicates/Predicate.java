package aljebra.predicates;

import aljebra.MathObject;
import aljebra.exceptions.EmptySetException;
import aljebra.sets.Set;

public abstract class Predicate {

    private final Predicate negation;

    public Predicate() {
        final Predicate original = this;
        this.negation = new Predicate(original) {
            @Override
            public boolean satisfies(MathObject element) {
                return !element.satisfies(original);
            }

            @Override
            public boolean alwaysTrue() {
                return original.alwaysFalse();
            }

            @Override
            public boolean alwaysFalse() {
                return original.alwaysTrue();
            }

            @Override
            public String toString() {
                return "!"+original.toString();
            }
        };
    }

    private Predicate(Predicate negation) {
        this.negation = negation;
    }

    public abstract boolean satisfies(MathObject element);

    public abstract boolean alwaysTrue();

    public abstract boolean alwaysFalse();

    public abstract String toString();

    public boolean inContradictionWith(Predicate other) {
        return and(other).alwaysFalse();
    }

    public Predicate negate() {
        // uses a final field in order to avoid creating lots of instance of the same predicate
        return negation;
    }

    public Predicate and(Predicate other) {
        final Predicate original = this;
        return new Predicate() {
            public final Set originalSet;
            public final Set otherSet;

            {
                originalSet = Set.fromPredicate(original);
                otherSet = Set.fromPredicate(other);
            }

            @Override
            public boolean satisfies(MathObject element) {
                return element.satisfies(original) && element.satisfies(other);
            }

            @Override
            public boolean alwaysTrue() {
                return original.alwaysTrue() && other.alwaysTrue();
            }

            @Override
            public boolean alwaysFalse() {
                if(original.alwaysFalse() || other.alwaysFalse())
                    return true;
                return !originalSet.isSubset(otherSet) && !otherSet.isSubset(originalSet);
            }

            @Override
            public String toString() {
                return "("+original.toString()+" & "+other.toString()+")";
            }
        };
    }

    public Predicate or(Predicate other) {
        final Predicate original = this;
        return new Predicate() {
            public final Set originalSet;
            public final Set otherSet;

            {
                originalSet = Set.fromPredicate(original);
                otherSet = Set.fromPredicate(other);
            }

            @Override
            public boolean satisfies(MathObject element) {
                return element.satisfies(original) || element.satisfies(this);
            }

            @Override
            public boolean alwaysTrue() {
                return original.alwaysTrue() || other.alwaysTrue();
            }

            @Override
            public boolean alwaysFalse() {
                return original.alwaysFalse() && other.alwaysFalse();
            }

            @Override
            public String toString() {
                return "("+original.toString()+" | "+other.toString()+")";
            }
        };
    }

    public boolean equals(Object o) {
        if(o instanceof Predicate)
            return o.toString().equals(toString());
        return false;
    }
}
