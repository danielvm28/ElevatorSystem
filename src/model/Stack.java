package model;

public class Stack<E> {
	// Attributes
	private Node<E> head;
	private int size;
	
	public Stack() {
		head = null;
		size = 0;
	}
	
	public boolean isEmpty() {
        return (head == null) ? true : false;
    }

    public int size() {
        return size;
    }


    public void add(E e) {

        Node<E> node = new Node<E>(e);

        if (size == 0) {
            head = node;
        } else {
            node.setNext(head);
            head.setPrevious(node);
        
            head = node;
        }

        size += 1;
    }

    public int indexOf(E e) {
        return indexOf(e, head, 0);
    }

    private int indexOf(E e, Node<E> temp, int contador) {
        if (e.equals(temp.getElement())) {
            return contador;

        } else {
            return indexOf(e, temp.getNext(), contador + 1);
        }
    }

    public E get(int index) {

        return get(index, head);

    }

    private E get(int index, Node<E> temp) {

        if (index == 0) {
            return temp.getElement();

        } else {
            return get(index - 1, temp.getNext());
        }

    }
    
    public E peek() {
    	return head.getElement();
    }
    
    public E pop() {
    	E returnValue = head.getElement();
    	
        if (size == 1) {
            head = null;
        } else {
            Node<E> nextNode = head.getNext();
    	    nextNode.setPrevious(null);
    	    head = nextNode;
        }
    	
    	size--;

    	return returnValue;
    }
}
