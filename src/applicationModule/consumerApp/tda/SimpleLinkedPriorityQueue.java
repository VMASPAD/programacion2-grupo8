package applicationModule.consumerApp.tda;

import java.util.NoSuchElementException;

/**
 * Cola de prioridad implementada con nodos enlazados y prioridad bidireccional.
 * Los elementos con mayor prioridad (menor valor numérico) se desencolan primero.
 * @param <E> Tipo de elemento a almacenar en la cola
 */
public class SimpleLinkedPriorityQueue<E> {
    private PriorityLinkedNode<E> first;
    private PriorityLinkedNode<E> last;
    private int size;

    /**
     * Encola un elemento con una prioridad específica.
     * Inserta el elemento en la posición correcta según su prioridad.
     * 
     * @param element Elemento a encolar
     * @param priority Prioridad del elemento (menor número = mayor prioridad)
     * @throws IllegalArgumentException si element es nulo
     */
    public void enqueue(E element, int priority) {
        if (element == null) {
            throw new IllegalArgumentException("Error del TDA: No se pueden encolar elementos nulos.");
        }

        PriorityLinkedNode<E> newNode = new PriorityLinkedNode<>(element, priority);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            PriorityLinkedNode<E> current = last;
            while (current != null && priority < current.priority) {
                current = current.prev;
            }

            if (current == null) {
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
            } else {
                newNode.next = current.next;
                newNode.prev = current;

                if (current.next != null) {
                    current.next.prev = newNode;
                } else {
                    last = newNode;
                }
                current.next = newNode;
            }
        }
        size++;
    }

    /**
     * Desencola y retorna el primer elemento (de mayor prioridad).
     * 
     * @return el elemento desencolado
     * @throws NoSuchElementException si la cola está vacía
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error del TDA: La cola está vacía.");
        }
        E value = first.value;
        first = first.next;

        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        size--;
        return value;
    }

    /**
     * Retorna el primer elemento sin desencolarlo.
     * 
     * @return el primer elemento
     * @throws NoSuchElementException si la cola está vacía
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error del TDA: La cola está vacía.");
        }
        return first.value;
    }

    /**
     * Obtiene la prioridad del primer elemento.
     * 
     * @return la prioridad del primer elemento
     * @throws NoSuchElementException si la cola está vacía
     */
    public int getHighestPriority() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error del TDA: La cola está vacía.");
        }
        return first.priority;
    }

    /**
     * Comprueba si la cola está vacía.
     * 
     * @return true si está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Obtiene el tamaño actual de la cola.
     * 
     * @return número de elementos en la cola
     */
    public int size() {
        return size;
    }
}
