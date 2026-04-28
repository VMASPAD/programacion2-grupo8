package applicationModule.consumerApp.adt;

import java.util.NoSuchElementException;

/**
 * Queue of prioridad implementada with nodos enlazados y prioridad bidireccional.
 * The elements with mayor prioridad (menor value numérico) se desenqueuen primero.
 * @param <E> Tipo of element a almacenar in the queue
 */
public class SimpleLinkedPriorityQueue<E> {
    private PriorityLinkedNode<E> first;
    private PriorityLinkedNode<E> last;
    private int size;

    /**
     * Enqueue a element with a prioridad específica.
     * Inserta the element in the posición correcta según its prioridad.
     * 
     * @param element Element a enqueuer
     * @param priority Prioridad dthe element (menor número = mayor prioridad)
     * @throws IllegalArgumentException si element es nulo
     */
    public void enqueue(E element, int priority) {
        if (element == null) {
            throw new IllegalArgumentException("Error dthe TDA: No se pueden enqueuer elements nulos.");
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
     * Desenqueue y retorna the primer element (de mayor prioridad).
     * 
     * @return the element desenqueuedo
     * @throws NoSuchElementException si the queue está empty
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error dthe TDA: The queue está empty.");
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
     * Retorna the primer element sin desenqueuerlo.
     * 
     * @return the primer element
     * @throws NoSuchElementException si the queue está empty
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error dthe TDA: The queue está empty.");
        }
        return first.value;
    }

    /**
     * Obtiene the prioridad dthe primer element.
     * 
     * @return the prioridad dthe primer element
     * @throws NoSuchElementException si the queue está empty
     */
    public int getHighestPriority() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error dthe TDA: The queue está empty.");
        }
        return first.priority;
    }

    /**
     * Comprueba si the queue está empty.
     * 
     * @return true si está empty, false in caso contrario
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Obtiene the tamaño actual of the queue.
     * 
     * @return número of elements in the queue
     */
    public int size() {
        return size;
    }
}
