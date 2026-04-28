package listModule;

/**
 * Implementación de lista usando nodos doblemente enlazados.
 * Cada nodo contiene datos y referencias al nodo anterior (prev) y siguiente (next).
 * El acceso a elementos requiere recorrer desde cabecera o cola.
 * Ventajas: inserciones y eliminaciones son eficientes si se tiene referencia al nodo (O(1))
 * Desventajas: acceso a elemento por índice requiere recorrido (O(n))
 * Nota: optimización - busca desde extremo más cercano al índice
 * @param <E> tipo genérico de elementos que almacena la lista
 */
public class SimpleLinkedList<E> implements SimpleList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Clase interna que representa un nodo en la lista doblemente enlazada.
     * Almacena el dato, referencia al nodo anterior y siguiente.
     */
    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public SimpleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     * Crea un nuevo nodo y lo enlaza como nuevo tail.
     */
    @Override
    public boolean add(E element) {
        Node<E> newNode = new Node<>(element);
        
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    /**
     * Inserta un elemento en una posición específica.
     * Encuentra el nodo en esa posición y lo reenlaza.
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }

        if (index == size) {
            add(element);
            return;
        }

        Node<E> newNode = new Node<>(element);
        Node<E> nodeAtIndex = getNode(index);

        newNode.next = nodeAtIndex;
        newNode.prev = nodeAtIndex.prev;

        if (nodeAtIndex.prev != null) {
            nodeAtIndex.prev.next = newNode;
        } else {
            head = newNode;
        }
        nodeAtIndex.prev = newNode;

        size++;
    }

    /**
     * Elimina y devuelve el elemento en la posición indicada.
     * Rompe los enlaces del nodo para sacarlo de la cadena.
     * Optimización: busca desde extremo más cercano.
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }

        Node<E> nodeToRemove = getNode(index);
        E data = nodeToRemove.data;

        if (nodeToRemove.prev != null) {
            nodeToRemove.prev.next = nodeToRemove.next;
        } else {
            head = nodeToRemove.next;
        }

        if (nodeToRemove.next != null) {
            nodeToRemove.next.prev = nodeToRemove.prev;
        } else {
            tail = nodeToRemove.prev;
        }

        size--;
        return data;
    }

    /**
     * Busca la primera ocurrencia del objeto y la elimina.
     * Recorre la lista desde la cabecera.
     */
    @Override
    public boolean remove(Object object) {
        Node<E> current = head;
        
        while (current != null) {
            if (
                (current.data != null && current.data.equals(object))) {
                
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Limpia la lista eliminando todas las referencias a nodos.
     * La memoria de los nodos se libera automáticamente.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Verifica si el elemento está presente en la lista.
     * Recorre hasta encontrar o llegar al final.
     */
    @Override
    public boolean contains(Object object) {
        Node<E> current = head;
        
        while (current != null) {
            if ((current.data == null && object == null) || 
                (current.data != null && current.data.equals(object))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Obtiene el elemento en la posición indicada.
     * Usa getNode optimizado que busca desde extremo más cercano.
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }
        return getNode(index).data;
    }

    /**
     * Reemplaza el elemento en una posición con uno nuevo.
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }

        Node<E> nodeAtIndex = getNode(index);
        E oldData = nodeAtIndex.data;
        nodeAtIndex.data = element;
        return oldData;
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
     * Encuentra el nodo en la posición indicada optimizando la búsqueda.
     * Si el índice está en la primera mitad, busca desde cabecera (head).
     * Si está en la segunda mitad, busca desde cola (tail).
     * Esto reduce el tiempo de búsqueda a la mitad en promedio.
     */
    private Node<E> getNode(int index) {
        if (index < size / 2) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            Node<E> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }
}
