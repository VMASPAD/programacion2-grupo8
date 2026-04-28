package applicationModule.consumerApp.adt;

public class PriorityLinkedNode<E> {
    E value;
    int priority;
    PriorityLinkedNode<E> next;
    PriorityLinkedNode<E> prev;

    public PriorityLinkedNode(E value, int priority) {
        this.value = value;
        this.priority = priority;
    }
}
