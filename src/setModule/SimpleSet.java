package setModule;

/**
 * Interfaz que define un contrato para implementaciones de conjuntos.
 * Un conjunto es una colección que no permite elementos duplicados.
 * Soporta operaciones matemáticas: unión, intersección y diferencia.
 * @param <E> tipo genérico de elementos que almacena el conjunto
 */
public interface SimpleSet<E> {
    /**
     * Agrega un elemento al conjunto.
     * @param element elemento a insertar
     * @return true si se agregó, false si ya existía
     */
    boolean add(E element);
    
    /**
     * Elimina un elemento del conjunto.
     * @param element elemento a remover
     * @return true si existía y se removió, false si no estaba
     */
    boolean remove(E element);
    
    /**
     * Verifica si el elemento está en el conjunto.
     * @param element elemento a buscar
     * @return true si está presente, false si no
     */
    boolean contains(E element);
    
    /**
     * Elimina todos los elementos del conjunto.
     */
    void clear();
    
    /**
     * Verifica si el conjunto no tiene elementos.
     * @return true si está vacío, false si tiene elementos
     */
    boolean isEmpty();
    
    /**
     * Retorna la cantidad de elementos en el conjunto.
     * @return tamaño del conjunto
     */
    int size();
    
    /**
     * Convierte el conjunto a un array.
     * @return array con todos los elementos del conjunto
     */
    E[] toArray();
    
    /**
     * Calcula la unión con otro conjunto.
     * La unión contiene todos los elementos de ambos conjuntos.
     * @param other otro conjunto
     * @return nuevo conjunto con la unión
     */
    SimpleSet<E> unionWith(SimpleSet<E> other);
    
    /**
     * Calcula la intersección con otro conjunto.
     * La intersección contiene solo elementos que están en ambos.
     * @param other otro conjunto
     * @return nuevo conjunto con los elementos comunes
     */
    SimpleSet<E> intersectWith(SimpleSet<E> other);
    
    /**
     * Calcula la diferencia con otro conjunto.
     * El resultado contiene elementos de este conjunto que NO están en otro.
     * @param other otro conjunto
     * @return nuevo conjunto con la diferencia
     */
    SimpleSet<E> differenceWith(SimpleSet<E> other);
}
