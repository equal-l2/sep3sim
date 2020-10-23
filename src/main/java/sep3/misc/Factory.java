package sep3.misc;

import java.util.HashMap;

// 要素集合の登録と検索ができるようなもの
public class Factory<Key, Value> {
	private final HashMap<Key, Value> factory;

	public Factory() {
		factory = new HashMap<>();
	}

	public void makeItem(Key k, Value v) {
		factory.put(k, v);
	}

	public Value getItem(Key k) {
		return factory.get(k);
	}
}
