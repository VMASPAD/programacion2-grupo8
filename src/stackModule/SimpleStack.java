package stackModule;

/**
 * Interfaz que define un contrato para implementaciones de pilas (LIFO).
 * Una pila funciona como "Último en Entrar, Primero en Salir" (LIFO).
 * Se inserta y se extrae elementos siempre de la parte superior (top).
 * @param <E> tipo genérico de elementos que almacena la pila
 */
public interface SimpleStack<E> {
    /**
     * Agrega un elemento al tope de la pila.
     * @param element elemento a insertar
     */
    void push(E element);
    
    /**
     * Extrae y devuelve el elemento del tope de la pila.
     * @return el elemento removido del tope
     */
    E pop();
    
    /**
     * Retorna el elemento del tope sin removerlo de la pila.
     * @return el elemento en el tope
     */
    E peek();
    
    /**
     * Elimina todos los elementos de la pila.
     */
    void clear();
    
    /**
     * Retorna la cantidad de elementos en la pila.
     * @return tamaño actual de la pila
     */
    int size();
    
    /**
     * Verifica si la pila no contiene elementos.
     * @return true si está vacía, false si tiene elementos
     */
    boolean isEmpty();
}
