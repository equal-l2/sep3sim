package sep3.misc;

import java.util.HashMap;

// 要素集合の登録と検索ができるようなもの
public class Factory<Key, Value> {
	private HashMap<Key, Value> factory;

	public Factory() {
		if (factory == null) {
			factory = new HashMap<Key, Value>();
		}
	}
	public void makeItem(Key k, Value v) { factory.put(k, v); }
	public Value getItem(Key k)          { return factory.get(k); }
}
