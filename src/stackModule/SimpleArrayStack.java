package stackModule;

public class SimpleArrayStack<E> implements SimpleStack<E> {
    private Object[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public SimpleArrayStack() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    // Recibe un elemento, lo coloca en el tope (expande si es necesario)
    @Override
    public void push(E element) {
        if (size == elements.length) {
            expandCapacity();
        }
        elements[size] = element;
        size++;
    }

    // Verifica que no esté vacía, decrementa size, devuelve el elemento del tope
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

    // Verifica que no esté vacía, devuelve el tope sin removerlo
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        @SuppressWarnings("unchecked")
        E element = (E) elements[size - 1];
        return element;
    }

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

    private void expandCapacity() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
