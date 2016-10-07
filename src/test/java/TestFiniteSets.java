import aljebra.MathObject;
import aljebra.sets.Set;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestFiniteSets {

    @Test
    public void testSingletonContainsValue() {
        MathObject object = new MathObject();
        Set singleton = Set.singleton(object);

        assertTrue(singleton.contains(object));
    }

    @Test
    public void testListsContainsValue() {
        List<MathObject> objects = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            objects.add(new MathObject());
        }
        Set set = Set.fromList(objects);
        for (MathObject o : objects) {
            assertTrue(set.contains(o));
        }
    }

    @Test
    public void emptyListLeadsToEmptySet() {
        List<MathObject> objects = new ArrayList<>();

        Set set = Set.fromList(objects);
        assertTrue(set.isEmpty());
    }
}
