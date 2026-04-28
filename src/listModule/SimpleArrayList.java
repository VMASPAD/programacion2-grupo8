package listModule;

/**
 * Implementación de lista usando un array dinámico.
 * Los elementos se almacenan en posiciones contiguas de memoria.
 * Cuando el array se llena, se expande automáticamente al doble de tamaño.
 * Ventajas: acceso rápido a elementos por índice (O(1))
 * Desventajas: inserciones y eliminaciones en el medio requieren desplazar elementos (O(n))
 * @param <E> tipo genérico de elementos que almacena la lista
 */
public class SimpleArrayList<E> implements SimpleList<E> {
    private Object[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public SimpleArrayList() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     * Si el array está lleno, expande su capacidad automáticamente.
     */
    @Override
    public boolean add(E element) {
        if (size == elements.length) {
            expandCapacity();
        }
        elements[size] = element;
        size++;
        return true;
    }

    /**
     * Inserta un elemento en una posición específica.
     * Los elementos posteriores se desplazan una posición a la derecha.
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }
        
        if (size == elements.length) {
            expandCapacity();
        }

        // Desplaza elementos hacia la derecha a partir de la posición index
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * Elimina y devuelve el elemento en la posición indicada.
     * Los elementos posteriores se desplazan una posición a la izquierda.
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }

        @SuppressWarnings("unchecked")
        E element = (E) elements[index];

        // Desplaza elementos hacia la izquierda si no es el último elemento
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        elements[size - 1] = null;
        size--;
        return element;
    }

    /**
     * Busca la primera ocurrencia del objeto y la elimina.
     */
    @Override
    public boolean remove(Object object) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && object == null) || 
                (elements[i] != null && elements[i].equals(object))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Limpia la lista eliminando todas las referencias a elementos.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Verifica si el elemento está presente en la lista.
     * Compara usando equals() o null si el elemento es nulo.
     */
    @Override
    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && object == null) || 
                (elements[i] != null && elements[i].equals(object))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene el elemento en la posición indicada sin removerlo.
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }
        @SuppressWarnings("unchecked")
        E element = (E) elements[index];
        return element;
    }

    /**
     * Reemplaza el elemento en una posición con uno nuevo.
     * Devuelve el elemento anterior.
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }
        @SuppressWarnings("unchecked")
        E oldElement = (E) elements[index];
        elements[index] = element;
        return oldElement;
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
     * Se usa internamente cuando la lista se llena.
     */
    private void expandCapacity() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
