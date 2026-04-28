# Implementación de Estructuras de Datos: SimpleList

## Resumen Ejecutivo

Este proyecto implementa una interfaz personalizada `SimpleList<E>` con dos implementaciones distintas:
- **SimpleArrayList**: utiliza un array dinámico que expande su capacidad cuando es necesario
- **SimpleLinkedList**: utiliza nodos enlazados bidireccionales para almacenar elementos

Ambas implementaciones ofrecen operaciones fundamentales de listas con comportamientos y características de rendimiento diferentes.

---

## Estructura del Proyecto

```
src/
├── application/
│   ├── Excercise.java (Clase abstracta base)
│   ├── TestExcercise.java (Prueba básica)
│   ├── ListExercise.java (Ejercicio con ArrayList de Java)
│   └── ListImplementationExercise.java (Ejercicio con SimpleList)
├── listModule/
│   ├── SimpleList.java (Interfaz principal)
│   ├── SimpleArrayList.java (Implementación con array)
│   └── SimpleLinkedList.java (Implementación con nodos)
└── App.java (Punto de entrada)
```

---

## La Interfaz SimpleList<E>

Define 10 operaciones fundamentales que cualquier implementación debe proporcionar:

### Métodos

| Método | Descripción | Retorna |
|--------|-------------|---------|
| `add(E element)` | Agrega un elemento al final | `boolean` (siempre true) |
| `add(int index, E element)` | Inserta en posición específica | `void` |
| `remove(int index)` | Elimina por índice | Elemento eliminado |
| `remove(Object object)` | Elimina por valor | `boolean` |
| `clear()` | Vacía la lista | `void` |
| `contains(Object object)` | Busca un elemento | `boolean` |
| `get(int index)` | Obtiene un elemento | Elemento en esa posición |
| `set(int index, E element)` | Reemplaza un elemento | Elemento anterior |
| `size()` | Cantidad de elementos | `int` |
| `isEmpty()` | Verifica si está vacía | `boolean` |

---

## SimpleArrayList: Implementación con Array Dinámico

### Funcionamiento

- **Almacenamiento**: Utiliza un array `Object[]` interno
- **Capacidad Inicial**: 10 elementos
- **Expansión**: Cuando se llena, se duplica la capacidad y se copian todos los elementos
- **Size**: Solo cuenta elementos ocupados (no espacios vacíos)

### Características

✅ **Ventajas:**
- Acceso rápido a elementos (O(1))
- Ocupado menos memoria para listas pequeñas
- Cache-friendly (elementos contiguos en memoria)

❌ **Desventajas:**
- Inserción/eliminación en el medio es lenta (O(n))
- Operaciones de crecimiento pueden ser costosas
- Puede desperdiciar memoria cuando se encoge poco

### Ejemplo de Expansión

```
Inicial:    [elem0] [elem1] ... [elem9] [null] [null] ...
            Capacidad = 10, Size = 10

Después:    [elem0] [elem1] ... [elem9] [elem10] ... [null] ...
            Capacidad = 20, Size = 11
```

---

## SimpleLinkedList: Implementación con Nodos Enlazados

### Funcionamiento

- **Almacenamiento**: Cada elemento está en un `Node<E>` que contiene:
  - `data`: el elemento
  - `prev`: referencia al nodo anterior
  - `next`: referencia al nodo siguiente

- **Estructura**: Lista doblemente enlazada con referencias a cabeza y cola

### Características

✅ **Ventajas:**
- Inserción/eliminación rápida (O(1)) si tienes la referencia al nodo
- No requiere pre-asignación de memoria
- Crece dinámicamente sin reorganización

❌ **Desventajas:**
- Acceso lento (O(n)) - debe recorrer desde cabeza o cola
- Mayor consumo de memoria (dos referencias por nodo)
- Menos cache-friendly

### Optimización de Acceso

El método `getNode()` implementa una estrategia inteligente:
- Si el índice está en la primera mitad → busca desde la cabeza
- Si está en la segunda mitad → busca desde la cola

Esto reduce el tiempo promedio de búsqueda a O(n/2).

---

## Interfaz de Usuario

### Menú Principal

El programa ofrece tres opciones:
1. **Test Básico**: Verifica que el programa funciona
2. **ArrayList de Java**: Ejercicio con la clase estándar
3. **SimpleList**: Ejercicio permitiendo elegir entre SimpleArrayList o SimpleLinkedList

### Operaciones en ListImplementationExercise

1. Agregar elemento
2. Remover por índice
3. Remover por valor
4. Buscar elemento
5. Insertar en posición
6. Reemplazar elemento
7. Limpiar lista

---

## Ejemplo de Uso

```java
// Crear una lista de arrays
SimpleList<String> lista = new SimpleArrayList<>();

// Agregar elementos
lista.add("Java");
lista.add("Python");
lista.add("Kotlin");

// Insertar en posición específica
lista.add(1, "JavaScript");  
// Resultado: [Java, JavaScript, Python, Kotlin]

// Obtener por índice
String lenguaje = lista.get(2);  // "Python"

// Remover por valor
lista.remove("Kotlin");

// Buscar
boolean existe = lista.contains("Java");  // true

// Reemplazar
lista.set(0, "Go");

// Tamaño
int cantidad = lista.size();  // 3
```

---

## Análisis de Complejidad

### SimpleArrayList

| Operación | Costo | Razón |
|-----------|-------|-------|
| `add(E)` | O(1) amortizado | Generalmente constante, expansión ocasional |
| `add(int, E)` | O(n) | Debe desplazar elementos |
| `remove(int)` | O(n) | Debe desplazar elementos hacia atrás |
| `remove(Object)` | O(n) | Busca + desplazamiento |
| `get(int)` | O(1) | Acceso directo |
| `contains(Object)` | O(n) | Búsqueda lineal |

### SimpleLinkedList

| Operación | Costo | Razón |
|-----------|-------|-------|
| `add(E)` | O(1) | Se agrega directamente en la cola |
| `add(int, E)` | O(n) | Debe encontrar el nodo |
| `remove(int)` | O(n) | Debe buscar el nodo |
| `remove(Object)` | O(n) | Búsqueda lineal |
| `get(int)` | O(n) | Recorrido desde cabeza/cola |
| `contains(Object)` | O(n) | Búsqueda lineal |

---

## Cuándo Usar Cada Implementación

### Usa SimpleArrayList cuando:
- Necesitas acceso frecuente a elementos por índice
- Las inserciones/eliminaciones son principalmente al final
- La memoria es limitada
- Trabajas con datos que caben en caché

### Usa SimpleLinkedList cuando:
- Realizas frecuentes inserciones/eliminaciones en medio
- El tamaño de la lista varía mucho
- No necesitas acceso aleatorio
- Tienes iteraciones lineales

---

## Detalles Técnicos Importantes

### Gestión de Genéricos
- Ambas clases usan `Object[]` internamente y realizan casting

### Manejo de Nulidades
- Ambas implementaciones soportan `null` como elemento válido
- Las comparaciones usan `==` para null y `.equals()` para objetos

### Expansión del Array
```java
private void expandCapacity() {
    Object[] newElements = new Object[elements.length * 2];
    for (int i = 0; i < size; i++) {
        newElements[i] = elements[i];
    }
    elements = newElements;
}
```

### Optimización de Búsqueda en LinkedList
```java
private Node<E> getNode(int index) {
    if (index < size / 2) {
        // Buscar desde cabeza
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    } else {
        // Buscar desde cola
        Node<E> current = tail;
        for (int i = size - 1; i > index; i--) {
            current = current.prev;
        }
        return current;
    }
}
```

