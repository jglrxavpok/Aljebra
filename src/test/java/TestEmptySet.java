import aljebra.MathObject;
import aljebra.exceptions.EmptySetException;
import aljebra.predicates.Predicate;
import aljebra.sets.Set;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEmptySet {

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
    public void emptyIntersection() throws EmptySetException {
        Set randomObjects = Set.fromPredicate(predicate);

        Set complement = randomObjects.complement();

        MathObject fromSet = randomObjects.takeAny();
        MathObject fromComplement = complement.takeAny();
        System.out.println("!!!A "+fromSet.satisfies(predicate.negate()));
        System.out.println("!!!B "+fromComplement.satisfies(predicate));

        System.out.println("A inter B contains 'fromSet' : "+randomObjects.intersect(complement).contains(fromSet));
        System.out.println("A inter B contains 'fromComplement' : "+randomObjects.intersect(complement).contains(fromComplement));

        System.out.println("A is subset of B: "+randomObjects.isSubset(complement));
        System.out.println("B is subset of A: "+complement.isSubset(randomObjects));
        assertEquals(Set.EMPTY_SET, randomObjects.intersect(complement));
    }

    @Test
    public void intersection() {
        Set allMathObjects = Set.fromPredicate(new Predicate() {

            @Override
            public boolean satisfies(MathObject element) {
                return true;
            }

            @Override
            public boolean alwaysTrue() {
                return true;
            }

            @Override
            public boolean alwaysFalse() {
                return false;
            }

            @Override
            public String toString() {
                return "true";
            }
        });

        assertEquals(Set.EMPTY_SET, allMathObjects.intersect(Set.EMPTY_SET));
    }

    @Test
    public void union() {
        Set allMathObjects = Set.fromPredicate(new Predicate() {

            @Override
            public boolean satisfies(MathObject element) {
                return true;
            }

            @Override
            public boolean alwaysTrue() {
                return true;
            }

            @Override
            public boolean alwaysFalse() {
                return false;
            }

            @Override
            public String toString() {
                return "true";
            }
        });

        assertEquals(allMathObjects, allMathObjects.union(Set.EMPTY_SET));
    }
}
