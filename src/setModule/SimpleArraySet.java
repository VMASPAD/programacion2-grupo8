package setModule;

public class SimpleArraySet<E> implements SimpleSet<E> {
    private Object[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public SimpleArraySet() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    // Recibe un elemento, verifica que no exista, lo agrega, devuelve si fue exitosa
    @Override
    public boolean add(E element) {
        if (contains(element)) {
            return false;
        }
        if (size == elements.length) {
            expandCapacity();
        }
        elements[size] = element;
        size++;
        return true;
    }

    // Recibe un elemento, lo busca, lo remueve si existe, devuelve si fue exitosa
    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && element == null) || 
                (elements[i] != null && elements[i].equals(element))) {
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && element == null) || 
                (elements[i] != null && elements[i].equals(element))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        @SuppressWarnings("unchecked")
        E[] array = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E element = (E) elements[i];
            array[i] = element;
        }
        return array;
    }

    // Recibe otro Set, devuelve nuevo Set con elementos de ambos
    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleArraySet<>();
        
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E element = (E) elements[i];
            result.add(element);
        }
        
        E[] otherElements = other.toArray();
        for (E element : otherElements) {
            result.add(element);
        }
        
        return result;
    }

    // Recibe otro Set, devuelve nuevo Set con elementos comunes
    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleArraySet<>();
        
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E element = (E) elements[i];
            if (other.contains(element)) {
                result.add(element);
            }
        }
        
        return result;
    }

    // Recibe otro Set, devuelve nuevo Set con elementos de este que no están en other
    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleArraySet<>();
        
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E element = (E) elements[i];
            if (!other.contains(element)) {
                result.add(element);
            }
        }
        
        return result;
    }

    private void expandCapacity() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
