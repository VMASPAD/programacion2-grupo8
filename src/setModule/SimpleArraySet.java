package setModule;

/**
 * Implementación de conjunto usando un array dinámico sin elementos duplicados.
 * Un conjunto no permite elementos repetidos.
 * Soporta operaciones matemáticas como unión, intersección y diferencia.
 * El array se expande automáticamente cuando se llena.
 * @param <E> tipo genérico de elementos que almacena el conjunto
 */
public class SimpleArraySet<E> implements SimpleSet<E> {
    private Object[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public SimpleArraySet() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Agrega un elemento al conjunto.
     * Si el elemento ya existe, no se agrega y retorna false.
     * Si el array está lleno, expande su capacidad automáticamente.
     */
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

    /**
     * Elimina un elemento del conjunto.
     * Los elementos posteriores se desplazan para llenar el espacio.
     * Retorna true si el elemento existía y fue removido.
     */
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

    /**
     * Verifica si el elemento está presente en el conjunto.
     */
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

    /**
     * Elimina todos los elementos del conjunto.
     */
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

    /**
     * Convierte el conjunto a un array.
     */
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

    /**
     * Calcula la unión con otro conjunto.
     * La unión contiene todos los elementos de ambos conjuntos.
     * Los duplicados se descartan automáticamente al agregar.
     */
    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleArraySet<>();
        
        // Agrega todos los elementos de este conjunto
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E element = (E) elements[i];
            result.add(element);
        }
        
        // Agrega todos los elementos del otro conjunto (no agrega duplicados)
        E[] otherElements = other.toArray();
        for (E element : otherElements) {
            result.add(element);
        }
        
        return result;
    }

    /**
     * Calcula la intersección con otro conjunto.
     * La intersección contiene solo elementos que están en ambos conjuntos.
     */
    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleArraySet<>();
        
        // Agrega solo elementos que también están en el otro conjunto
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E element = (E) elements[i];
            if (other.contains(element)) {
                result.add(element);
            }
        }
        
        return result;
    }

    /**
     * Calcula la diferencia con otro conjunto.
     * El resultado contiene elementos de este conjunto que NO están en otro.
     */
    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleArraySet<>();
        
        // Agrega elementos de este que no están en el otro
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E element = (E) elements[i];
            if (!other.contains(element)) {
                result.add(element);
            }
        }
        
        return result;
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
