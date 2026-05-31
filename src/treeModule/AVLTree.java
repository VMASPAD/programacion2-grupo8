package treeModule;

public class AVLTree<E extends Comparable<E>> extends BST<E> {
    
    private int height(TreeNode<E> N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private TreeNode<E> rightRotate(TreeNode<E> y) {
        TreeNode<E> x = y.left;
        TreeNode<E> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private TreeNode<E> leftRotate(TreeNode<E> x) {
        TreeNode<E> y = x.right;
        TreeNode<E> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int getBalance(TreeNode<E> N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    @Override
    protected TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
        // 1. Inserción normal de BST
        if (current == null) {
            size++;
            return new TreeNode<E>(value);
        }

        int comparison = value.compareTo(current.value);
        if (comparison < 0)
            current.left = insertRecursive(current.left, value);
        else if (comparison > 0)
            current.right = insertRecursive(current.right, value);
        else
            return current; // No se permiten duplicados

        // 2. Actualizar altura
        current.height = 1 + max(height(current.left), height(current.right));

        // 3. Obtener el factor de balance
        int balance = getBalance(current);

        // Si el nodo se desbalancea, hay 4 casos
        // Caso Izquierda Izquierda
        if (balance > 1 && value.compareTo(current.left.value) < 0)
            return rightRotate(current);

        // Caso Derecha Derecha
        if (balance < -1 && value.compareTo(current.right.value) > 0)
            return leftRotate(current);

        // Caso Izquierda Derecha
        if (balance > 1 && value.compareTo(current.left.value) > 0) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }

        // Caso Derecha Izquierda
        if (balance < -1 && value.compareTo(current.right.value) < 0) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        return current;
    }

    @Override
    protected TreeNode<E> removeRecursive(TreeNode<E> current, E value) {
        // 1. Remoción normal de BST
        if (current == null)
            return current;

        int comparison = value.compareTo(current.value);
        if (comparison < 0)
            current.left = removeRecursive(current.left, value);
        else if (comparison > 0)
            current.right = removeRecursive(current.right, value);
        else {
            // Nodo con uno o ningún hijo
            if ((current.left == null) || (current.right == null)) {
                TreeNode<E> temp = null;
                if (temp == current.left)
                    temp = current.right;
                else
                    temp = current.left;

                if (temp == null) {
                    current = null;
                } else
                    current = temp;
                size--;
            } else {
                // Nodo con dos hijos
                TreeNode<E> temp = getMinNode(current.right);
                current.value = temp.value;
                current.right = removeRecursive(current.right, temp.value);
            }
        }

        // Si el árbol tenía solo un nodo
        if (current == null)
            return current;

        // 2. Actualizar altura
        current.height = max(height(current.left), height(current.right)) + 1;

        // 3. Obtener el balance
        int balance = getBalance(current);

        // Si el nodo se desbalancea, hay 4 casos
        // Caso Izquierda Izquierda
        if (balance > 1 && getBalance(current.left) >= 0)
            return rightRotate(current);

        // Caso Izquierda Derecha
        if (balance > 1 && getBalance(current.left) < 0) {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }

        // Caso Derecha Derecha
        if (balance < -1 && getBalance(current.right) <= 0)
            return leftRotate(current);

        // Caso Derecha Izquierda
        if (balance < -1 && getBalance(current.right) > 0) {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        return current;
    }
}