package aljebra.objects;

import aljebra.MathObject;
import aljebra.objects.numbers.IntegerNumber;
import aljebra.sets.Set;

public class Tuple extends Family {

    private final MathObject[] elements;

    public Tuple(MathObject... elements) {
        super(createFunction(elements));
        this.elements = elements;
    }

    private static Function createFunction(MathObject[] elements) {
        Set domain = Set.isInstanceOf(IntegerNumber.class);
        Set range = Set.fromValues(elements);
        return new Function(domain, range) {
            @Override
            protected MathObject calculateImage(MathObject o) {
                ArrayElement element = new ArrayElement(elements, (IntegerNumber)o);
                element.addSatisfiedByDefinition(range.getDefinitionPredicate());
                return element;
            }
        };
    }

    public MathObject[] getElements() {
        return elements;
    }
}
