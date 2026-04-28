package dictionaryModule;

/**
 * Interfaz SimpleDictionary - TDA Diccionario/Mapa.
 * Define las operaciones básicas de un diccionario que almacena pares clave-valor.
 * Garantiza que las claves son únicas en el diccionario.
 *
 * @param <K> Tipo de las claves
 * @param <V> Tipo de los valores
 */
public interface SimpleDictionary<K, V> {

    /**
     * Agrega un par clave-valor al diccionario.
     * Si la clave ya existe, reemplaza el valor asociado.
     *
     * @param key   La clave (no puede ser null)
     * @param value El valor (puede ser null)
     * @throws IllegalArgumentException si la clave es null
     */
    void put(K key, V value);

    /**
     * Obtiene el valor asociado a una clave.
     *
     * @param key La clave a buscar
     * @return El valor asociado a la clave, o null si no existe
     */
    V get(K key);

    /**
     * Elimina el par clave-valor asociado a la clave especificada.
     *
     * @param key La clave del par a eliminar
     * @return El valor que estaba asociado a la clave, o null si la clave no existía
     */
    V remove(K key);

    /**
     * Verifica si una clave existe en el diccionario.
     *
     * @param key La clave a verificar
     * @return true si la clave existe, false en caso contrario
     */
    boolean containsKey(K key);

    /**
     * Obtiene un arreglo con todos los valores del diccionario.
     *
     * @return Arreglo con todos los valores (vacío si el diccionario está vacío)
     */
    V[] values();

    /**
     * Obtiene un arreglo con todas las claves del diccionario.
     *
     * @return Arreglo con todas las claves (vacío si el diccionario está vacío)
     */
    K[] keys();

    /**
     * Verifica si el diccionario está vacío.
     *
     * @return true si no contiene pares clave-valor, false en caso contrario
     */
    boolean isEmpty();

    /**
     * Obtiene la cantidad de pares clave-valor en el diccionario.
     *
     * @return El número de elementos
     */
    int size();

    /**
     * Elimina todos los pares clave-valor del diccionario.
     */
    void clear();

    /**
     * Obtiene un arreglo con todos los pares clave-valor del diccionario.
     * Permite iterar sobre los entries sin problemas de casting.
     *
     * @return Arreglo de SimpleEntry con todos los pares (vacío si el diccionario está vacío)
     */
    SimpleEntry<K, V>[] entries();
}
