package solution;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	private Map<Integer, Node> elements = new HashMap<Integer, Node>();
	private Node head = null;
	private Node tail = null;
	private final int capacity;

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (!elements.containsKey(key))
			return -1;
		Node node = elements.get(key);
		remove(node);
		setHead(node);
		return node.value;
	}

	public void set(int key, int value) {
		if (elements.containsKey(key)) {
			Node node = elements.get(key);
			node.value = value;
			remove(node);
			setHead(node);
		} else {
			Node node = new Node(key, value);
			if (elements.size() >= capacity) {
				elements.remove(tail.key);
				remove(tail);
			}
			elements.put(key, node);
			setHead(node);
		}
	}

	public void remove(Node node) {
		if (node.previous == null) {
			head = node.next;
		} else {
			node.previous.next = node.next;
		}

		if (node.next == null) {
			tail = node.previous;
		} else {
			node.next.previous = node.previous;
		}
	}

	public void setHead(Node node) {
		node.previous = null;
		node.next = head;
		if (head != null)
			head.previous = node;
		
		head = node;
		
		if (tail == null)
			tail = head;
	}

	private static class Node {
		final int key;
		int value;
		Node next;
		Node previous;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
			this.next = null;
			this.previous = null;
		}
	}
}