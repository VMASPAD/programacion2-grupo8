package dictionaryModule;

/**
 * Implementación del TDA Dictionary usando una lista enlazada.
 * Los pares clave-valor se almacenan en nodos enlazados.
 *
 * @param <K> Tipo de las claves
 * @param <V> Tipo de los valores
 */
public class SimpleLinkedDictionary<K, V> implements SimpleDictionary<K, V> {
    private Node<K, V> head;
    private int size;

    /**
     * Nodo interno para la lista enlazada.
     */
    private static class Node<K, V> {
        SimpleEntry<K, V> entry;
        Node<K, V> next;

        Node(SimpleEntry<K, V> entry) {
            this.entry = entry;
        }
    }

    /**
     * Construye un nuevo diccionario basado en lista enlazada.
     */
    public SimpleLinkedDictionary() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser null");
        }

        // Buscar si la clave ya existe
        Node<K, V> current = head;
        while (current != null) {
            if (current.entry.getKey().equals(key)) {
                current.entry.setValue(value);
                return;
            }
            current = current.next;
        }

        // Si llegamos aquí, la clave no existe, agregarla al inicio
        Node<K, V> newNode = new Node<>(new SimpleEntry<>(key, value));
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public V get(K key) {
        Node<K, V> current = head;
        while (current != null) {
            if (current.entry.getKey().equals(key)) {
                return current.entry.getValue();
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (head == null) {
            return null;
        }

        // Si el nodo a eliminar es el head
        if (head.entry.getKey().equals(key)) {
            V value = head.entry.getValue();
            head = head.next;
            size--;
            return value;
        }

        // Buscar en el resto de la lista
        Node<K, V> current = head;
        while (current.next != null) {
            if (current.next.entry.getKey().equals(key)) {
                V value = current.next.entry.getValue();
                current.next = current.next.next;
                size--;
                return value;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        Node<K, V> current = head;
        while (current != null) {
            if (current.entry.getKey().equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] values() {
        V[] result = (V[]) new Object[size];
        int index = 0;
        Node<K, V> current = head;
        while (current != null) {
            result[index++] = current.entry.getValue();
            current = current.next;
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public K[] keys() {
        K[] result = (K[]) new Object[size];
        int index = 0;
        Node<K, V> current = head;
        while (current != null) {
            result[index++] = current.entry.getKey();
            current = current.next;
        }
        return result;
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
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SimpleEntry<K, V>[] entries() {
        SimpleEntry<K, V>[] result = new SimpleEntry[size];
        int index = 0;
        Node<K, V> current = head;
        while (current != null) {
            result[index++] = current.entry;
            current = current.next;
        }
        return result;
    }
}
