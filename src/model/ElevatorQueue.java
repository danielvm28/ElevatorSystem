package model;

public class ElevatorQueue<E> {
	// Attributes
	private Stack<E> up;
	private Stack<E> down;
	private boolean goingUp;
	private int size;

	public ElevatorQueue() {
		up = new Stack<>();
		down = new Stack<>();
		size = 0;
		goingUp = true;
	}

	public ElevatorQueue(int size) {
		up = new Stack<>();
		down = new Stack<>();
		size = 0;
		goingUp = true;
	}

	public boolean isEmpty() {
		return (size == 0) ? true : false;
	}

	public int size() {
		return size;
	}

	public void add(E e) {
		if(goingUp) {
			up.add(e);
		} else {
			down.add(e);
		}

		size += 1;
	}

	public E peek() {
		if (goingUp) {
			return up.peek();
		} else {
			return down.peek();
		}
	}

	public E operate() {
		E returnValue;

		if (goingUp) {
			returnValue = up.pop();
			down.add(returnValue);

			if (up.size() == 0) {
				// To avoid repeating the last and first floors
				E last = down.pop();
				up.add(last);

				goingUp = false;
			}
		} else {
			returnValue = down.pop();
			up.add(returnValue);

			if (down.size() == 0) {
				// To avoid repeating the last and first floors
				E last = up.pop();
				down.add(last);

				goingUp = true;
			}
		}

		return returnValue;
	}
}
