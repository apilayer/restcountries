package fayder.restcountries.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

// Acts like a SortedMap, but allows associating several values to the same key.
public class SortedMultimap<K extends Comparable<K>,V> {
	private SortedMap<K, List<V>> map;

	public SortedMultimap() {
		map = new TreeMap<>();
	}

	public SortedMultimap(boolean descendingOrder) {
		if(descendingOrder) {
			map = new TreeMap<>(new Comparator<K>() {
				public int compare(K k1, K k2) {
					return k2.compareTo(k1);
				}
			});
		} else {
			map = new TreeMap<>();
		}
	}
	
	public void put(K key, V value) {
    	if(map.containsKey(key)) {
    		map.get(key).add(value);
    	} else {
    		List<V> list = new ArrayList<>();
    		list.add(value);
    		map.put(key, list);
    	}
    }
	
	public List<V> values() {
		return flatten(map.values());
	}
    
    private static <V> List<V> flatten(Collection<List<V>> nested) {
    	List<V> result = new ArrayList<>();
    	for(List<V> list: nested) {
    		result.addAll(list);
    	}
    	return result;
    }

}