package dictionaryModule;

/**
 * Implementación del TDA Dictionary usando un arreglo dinámico.
 * Los pares clave-valor se almacenan en un arreglo que crece dinámicamente.
 *
 * @param <K> Tipo de las claves
 * @param <V> Tipo de los valores
 */
public class SimpleArrayDictionary<K, V> implements SimpleDictionary<K, V> {
    private static final int INITIAL_CAPACITY = 10;
    private SimpleEntry<K, V>[] entries;
    private int size;

    /**
     * Construye un nuevo diccionario basado en arreglo con capacidad inicial.
     */
    @SuppressWarnings("unchecked")
    public SimpleArrayDictionary() {
        this.entries = new SimpleEntry[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser null");
        }

        // Buscar si la clave ya existe
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)) {
                entries[i].setValue(value);
                return;
            }
        }

        // Si llegamos aquí, la clave no existe, agregarla
        if (size >= entries.length) {
            expandCapacity();
        }
        entries[size] = new SimpleEntry<>(key, value);
        size++;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)) {
                return entries[i].getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)) {
                V value = entries[i].getValue();
                // Desplazar elementos
                for (int j = i; j < size - 1; j++) {
                    entries[j] = entries[j + 1];
                }
                entries[size - 1] = null;
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] values() {
        V[] result = (V[]) new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = entries[i].getValue();
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public K[] keys() {
        K[] result = (K[]) new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = entries[i].getKey();
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
    @SuppressWarnings("unchecked")
    public void clear() {
        entries = new SimpleEntry[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Expande la capacidad del arreglo cuando es necesario.
     */
    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        SimpleEntry<K, V>[] newEntries = new SimpleEntry[entries.length * 2];
        for (int i = 0; i < size; i++) {
            newEntries[i] = entries[i];
        }
        entries = newEntries;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SimpleEntry<K, V>[] entries() {
        SimpleEntry<K, V>[] result = new SimpleEntry[size];
        for (int i = 0; i < size; i++) {
            result[i] = entries[i];
        }
        return result;
    }

    /**
     * Obtiene la capacidad actual del arreglo.
     *
     * @return Capacidad del arreglo
     */
    public int getCapacity() {
        return entries.length;
    }
}
