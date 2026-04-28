package queueModule;

/**
 * Interfaz que define un contrato para implementaciones de colas (FIFO).
 * Una cola funciona como "Primero en Entrar, Primero en Salir" (FIFO).
 * Se inserta elementos al final (rear) y se extrae del inicio (front).
 * @param <E> tipo genérico de elementos que almacena la cola
 */
public interface SimpleQueue<E> {
    /**
     * Agrega un elemento al final (parte trasera) de la cola.
     * @param element elemento a insertar
     */
    void enqueue(E element);
    
    /**
     * Extrae y devuelve el elemento del inicio (frente) de la cola.
     * @return el elemento más antiguedad de la cola
     */
    E dequeue();
    
    /**
     * Retorna el elemento del frente sin removerlo.
     * @return el elemento que será desencolado próximamente
     */
    E peek();
    
    /**
     * Elimina todos los elementos de la cola.
     */
    void clear();
    
    /**
     * Retorna la cantidad de elementos en la cola.
     * @return tamaño actual de la cola
     */
    int size();
    
    /**
     * Verifica si la cola no contiene elementos.
     * @return true si está vacía, false si tiene elementos
     */
    boolean isEmpty();
}
