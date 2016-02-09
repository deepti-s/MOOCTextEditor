package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// Create sentinel nodes
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		initialize();
	}

	void initialize() {
		size = 0;
		// point head's next to tail; and tail's prev to head
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		add(size(), element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
		}
		return node(index).data;
	}

	/**
	 * Returns the (non-null) Node at the specified element index.
	 */
	LLNode<E> node(int index) {
		LLNode<E> result = head;
		//determine if given index is in the lower end (half) or higher end (half) of list
		int midpoint = size/2;
		if (index < midpoint) {
			// start from beginning of list i.e., from head
			LLNode<E> x = head;
			int i = 0;
			while (i <= index) {
				x = x.next;
				i++;
			}
			return x;
		} else {
			// start from end of list i.e., from tail
			LLNode<E> x = tail;
			int i = size - 1;
			while (i >= index) {
				x = x.prev;
				i--;
			}
			return x;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
		}

		if (index == size) {
			// link towards last
			linkLast(element);
		} else {
			// link before the element at current index
			LLNode<E> existingNode = node(index);
			linkBefore(element, existingNode);
		}
	}

	void linkLast(E element) {
		linkBefore(element, tail);
	}

	void linkBefore(E element, LLNode<E> successorNode) {
		LLNode<E> predecessorNode = successorNode.prev;
		LLNode<E> newNode = new LLNode<E>(predecessorNode, element, successorNode);

		predecessorNode.next = newNode;
		successorNode.prev = newNode;

		size++;
	}

	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
		}
		return unlink(node(index));
	}

	E unlink(LLNode<E> node) {
		E data = node.data;
		LLNode<E> predecessorNode = node.prev;
		LLNode<E> successorNode = node.next;

		predecessorNode.next = successorNode;
		successorNode.prev = predecessorNode;

		node.prev = null;
		node.next = null;
		node.data = null;

		size--;
		return data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
		}

		LLNode<E> node = node(index);
		E oldElement = node.data;
		node.data = element;

		return oldElement;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.prev = null;
		this.data = e;
		this.next = null;
	}

	public LLNode(LLNode<E> prev, E data, LLNode<E> next) {
		this.prev = prev;
		this.data = data;
		this.next = next;
	}
}
