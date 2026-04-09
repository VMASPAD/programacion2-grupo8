package queueModule;

public class SimpleArrayQueue<E> implements SimpleQueue<E> {
    private Object[] elements;
    private int front;
    private int rear;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public SimpleArrayQueue() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(E element) {
        if (size == elements.length) {
            expandCapacity();
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        @SuppressWarnings("unchecked")
        E element = (E) elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        @SuppressWarnings("unchecked")
        E element = (E) elements[front];
        return element;
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void expandCapacity() {
        Object[] newElements = new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            newElements[i] = (E) elements[(front + i) % elements.length];
        }
        elements = newElements;
        front = 0;
        rear = size - 1;
    }
}
