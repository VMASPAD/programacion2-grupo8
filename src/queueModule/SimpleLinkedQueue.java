package queueModule;

public class SimpleLinkedQueue<E> implements SimpleQueue<E> {
    private Node<E> front;
    private Node<E> rear;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public SimpleLinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Recibe un elemento, crea nodo nuevo, lo enlaza al final de la cola
    @Override
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);
        
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    // Verifica que no esté vacía, obtiene dato del frente, mueve front al siguiente, devuelve dato
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        E data = front.data;
        front = front.next;
        size--;
        
        if (isEmpty()) {
            rear = null;
        }
        return data;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return front.data;
    }

    @Override
    public void clear() {
        front = null;
        rear = null;
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
}
