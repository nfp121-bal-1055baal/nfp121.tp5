package question1;

public class EnsembleTest extends junit.framework.TestCase {

	public void testUnion() {
		question1.Ensemble<Integer> e1, e2;
		e1 = new question1.Ensemble<Integer>();
		assertEquals(true, e1.add(2));
		assertEquals(true, e1.add(3));

		e2 = new question1.Ensemble<Integer>();
		assertEquals(true, e2.add(3));
		assertEquals(true, e2.add(4));

		question1.Ensemble<Integer> union = e1.union(e2);
		assertEquals(3, union.size());
		assertTrue(union.contains(2));
		assertTrue(union.contains(3));
		assertTrue(union.contains(4));
		
	}
	public void testInter(){
		question1.Ensemble<Integer> e1, e2;
		e1 = new question1.Ensemble<Integer>();
		assertEquals(true, e1.add(2));
		assertEquals(true, e1.add(3));

		e2 = new question1.Ensemble<Integer>();
		assertEquals(true, e2.add(3));
		assertEquals(true, e2.add(4));

		
		question1.Ensemble<Integer> inter = e1.inter(e2);
		assertEquals(1, inter.size());
		assertTrue(inter.contains(3));
		assertTrue(!inter.contains(2));
	}
	
	public void testDiff(){
		question1.Ensemble<Integer> e1, e2;
		e1 = new question1.Ensemble<Integer>();
		assertEquals(true, e1.add(2));
		assertEquals(true, e1.add(3));

		e2 = new question1.Ensemble<Integer>();
		assertEquals(true, e2.add(3));
		assertEquals(true, e2.add(4));

		question1.Ensemble<Integer> diff = e1.diff(e2);
		assertEquals(1, diff.size());
		assertTrue(diff.contains(2));
		assertTrue(!diff.contains(4));
		
	}
	
	public void testDiffSym(){
		question1.Ensemble<Integer> e1, e2;
		e1 = new question1.Ensemble<Integer>();
		assertEquals(true, e1.add(2));
		assertEquals(true, e1.add(3));

		e2 = new question1.Ensemble<Integer>();
		assertEquals(true, e2.add(3));
		assertEquals(true, e2.add(4));

		question1.Ensemble<Integer> diffSym = e1.diffSym(e2);
		assertEquals(2, diffSym.size());
		assertTrue(diffSym.contains(2));
		assertTrue(diffSym.contains(4));
		assertTrue(!diffSym.contains(3));
	}
	
}
