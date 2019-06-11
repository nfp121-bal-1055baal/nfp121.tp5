package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

	protected java.util.Vector<T> table = new java.util.Vector<T>();

	public int size() {
		return table.size();
	}

	public Iterator<T> iterator() {
		return table.iterator();
	}

	public boolean add(T t) {
		/* if(!this.contains(t)
			return this.add(t); */ //throws StackOverFlowException
		if(!table.contains(t))
			return table.add(t);
		return false;
	}

	public Ensemble<T> union(Ensemble<? extends T> e) {
		Ensemble<T> ens = new Ensemble<T>();
		ens.addAll(table);
		ens.addAll(e);
		return ens;
	}

	public Ensemble<T> inter(Ensemble<? extends T> e) {
		Ensemble<T> ens = new Ensemble<T>();
		ens.addAll(table);
		ens.retainAll(e);
		return ens;

	}

	public Ensemble<T> diff(Ensemble<? extends T> e) {
		Ensemble<T> ens = new Ensemble<T>();
		ens.addAll(table);
		ens.removeAll(e);
		return ens;
	}

	Ensemble<T> diffSym(Ensemble<? extends T> e) {
		Ensemble<T> ens1 = new Ensemble<T>();
		Ensemble<T> ens2 = new Ensemble<T>();
		ens1.addAll(table);
		ens2.addAll(e);
		ens1.removeAll(e);
		ens2.removeAll(table);
		ens1.addAll(ens2);
		
		return ens1;

	}
	
}
