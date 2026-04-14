package setModule;

public class SimpleLinkedSet<E> implements SimpleSet<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public SimpleLinkedSet() {
        this.head = null;
        this.size = 0;
    }

    // Recibe un elemento, verifica que no exista, lo agrega, devuelve si fue exitosa
    @Override
    public boolean add(E element) {
        if (contains(element)) {
            return false;
        }
        Node<E> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    // Recibe un elemento, lo busca, lo remueve si existe, devuelve si fue exitosa
    @Override
    public boolean remove(E element) {
        if (head == null) {
            return false;
        }

        if ((head.data == null && element == null) || 
            (head.data != null && head.data.equals(element))) {
            head = head.next;
            size--;
            return true;
        }

        Node<E> current = head;
        while (current.next != null) {
            if ((current.next.data == null && element == null) || 
                (current.next.data != null && current.next.data.equals(element))) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public boolean contains(E element) {
        Node<E> current = head;
        while (current != null) {
            if ((current.data == null && element == null) || 
                (current.data != null && current.data.equals(element))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
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
    public E[] toArray() {
        @SuppressWarnings("unchecked")
        E[] array = (E[]) new Object[size];
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            array[index] = current.data;
            current = current.next;
            index++;
        }
        return array;
    }

    // Recibe otro Set, devuelve nuevo Set con elementos de ambos
    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleLinkedSet<>();
        
        Node<E> current = head;
        while (current != null) {
            result.add(current.data);
            current = current.next;
        }
        
        E[] otherElements = other.toArray();
        for (E element : otherElements) {
            result.add(element);
        }
        
        return result;
    }

    // Recibe otro Set, devuelve nuevo Set con elementos comunes
    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleLinkedSet<>();
        
        Node<E> current = head;
        while (current != null) {
            if (other.contains(current.data)) {
                result.add(current.data);
            }
            current = current.next;
        }
        
        return result;
    }

    // Recibe otro Set, devuelve nuevo Set con elementos de este que no están en other
    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        SimpleSet<E> result = new SimpleLinkedSet<>();
        
        Node<E> current = head;
        while (current != null) {
            if (!other.contains(current.data)) {
                result.add(current.data);
            }
            current = current.next;
        }
        
        return result;
    }
}
