import aljebra.MathObject;
import aljebra.exceptions.EmptySetException;
import aljebra.predicates.Predicate;
import aljebra.sets.Set;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSets {

    private Predicate predicate;

    @Before
    public void init() {
        predicate = new Predicate() {

            @Override
            public boolean satisfies(MathObject element) {
                return element.getClass().getSimpleName().contains("a");
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
                return "NameContainsA(x)";
            }
        };
    }

    @Test
    public void unionWithComplement() {
        Set randomObjects = Set.fromPredicate(predicate);

        MathObject anyElement = new MathObject();
        assertEquals(true, randomObjects.union(randomObjects.complement()).contains(anyElement));
    }

    @Test
    public void unionOfDifferentSetsContainsAllTheElementsOfTheSets() throws EmptySetException {
        Set setA = Set.fromPredicate(predicate);

        Set setB = Set.fromPredicate(predicate.and(new Predicate() {
            @Override
            public boolean satisfies(MathObject element) {
                return element.getClass().getSimpleName().contains("b");
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
                return "NameContainsB(x)";
            }
        }));

        MathObject anyElementFromA = setA.takeAny();
        MathObject anyElementFromB = setB.takeAny();
        assertEquals(true, setA.union(setB).contains(anyElementFromA));
        assertEquals(true, setB.union(setA).contains(anyElementFromA));

        assertEquals(true, setA.union(setB).contains(anyElementFromB));
        assertEquals(true, setB.union(setA).contains(anyElementFromB));
    }
}
