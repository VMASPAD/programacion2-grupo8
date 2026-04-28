package queueModule;

/**
 * Implementación de cola circular usando un array dinámico.
 * Funciona según el principio FIFO (Primero en Entrar, Primero en Salir).
 * Los elementos se insertan al final (rear) y se extraen del inicio (front).
 * Utiliza índices circulares (front y rear) para reutilizar espacio sin desplazar elementos.
 * Cuando el array se llena, se reorganizan los elementos y se expande la capacidad.
 * @param <E> tipo genérico de elementos que almacena la cola
 */
public class SimpleArrayQueue<E> implements SimpleQueue<E> {
    private Object[] elements;
    private int front;  // Índice del primer elemento
    private int rear;   // Índice del último elemento
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public SimpleArrayQueue() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * Agrega un elemento al final (parte trasera) de la cola.
     * Utiliza índice circular para reutilizar espacio.
     * Si la cola está llena, expande su capacidad y reorganiza los elementos.
     */
    @Override
    public void enqueue(E element) {
        if (size == elements.length) {
            expandCapacity();
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        size++;
    }

    /**
     * Extrae y devuelve el elemento del frente (inicio) de la cola.
     * Utiliza índice circular para avanzar sin desplazar elementos.
     * Lanza excepción si la cola está vacía.
     */
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

    /**
     * Retorna el elemento del frente sin removerlo de la cola.
     * Lanza excepción si la cola está vacía.
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        @SuppressWarnings("unchecked")
        E element = (E) elements[front];
        return element;
    }

    /**
     * Elimina todos los elementos de la cola.
     */
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

    /**
     * Crea un nuevo array con el doble de capacidad.
     * Reorganiza los elementos eliminando espacios vacíos en el medio.
     * Reinicia los índices front y rear en posiciones lineales.
     */
    private void expandCapacity() {
        Object[] newElements = new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E element = (E) elements[(front + i) % elements.length];
            newElements[i] = element;
        }
        elements = newElements;
        front = 0;
        rear = size - 1;
    }
}
