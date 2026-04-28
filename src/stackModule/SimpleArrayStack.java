package stackModule;

/**
 * Implementación de pila usando un array dinámico.
 * Funciona según el principio LIFO (Último en Entrar, Primero en Salir).
 * Los elementos se insertan y extraen siempre del tope (final del array).
 * El array se expande automáticamente cuando se llena.
 * @param <E> tipo genérico de elementos que almacena la pila
 */
public class SimpleArrayStack<E> implements SimpleStack<E> {
    private Object[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public SimpleArrayStack() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Agrega un elemento al tope de la pila.
     * Si el array está lleno, expande su capacidad automáticamente.
     */
    @Override
    public void push(E element) {
        if (size == elements.length) {
            expandCapacity();
        }
        elements[size] = element;
        size++;
    }

    /**
     * Extrae y devuelve el elemento del tope de la pila.
     * Lanza excepción si la pila está vacía.
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        size--;
        @SuppressWarnings("unchecked")
        E element = (E) elements[size];
        elements[size] = null;
        return element;
    }

    /**
     * Retorna el elemento del tope sin removerlo de la pila.
     * Lanza excepción si la pila está vacía.
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        @SuppressWarnings("unchecked")
        E element = (E) elements[size - 1];
        return element;
    }

    /**
     * Elimina todos los elementos de la pila.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
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
     * Crea un nuevo array con el doble de capacidad y copia los elementos.
     */
    private void expandCapacity() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
