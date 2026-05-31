package treeModule;

import listModule.SimpleLinkedList;

public class BST <E extends Comparable<E>>{
    public TreeNode<E> root = null;
    protected int size = 0;

    public void insert (E value){
        root = insertRecursive(root ,value);
    }

    //Recibe el nodo como estaba antes de insertar y devulve el nuevo nodo en ese mismo lugar
    //Puede recibir null con el espacio vacio
    // puede que devuelva el mismo nodo que recibio

    protected TreeNode<E> insertRecursive (TreeNode<E> current , E value){
        //caso base: encontramos un lugar vacio
        //Insertamos en ese lugar, devolviendo uno nuevo

        if (current== null) {
            size++;
            return new TreeNode<E>(value);
        }
        //la comparacion es una funcion que devuelve
        //-1 = "menor" (izq)
        //1 = "mayor"(der)
        // 0 = "igual" (misma posicion)
        int comparison = value.compareTo(current.value);

        if (comparison < 0) {                    //si es menor, seguimos por el hijo izquierdo
            current.left = insertRecursive(current.left, value);
        } else if(comparison > 0) {            //si es "mayor" seguimos por el hijo derecho
            current.right = insertRecursive(current.right, value);
        }
        //si llegamos aca, no hubieron cambios en este nodo
        return current; //no admite duplicados, retorna mismo nodo
    }

    public void remove(E value){
        root = removeRecursive(root, value);
    }

    protected TreeNode<E> removeRecursive(TreeNode<E> current,E value){
        if(current == null) return null; // caso base: llegamos al final y no estaba el value ( no hay nada para remover)

        int comparison = value.compareTo(current.value);

        if (comparison == 0 ) {//caso 1 : hoja (sin hijos)
            if (current.left == null && current.right == null) {
                size--;
                return null;
            }
            else if (current.left == null){ //Caso 2: tiene 1 solo hijo
                size --;
                return current.right;
            }
            else if (current.right == null) {
                size --;
                return current.left;
            }

            //Caso 3: tiene ambos hijos
            //buscamos el minimo de la derecha
            TreeNode<E> succesor =  getMinNode(current.right);
            //pisamos al nodo con el sucesor (values)
            current.value = succesor.value;
            //ahora removemos al sucesor para que no haya duplicados
            current.right = removeRecursive(current.right, succesor.value);
        }
        else if (comparison < 0){
            current.left = removeRecursive(current.left, value); // si es menor voy al izquierdo
        }
        else {
            current.right = removeRecursive(current.right, value); // si es mayor voy al derecho
        }
        return current ;
    }

    //para encontrar el minino, vamos a la  izquierda a fondo
    protected TreeNode<E> getMinNode(TreeNode<E> current){
        while (current.left != null)
            current = current.left;
        return current;
    }
    //DFS
    //Pre-Order
    public SimpleLinkedList<E> preOrder(){
        SimpleLinkedList<E> result = new SimpleLinkedList<>();
        preOrderDFS(root,result);
        return result;
    }

    protected void preOrderDFS(TreeNode<E> current, SimpleLinkedList<E> list){
        if (current == null) return;

        list.add(current.value);
        preOrderDFS(current.left, list);
        preOrderDFS(current.right, list);
    }
}
