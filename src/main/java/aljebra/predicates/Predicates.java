package aljebra.predicates;

import aljebra.MathObject;

import java.util.Arrays;
import java.util.List;

public class Predicates {
    public static Predicate equality(MathObject object) {
        return new Predicate() {
            @Override
            public boolean satisfies(MathObject element) {
                return object.equals(element);
            }

            @Override
            public boolean alwaysTrue() {
                return false;
            }

            @Override
            public boolean alwaysFalse() {
                return false;
            }

            @Override
            public String toString() {
                return "(x == "+object.toString()+")";
            }
        };
    }

    public static Predicate isInList(List<MathObject> objects) {
        return new Predicate() {
            @Override
            public boolean satisfies(MathObject element) {
                return objects.contains(element);
            }

            @Override
            public boolean alwaysTrue() {
                return false;
            }

            @Override
            public boolean alwaysFalse() {
                return objects.isEmpty();
            }

            @Override
            public String toString() {
                return "(x is in "+ Arrays.toString(objects.toArray())+")";
            }
        };
    }
}
