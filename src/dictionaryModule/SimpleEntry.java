package dictionaryModule;

/**
 * Clase que representa un par clave-valor en el diccionario.
 * Inmutable después de su creación.
 *
 * @param <K> Tipo de la clave
 * @param <V> Tipo del valor
 */
public class SimpleEntry<K, V> {
    private final K key;
    private V value;

    /**
     * Crea un nuevo par clave-valor.
     *
     * @param key   La clave (no puede ser null)
     * @param value El valor (puede ser null)
     */
    public SimpleEntry(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser null");
        }
        this.key = key;
        this.value = value;
    }

    /**
     * Obtiene la clave del par.
     *
     * @return La clave
     */
    public K getKey() {
        return key;
    }

    /**
     * Obtiene el valor del par.
     *
     * @return El valor
     */
    public V getValue() {
        return value;
    }

    /**
     * Establece el valor del par.
     *
     * @param value El nuevo valor
     */
    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
