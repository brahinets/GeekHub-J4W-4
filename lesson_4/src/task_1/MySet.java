package task_1;

import java.util.HashSet;
import java.util.Set;

/**
 * Class: MySet
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 02.11.13
 * Time: 11:44
 * Mail: ysb.kanivtsi@gmail.com
 *
 *
 * Realisation of few operations on sets
 * - check IsEquals
 * - find Union
 * - find Subtraction
 * - find Intersection
 * - find Symmetric Subtraction
 */

public class MySet implements SetOperations {


    /*
     *  Two sets A and B are equal (A = B) if they are composed of the same elements
     */
    @Override
    public boolean equals(Set a, Set b) {

        if (b.size() != a.size())
            return false;

        if (!a.containsAll(b) || !b.containsAll(a))
            return false;

        return true;
    }

    /*
     *  The union (sum) of A and B is the set A ∪ B, elements which belong to at least one of these sets
     */
    @Override
    public Set union(Set a, Set b) {
        Set union = new HashSet(a);
        union.addAll(b);

        return union;
    }

    /*
     *   The difference of sets A and B is the set AB, whose elements belong to A, but do not belong to the B
     */
    @Override
    public Set subtract(Set a, Set b) {
        Set difference = new HashSet(a);
        difference.removeAll(b);

        return difference;
    }

    /*
     *  Intersection (product) of A and B is the set A ∩ B, elements which belong to both the set A and set B
     */
    @Override
    public Set intersect(Set a, Set b) {


        Set union = new HashSet(a);
        union.addAll(b);

        Set difference = new HashSet(symmetricSubtract(a,b));
        union.removeAll(difference);

        return union;
    }

    /*
     *  Symmetric difference of sets A and B is the set AΔB the union of the sets of differences AB and BA, that is, A Δ B = (AB) ∪ (BA)
     */
    @Override
    public Set symmetricSubtract(Set a, Set b) {

        Set differenceAB = new HashSet(a);
        differenceAB.removeAll(b);

        Set differenceBA = new HashSet(b);
        differenceBA.removeAll(a);

        Set symmetricSubtract = new HashSet(differenceAB);
        symmetricSubtract.addAll(differenceBA);

        return symmetricSubtract;
    }
}
