package aljebra;

import aljebra.predicates.Predicate;

import java.util.ArrayList;
import java.util.List;

public class MathObject {

    private final List<Predicate> satisfiedByDefinitions;

    public MathObject() {
        satisfiedByDefinitions = new ArrayList<>();
    }

    public void addSatisfiedByDefinition(Predicate predicate) {
        satisfiedByDefinitions.add(predicate);
    }

    public boolean satisfies(Predicate predicate) {
        for (Predicate definition : satisfiedByDefinitions) {
            if(definition.negate().equals(predicate))
                return false;
        }

        for (Predicate definition : satisfiedByDefinitions) {
            if(definition.equals(predicate))
                return true;

        }
        return predicate.satisfies(this);
    }
}
