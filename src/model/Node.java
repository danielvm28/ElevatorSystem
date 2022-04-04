package model;

public class Node<T> {
    private T element;
    private Node<T> next;
    private Node<T> previous;

    public Node(T element) {
        this.setElement(element);
        this.setNext(null);
        this.setPrevious(null);

    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}
