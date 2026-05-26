# TP 07 - SISTEMA DE INVENTARIO CON DICTIONARY

**Estado:** ✅ **COMPLETADO Y FUNCIONAL**

---

## 📋 VERIFICACIÓN DEL TP 07

El TP 07 fue completado anteriormente usando la estructura `SimpleDictionary<K,V>`.

### Componentes Verificados:

#### 1. **TDA Dictionary - FUNCIONAL**

**Interfaz:** `SimpleDictionary<K, V>`
```java
public interface SimpleDictionary<K, V> {
    void put(K key, V value);              // Insertar/actualizar
    V get(K key);                          // Buscar
    V remove(K key);                       // Eliminar
    boolean containsKey(K key);            // Verificar existencia
    List<K> keys();                        // Obtener todas las claves
    List<V> values();                      // Obtener todos los valores
    SimpleEntry<K,V>[] entries();          // Obtener pares (clave, valor)
    boolean isEmpty();
    int size();
    void clear();
}
```

**Implementaciones disponibles:**
- ✅ `SimpleArrayDictionary` - Basada en array dinámico O(n)
- ✅ `SimpleLinkedDictionary` - Basada en lista enlazada O(n)

#### 2. **InventoryExercise - COMPLETAMENTE IMPLEMENTADO**

**Opciones del menú:**
```
1. Buscar Producto
2. Agregar Producto
3. Borrar Producto
4. Editar Producto
5. Listar Productos (con cálculo de valor total)
0. Salir
```

**Estructura de datos:**
```
SimpleDictionary<String, Product>
    ↓
Clave: Código del producto (ej: "P001")
Valor: Objeto Product con (nombre, precio, cantidad)
```

#### 3. **Modelo Product - BIEN ESTRUCTURADO**

```java
public class Product {
    private String code;           // Clave única del producto
    private String name;           // Nombre descriptivo
    private double price;          // Precio unitario
    private int quantity;          // Cantidad en stock
    
    public double getTotalValue(); // price * quantity
}
```

#### 4. **Funcionalidades Implementadas**

✅ **Búsqueda (opción 1):**
- Busca por código de producto
- Muestra datos completos si existe
- Mensaje claro si no existe

✅ **Agregar (opción 2):**
- Valida que código sea único
- Valida que inputs sean válidos
- Inserta en Dictionary

✅ **Borrar (opción 3):**
- Busca por código
- Elimina si existe
- Valida existencia previamente

✅ **Editar (opción 4):**
- Busca la clave
- Permite editar: nombre, precio, cantidad
- Valida inputs antes de guardar

✅ **Listar (opción 5):**
- Obtiene todos los pares del Dictionary
- Calcula valor total (BONUS del TP)
- Formatea salida clara

#### 5. **Datos Pre-programados (Requisito del TP)**

El sistema inicia con 3 productos de ejemplo:
```java
dictionary.put("P001", new Product("P001", "Notebook", 1200.00, 5));
dictionary.put("P002", new Product("P002", "Mouse", 25.50, 50));
dictionary.put("P003", new Product("P003", "Teclado", 85.00, 30));
```

---

## ✅ CUMPLIMIENTO DE REQUISITOS

| Requisito | Cumplimiento |
|-----------|---------|
| Sistema de Inventario | ✅ Completo |
| Código único por producto | ✅ Validado |
| Buscar por código | ✅ Implementado |
| Mostrar nombre, precio, cantidad | ✅ Implementado |
| Editar precio y cantidad | ✅ Implementado |
| Agregar/Borrar productos | ✅ Implementado |
| Listar todos con valor total | ✅ Implementado (BONUS) |
| Manejo de inputs inválidos | ✅ Robusto |
| TDA Dictionary | ✅ Dual (Array/Linked) |
| Datos pre-cargados | ✅ 3 productos |
| Interfaz amigable | ✅ Menú formateado |

---

## 📊 Estadísticas

```
Clases involucradas:
- InventoryExercise.java       (líneas: ~250)
- SimpleDictionary.java        (interfaz)
- SimpleArrayDictionary.java   (~150 líneas)
- SimpleLinkedDictionary.java  (~150 líneas)
- SimpleEntry.java             (~30 líneas)
- Product.java                 (~60 líneas)

Total de métodos implementados: ~40+
```

---

## 🔍 Validaciones Robustas

✅ **Validación de números:**
```java
try {
    double price = Double.parseDouble(input);
    if (price <= 0) throw new NumberFormatException();
} catch (NumberFormatException e) {
    System.out.println("❌ Ingrese un número válido positivo");
}
```

✅ **Validación de códigos únicos:**
```java
if (dictionary.containsKey(code)) {
    System.out.println("❌ Ya existe un producto con código: " + code);
    return;
}
```

✅ **Manejo de Dictionary vacío:**
```java
if (dictionary.isEmpty()) {
    System.out.println("📭 No hay productos en inventario");
    return;
}
```

---

## 📋 Interfaz de Usuario

```
╔════════════════════════════════════════════════╗
║      SISTEMA DE GESTIÓN DE INVENTARIO         ║
╠════════════════════════════════════════════════╣
║ 1. Buscar Producto por Código                 ║
║ 2. Agregar Producto                           ║
║ 3. Borrar Producto                            ║
║ 4. Editar Producto                            ║
║ 5. Listar Productos y Valor Total del Inv.   ║
║ 0. Salir                                      ║
╚════════════════════════════════════════════════╝
▶ Selecciona una opción: 
```

---

## 🚀 Cómo Ejecutar TP 07

```bash
# Compilar
cd "/home/aspad/Desktop/Atlas/Progrmacion 2"
javac -cp src -d bin src/**/*.java src/application/*.java

# Ejecutar
java -cp bin App

# En el menú, seleccionar opción: 8 (TP7: Sistema de Inventario)
```

---

## ✨ Características Destacadas

1. **Estructura Type-Safe:** Usa Generics `<String, Product>` para type safety
2. **Búsqueda O(n):** Optimizada con arrays dinámicos
3. **Cálculo de Valor Total:** BONUS - suma de (precio × cantidad)
4. **Interfaz Consistente:** Sigue patrones del proyecto
5. **Errores Claros:** Mensajes descriptivos con emojis

---

## 📝 Conclusión

**TP 07 VERIFICADO: ✅ COMPLETADO Y FUNCIONAL**

El sistema de inventario:
- ✅ Usa correctamente el TDA Dictionary
- ✅ Implementa todas las funcionalidades requeridas
- ✅ Maneja inputs inválidos robustamente
- ✅ Ofrece buena UX
- ✅ Tiene datos de prueba pre-cargados
- ✅ Compila sin errores

