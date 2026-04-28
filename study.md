

## Fundamentos de TDAs {#fundamentos}

### ¿Qué es un Tipo de Dato Abstracto (TDA)?

Un **Tipo de Dato Abstracto (TDA)** es un modelo conceptual que define:
- **QUÉ** operaciones se pueden realizar sobre los datos
- **CÓMO** se espera que se comporten esas operaciones
- **NO DICE** cómo implementarlas a nivel técnico

Esta separación entre especificación e implementación es el pilar del diseño de software robusto.

### Componentes de un TDA

```
┌─────────────────────────────────────┐
│      TDA (Concepto Abstracto)       │
├──────────────────┬──────────────────┤
│  Especificación  │  Implementación  │
│   (Interfaz)     │   (Clase Concreta)│
├──────────────────┼──────────────────┤
│ - Define contrato│ - Código real    │
│ - Operaciones    │ - Algoritmos      │
│ - Comportamiento │ - Estructura datos│
└──────────────────┴──────────────────┘
```

### Niveles de Abstracción en Java

```java
// NIVEL 1: Especificación (Interfaz)
public interface SimpleList<E> {
    boolean add(E element);
    E get(int index);
    E remove(int index);
    int size();
}

// NIVEL 2: Implementación Estática (Array)
public class SimpleArrayList<E> implements SimpleList<E> {
    private E[] elements;
    private int size;
    // ... código concreto ...
}

// NIVEL 3: Implementación Dinámica (Nodos)
public class SimpleLinkedList<E> implements SimpleList<E> {
    private Node<E> head;
    private Node<E> tail;
    // ... código concreto ...
}
```

---

## Complejidad Algorítmica {#complejidad}

### Notación Big O (O Grande)

La complejidad no se mide en segundos o bytes absolutos, sino en **cómo escalan los costos con el tamaño del input (n)**.

#### Jerarquía de Complejidades

```
O(1)      < O(log n)   < O(n)    < O(n log n) < O(n²)     < O(2ⁿ)    < O(n!)
Constante   Logarítmica  Lineal    Log-lineal   Cuadrática  Exponencial Factorial

MEJOR                                         PEOR
```

### Tipos de Análisis

Para cada operación evaluamos **3 escenarios**:

| Escenario | Descripción | Símmbolo |
|-----------|-------------|----------|
| **Mejor caso** | Entrada más favorable | Ω(n) |
| **Caso promedio** | Promedio de todas entradas | Θ(n) |
| **Peor caso** | Entrada más desfavorable | O(n) |

**Ejemplo: Búsqueda Lineal**
```java
public int buscar(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) return i;  // Mejor caso: O(1)
    }
    return -1;  // Peor caso: O(n)
}
```

---

## TDA List {#tda-list}

### Definición

Una **List** es una colección **ordenada** donde cada elemento tiene una posición accesible por índice.

### Interfaz SimpleList

```java
public interface SimpleList<E> {
    boolean add(E element);           // Agregar al final - O(1) estático, O(1) dinámico
    void add(int index, E element);   // Insertar en posición - O(n) estático, O(n) dinámico
    E remove(int index);              // Remover por índice - O(n) estático, O(n) dinámico
    boolean remove(Object object);    // Remover por valor - O(n) estático, O(n) dinámico
    E get(int index);                 // Acceso directo - O(1) estático, O(n) dinámico
    E set(int index, E element);      // Reemplazar - O(1) estático, O(n) dinámico
    int size();                       // Tamaño - O(1)
    boolean contains(Object object);  // Búsqueda - O(n)
    void clear();                     // Limpiar - O(n)
    boolean isEmpty();                // ¿Vacía? - O(1)
}
```

### Implementación Estática: SimpleArrayList

**Estructura Interna:**
```java
private E[] elements;      // Array primitivo contiguo
private int size;          // Elementos reales usados
private static final int DEFAULT_CAPACITY = 10;
```

**Ventajas:**
- ✅ Acceso O(1) por índice (directo a memoria)
- ✅ Búsqueda rápida en arrays ordenados
- ✅ Bajo overhead de memoria

**Desventajas:**
- ❌ Insertions/removals en mitad = O(n) (requiere desplazamiento)
- ❌ Redimensionamiento costoso cuando se llena
- ❌ Espacio pre-alocado desperdiciado

**Mecanismo de Redimensionamiento:**
```
Array lleno de 10 elementos
              ↓
Validar capacidad insuficiente
              ↓
Crear nuevo array de tamaño 20
              ↓
Copiar elemento por elemento (O(n))
              ↓
Actualizar referencia
```

### Implementación Dinámica: SimpleLinkedList

**Estructura Interna:**
```java
private Node<E> head;    // Referencia al primer nodo
private Node<E> tail;    // Referencia al último nodo
private int size;        // Cantidad de nodos

private class Node<E> {
    E data;
    Node<E> prev;        // Doble enlace (anterior)
    Node<E> next;        // Doble enlace (siguiente)
}
```

**Ventajas:**
- ✅ Inserción/Remoción O(1) en extremos (solo cambiar referencias)
- ✅ Sin redimensionamiento (se expande dinámicamente)
- ✅ Memoria usada solo para datos reales

**Desventajas:**
- ❌ Acceso O(n) por índice (iterar nodos)
- ❌ Mayor overhead por referencias
- ❌ Cache-unfriendly (nodos dispersos en memoria)

### Comparativa de Complejidad (List)

| Operación | Estática | Dinámica |
|-----------|----------|----------|
| Agregar al final | O(1)* | O(1) |
| Insertar en medio | O(n) | O(n)** |
| Remover de inicio | O(n) | O(1) |
| Remover de medio | O(n) | O(n)** |
| Acceder por índice | O(1) | O(n) |
| Buscar por valor | O(n) | O(n) |

*Excepto cuando necesita resize  
**O(n) por encontrar el nodo, O(1) para reconectar

---

## TDA Stack {#tda-stack}

### Definición

Un **Stack (Pila)** es una colección **LIFO** (Last In, First Out): el último en entrar es el primero en salir.

**Metáfora Real:** Pila de platos - sacas el de arriba primero.

### Interfaz SimpleStack

```java
public interface SimpleStack<E> {
    void push(E element);    // Agregar al tope - O(1)
    E pop();                 // Sacar del tope - O(1) devuelve y elimina
    E peek();                // Ver tope - O(1) solo mira, no elimina
    void clear();            // Limpiar todos
    int size();              // Cantidad
    boolean isEmpty();       // ¿Está vacía?
}
```

### Acceso Restringido

```
     push → [TOP]
             [ 3 ]
             [ 2 ]
             [ 1 ]
     pop ← [BOT]
```

### Implementación Estática vs Dinámica

**SimpleArrayStack:**
```java
private E[] elements;
private int top;           // Índice del siguiente libre

// push: elements[top++] = element → O(1)
// pop:  return elements[--top]   → O(1)
// peek: return elements[top-1]   → O(1)
```

**SimpleLinkedStack:**
```java
private Node<E> top;       // Solo necesita referencia al tope

// push: nuevo nodo apunta al actual top → O(1)
// pop:  devuelve top y avanza al siguiente → O(1)
// peek: devuelve data del top → O(1)
```

### Complejidad (Stack)

| Operación | Estática | Dinámica |
|-----------|----------|----------|
| push | O(1)* | O(1) |
| pop | O(1) | O(1) |
| peek | O(1) | O(1) |
| isEmpty | O(1) | O(1) |

*Excepto resize raro

### Ejemplo de Uso: Paréntesis Balanceados

```java
boolean esBalanceado(String expr) {
    SimpleStack<Character> stack = new SimpleArrayStack<>();
    
    for (char c : expr.toCharArray()) {
        if (c == '(' || c == '[') {
            stack.push(c);
        } else if (c == ')' || c == ']') {
            if (stack.isEmpty()) return false;
            char abre = stack.pop();
            if (!coinciden(abre, c)) return false;
        }
    }
    return stack.isEmpty();
}
```

---

## TDA Queue {#tda-queue}

### Definición

Una **Queue (Cola)** es una colección **FIFO** (First In, First Out): el primero en entrar es el primero en salir.

**Metáfora Real:** Fila de banco - el primero en llegar es el primero atendido.

### Interfaz SimpleQueue

```java
public interface SimpleQueue<E> {
    void enqueue(E element);  // Agregar al final - O(1)
    E dequeue();              // Sacar del inicio - O(1) dinámica, O(n) estática
    E peek();                 // Ver primero - O(1)
    void clear();             // Vaciar
    int size();               // Cantidad
    boolean isEmpty();        // ¿Está vacía?
}
```

### Flujo de Datos

```
enqueue →    [ 3 ]
             [ 2 ]
             [ 1 ] ← dequeue
```

### Implementación Estática (PROBLEMA)

```java
private E[] elements;
private int front;    // Índice del primero
private int rear;     // Índice del siguiente libre

// enqueue: elements[rear++] = element → O(1) ✅
// dequeue: E temp = elements[front++]; return temp → O(n) ❌
//          ¿Por qué O(n)? Todos se corren un lugar izquierda para evitar gaps
```

**El Problema del Dequeue Estático:**
```
Original:  [ 1, 2, 3, 4, 5 ]  front=0
                    ↓
dequeue()
                    ↓
Debe quedar: [ -, 2, 3, 4, 5 ]
                    ↓
Pero crearía un hueco, así que:
Copia todo: [ 2, 3, 4, 5, - ]  front=0
                    ↓
Costo: O(n) - copiar n-1 elementos
```

### Implementación Dinámica (SOLUCIÓN)

```java
private Node<E> front;    // Puntero al primero
private Node<E> rear;     // Puntero al último

// enqueue: nuevo nodo en rear, apunta al null → O(1)
// dequeue: devuelve front, avanza front → O(1)
// peek: devuelve data de front → O(1)
```

**Sin necesidad de copias:** Solo cambias referencias.

### Comparativa de Complejidad (Queue)

| Operación | Estática | Dinámica |
|-----------|----------|----------|
| enqueue | O(1) | O(1) |
| dequeue | O(n) | O(1) |
| peek | O(1) | O(1) |
| isEmpty | O(1) | O(1) |

**Conclusión:** Para Queue, **SIEMPRE usa implementación dinámica**.

### Ejemplo de Uso: Procesamiento de Tareas

```java
SimpleQueue<String> tareas = new SimpleLinkedQueue<>();

tareas.enqueue("Lavar platos");
tareas.enqueue("Limpiar casa");
tareas.enqueue("Hacer compras");

// Procesar en orden FIFO
while (!tareas.isEmpty()) {
    String tarea = tareas.dequeue();
    procesarTarea(tarea);
}
```

---

## TDA Set {#tda-set}

### Definición

Un **Set (Conjunto)** es una colección **no ordenada** de elementos **únicos** (sin duplicados).

**Propiedad fundamental:** No puede haber dos elementos iguales.

### Interfaz SimpleSet

```java
public interface SimpleSet<E> {
    boolean add(E element);            // Agregar - O(n) búsqueda + O(1) inserción
    boolean remove(E element);         // Remover - O(n) búsqueda
    boolean contains(E element);       // ¿Contiene? - O(n)
    void clear();                      // Vaciar - O(n)
    boolean isEmpty();                 // ¿Vacía? - O(1)
    int size();                        // Cantidad - O(1)
    E[] toArray();                     // Convertir a array
    SimpleSet<E> unionWith(SimpleSet<E> other);       // A ∪ B
    SimpleSet<E> intersectWith(SimpleSet<E> other);   // A ∩ B
    SimpleSet<E> differenceWith(SimpleSet<E> other);  // A - B
}
```

### Características Clave

**Unicidad garantizada:**
```java
SimpleSet<Integer> numeros = new SimpleArraySet<>();

numeros.add(5);        // true - se agrega
numeros.add(5);        // false - ya existe, no se duplica

numeros.size();        // 1 - solo hay un 5
```

**Sin orden:**
```java
numeros.add(30);
numeros.add(10);
numeros.add(20);
// El orden de iteración NO es garantizado
```

### Implementación Estática

```java
private E[] elements;
private int size;

public boolean add(E element) {
    // 1. Búsqueda O(n) - ¿ya existe?
    if (contains(element)) return false;
    
    // 2. Inserción O(1) si hay espacio
    elements[size++] = element;
    return true;
}
```

**Optimización en Remoción:**
```
Estática pura (problema):
[ 1, 2, 3, 4, 5 ]
Remove 2:
[ 1, 3, 4, 5, - ]  → O(n) desplazamiento

Set estático (optimizado):
[ 1, 2, 3, 4, 5 ]
Remove 2:
[ 1, 5, 3, 4, - ]  → O(1) intercambio con último
```

### Operaciones Algebraicas

**Unión (A ∪ B):** Todos los elementos de A y B
```
A = {1, 2, 3}
B = {3, 4, 5}
A ∪ B = {1, 2, 3, 4, 5}
```

**Intersección (A ∩ B):** Solo elementos que están en ambos
```
A = {1, 2, 3}
B = {2, 3, 4}
A ∩ B = {2, 3}
```

**Diferencia (A - B):** Elementos de A que NO están en B
```
A = {1, 2, 3}
B = {2, 3, 4}
A - B = {1}
```

### Ejemplo Operaciones

```java
SimpleSet<Integer> pares = new SimpleArraySet<>();
pares.add(2); pares.add(4); pares.add(6);

SimpleSet<Integer> menores5 = new SimpleArraySet<>();
menores5.add(2); menores5.add(3); menores5.add(4);

// Unión: {2, 3, 4, 6}
SimpleSet<Integer> union = pares.unionWith(menores5);

// Intersección: {2, 4}
SimpleSet<Integer> inter = pares.intersectWith(menores5);

// Diferencia: {6}
SimpleSet<Integer> diff = pares.differenceWith(menores5);
```

### Complejidad (Set)

| Operación | Estática | Dinámica |
|-----------|----------|----------|
| add | O(n) | O(n) |
| remove | O(1)* | O(n) |
| contains | O(n) | O(n) |
| unionWith | O(n²) | O(n²) |
| intersectWith | O(n²) | O(n²) |
| differenceWith | O(n²) | O(n²) |

*En estática es O(1) si usas intercambio con último

---

## Análisis Comparativo {#análisis-comparativo}

### Matriz de Decisión

```
┌─────────────────────────────────────────────────────────────┐
│  ¿Cuál estructura usar según tus necesidades?               │
├──────────────────┬──────────────────┬──────────────────────┤
│  Caso de Uso     │  Estructura      │  Razón               │
├──────────────────┼──────────────────┼──────────────────────┤
│ Acceso frecuente │  LIST ESTÁTICA   │ O(1) por índice      │
│ por índice       │  (Array)         │                      │
│                  │                  │                      │
│ Inserciones/     │  LIST DINÁMICA   │ O(1) en extremos     │
│ remociones       │  (Linked)        │ sin redimensionamiento
│ frecuentes       │                  │                      │
│                  │                  │                      │
│ Último en        │  STACK           │ LIFO simple y rápido │
│ entrar,          │  (cualquiera)    │ O(1) push/pop        │
│ primero en       │                  │                      │
│ salir            │                  │                      │
│                  │                  │                      │
│ Primero en       │  QUEUE DINÁMICA  │ FIFO eficiente       │
│ entrar,          │  (Linked)        │ O(1) enqueue/dequeue │
│ primero en       │                  │                      │
│ salir            │                  │                      │
│                  │                  │                      │
│ Elementos        │  SET             │ No duplicados        │
│ únicos           │  (cualquiera)    │ Operaciones booleanas│
│ sin duplicados   │                  │ Operaciones conjuntos│
└──────────────────┴──────────────────┴──────────────────────┘
```

### Tabla General de Complejidades

```
ESTÁTICA (Array)
════════════════════════════════════════════════════════════════
            LIST        STACK       QUEUE       SET
add inicio  O(n)        -           O(1)*       O(n)
add final   O(1)*       push O(1)   O(1)*       -
get/peek    O(1)        O(1)        O(1)        -
remove ini  O(n)        pop O(1)    O(n)        -
remove fin  O(n)        -           -           O(n)
remove mid  O(n)        -           -           O(1)**
contains    O(n)        -           -           O(n)

DINÁMICA (Linked)
════════════════════════════════════════════════════════════════
            LIST        STACK       QUEUE       SET
add inicio  O(1)        -           -           O(n)
add final   O(1)        push O(1)   O(1)        -
get/peek    O(n)        O(1)        O(1)        -
remove ini  O(1)        pop O(1)    O(1)        -
remove fin  O(1)        -           -           O(n)
remove mid  O(n)        -           -           O(n)
contains    O(n)        -           -           O(n)

* Puede O(n) si requiere resize
** Usando intercambio con último (solo estática Set)
```

---

## Patrones de Implementación en el Proyecto

### 1. **Patrón Interfaz + Dos Implementaciones**

```java
// Interfaz (Especificación)
public interface SimpleList<E> { ... }

// Implementación Estática
public class SimpleArrayList<E> implements SimpleList<E> {
    private E[] elements;
    // ...
}

// Implementación Dinámica
public class SimpleLinkedList<E> implements SimpleList<E> {
    private Node<E> head;
    // ...
}
```

**Ventaja:** El usuario elige qué implementación usar según necesidad.

### 2. **Validación Preventiva**

No esperar excepciones del TDA. La aplicación valida ANTES:

```java
// MAL: Esperar excepción
try {
    list.remove(-1);  // IndexOutOfBoundsException
} catch (Exception e) { }

// BIEN: Validar antes
if (index >= 0 && index < list.size()) {
    list.remove(index);
}
```

### 3. **Búsqueda Eficiente en Dinámica**

Para `SimpleLinkedList`, buscar desde extremo más cercano:

```java
private Node<E> getNode(int index) {
    if (index < size / 2) {
        // Comenzar desde head
        return nodoDesdeInicio(index);
    } else {
        // Comenzar desde tail
        return nodosDesdeFinal(index);
    }
}
```

---

## Análisis del Proyecto: Sistema de Inventario

En `InventoryExercise.java`, se aplican estos TDAs:

```java
// Uso de List (Dinámica) para almacenar productos
SimpleList<Producto> inventory = new SimpleLinkedList<>();

// ¿Por qué LinkedList?
// - Se agregan productos dinámicamente (sin redimensionamiento)
// - No hay acceso frecuente por índice
// - Búsqueda lineal es aceptable para educación

// Aplicación de conceptos:
// 1. Validación preventiva (no confiar en TDA para validation)
// 2. Búsqueda lineal manual con bucles
// 3. Operaciones CRUD simples
```

---

## Conclusiones y Recomendaciones

### Reglas de Oro

1. **Entiende el problema ANTES de elegir estructura**
   - ¿Frecuencia de búsqueda vs inserciones?
   - ¿Necesitas acceso aleatorio?
   - ¿Importa el orden?

2. **No existe estructura "ganadora universal"**
   - Array: ⭐ acceso rápido, ❌ inserciones costosas
   - Linked: ⭐ inserciones flexibles, ❌ acceso lento

3. **Analiza siempre el peor caso**
   - O(n) en mitad de lista es muy diferente a O(1) en final

4. **Valida entrada, no confíes en excepciones**
   - La aplicación debe asegurar no enviar datos inválidos al TDA

5. **Mide y prueba realmente**
   - La teoría es guía; las pruebas son verdad

---

## Preguntas para Autoevaluación

1. **¿Cuál es la diferencia entre O(1) y O(n)?**
   - O(1): Costo constante, NO cambia con input
   - O(n): Costo linear, crece directamente con n

2. **¿Por qué Queue estática sufre en dequeue?**
   - El primer elemento está en índice 0; removerlo requiere desplazar TODO

3. **¿Por qué Set no permite duplicados?**
   - Concepto matemático: un Set es una colección de elementos únicos

4. **¿Cuándo preferirías Array sobre Linked?**
   - Cuando necesitas acceso O(1) por índice frecuente

5. **¿Qué es la complejidad espacial?**
   - Cuánta memoria adicional usa el algoritmo (no contar input)

