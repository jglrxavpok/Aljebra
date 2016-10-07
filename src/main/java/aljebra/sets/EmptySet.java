package aljebra.sets;

import aljebra.MathObject;
import aljebra.exceptions.EmptySetException;
import aljebra.predicates.Predicate;

class EmptySet extends Set {

    public EmptySet() {
        super(new Predicate() {
            @Override
            public boolean satisfies(MathObject element) {
                return false;
            }

            @Override
            public boolean alwaysTrue() {
                return false;
            }

            @Override
            public boolean alwaysFalse() {
                return true;
            }

            @Override
            public String toString() {
                return "false";
            }
        });
    }

    @Override
    public MathObject takeAny() throws EmptySetException {
        throw new EmptySetException();
    }

    @Override
    public boolean contains(MathObject element) {
        return false;
    }

    @Override
    public Set intersect(Set other) {
        return this;
    }

    @Override
    public Set union(Set other) {
        return other;
    }

    @Override
    public boolean isSubset(Set other) {
        return true;
    }
}
