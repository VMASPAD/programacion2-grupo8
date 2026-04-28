package listModule;

/**
 * Interfaz que define un contrato para implementaciones de listas.
 * Una lista es una colección bidireccional donde se pueden agregar, eliminar y recuperar elementos.
 * Cada elemento ocupa una posición indexada (0, 1, 2, ...).
 * @param <E> tipo genérico de elementos que almacena la lista
 */
public interface SimpleList<E> {
    /**
     * Agrega un elemento al final de la lista.
     * @param element elemento a agregar
     * @return true si se agregó exitosamente
     */
    boolean add(E element);
    
    /**
     * Inserta un elemento en una posición específica.
     * Los elementos posteriores se desplazan hacia la derecha.
     * @param index posición donde insertar (0 al inicio, size al final)
     * @param element elemento a insertar
     */
    void add(int index, E element);
    
    /**
     * Elimina y devuelve el elemento en la posición indicada.
     * @param index posición del elemento a remover
     * @return el elemento removido
     */
    E remove(int index);
    
    /**
     * Elimina la primera ocurrencia del objeto especificado.
     * @param object elemento a buscar y remover
     * @return true si se encontró y removió, false si no existe
     */
    boolean remove(Object object);
    
    /**
     * Elimina todos los elementos de la lista.
     */
    void clear();
    
    /**
     * Verifica si la lista contiene el elemento especificado.
     * @param object elemento a buscar
     * @return true si está presente, false si no
     */
    boolean contains(Object object);
    
    /**
     * Obtiene el elemento en la posición indicada sin removerlo.
     * @param index posición del elemento
     * @return el elemento en esa posición
     */
    E get(int index);
    
    /**
     * Reemplaza el elemento en una posición con otro nuevo.
     * @param index posición a modificar
     * @param element nuevo elemento
     * @return el elemento anterior
     */
    E set(int index, E element);
    
    /**
     * Retorna la cantidad total de elementos en la lista.
     * @return tamaño de la lista
     */
    int size();
    
    /**
     * Verifica si la lista no tiene elementos.
     * @return true si está vacía, false si tiene elementos
     */
    boolean isEmpty();
}
