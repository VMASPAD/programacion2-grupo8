package application;

import dictionaryModule.SimpleArrayDictionary;
import dictionaryModule.SimpleDictionary;
import graphModule.Edge;
import graphModule.ListGraph;
import java.util.Scanner;
import listModule.SimpleList;

public class GpsExercise extends Exercise {

    private ListGraph<String> mapa;

    public GpsExercise(Scanner scanner) {
        super(scanner);

        mapa = new ListGraph<>();

        cargarMapa();
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║           MAPA GPS CARGADO            ║");
        System.out.println("╚═══════════════════════════════════════╝");
        mapa.printGraph();
    }

    private void cargarMapa() {
        mapa.addEdge("Buenos Aires", "Rosario", 300);
        mapa.addEdge("Buenos Aires", "La Plata", 60);
        mapa.addEdge("Rosario", "Cordoba", 400);
        mapa.addEdge("Rosario", "Santa Fe", 160);
        mapa.addEdge("La Plata", "Mar del Plata", 400);
        mapa.addEdge("Santa Fe", "Cordoba", 350);
        mapa.addEdge("Cordoba", "Mendoza", 600);
    }

    @Override
    protected void exerciseLogic() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║              GPS - RUTAS              ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║ 1. Mostrar mapa                       ║");
        System.out.println("║ 2. Buscar camino más corto            ║");
        System.out.println("║ 3. Agregar ruta                       ║");
        System.out.println("║ 4. Editar ruta                        ║");
        System.out.println("║ 5. Eliminar ruta                      ║");
        System.out.println("║ 6. Listar ciudades                    ║");
        System.out.println("║ 0. Volver al menú principal           ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.print("▶ Selecciona una opción: ");

        int opcion = readSafeInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                mostrarMapa();
                break;
            case 2:
                buscarCamino();
                break;
            case 3:
                agregarRuta();
                break;
            case 4:
                editarRuta();
                break;
            case 5:
                eliminarRuta();
                break;
            case 6:
                listarCiudades();
                break;
            case 0:
                running = false;
                System.out.println("\n✓ Volviendo al menú principal...\n");
                break;
            default:
                System.out.println("❌ Opción inválida. Intenta nuevamente.");
        }
    }

    private void mostrarMapa() {
        System.out.println("\n--- Mapa actual ---");
        mapa.printGraph();
    }

    private String[] obtenerVertices() {
        Object[] raw = (Object[]) mapa.vertices();
        String[] vertices = new String[raw.length];
        for (int i = 0; i < raw.length; i++) {
            vertices[i] = (String) raw[i];
        }
        return vertices;
    }

    private void listarCiudades() {
        String[] ciudades = obtenerVertices();

        if (ciudades.length == 0) {
            System.out.println("\nNo hay ciudades registradas en el mapa.");
            return;
        }

        System.out.println("\n--- Ciudades disponibles ---");
        for (int i = 0; i < ciudades.length; i++) {
            System.out.println((i + 1) + ". " + ciudades[i]);
        }
    }

    private void buscarCamino() {
        System.out.println("\n--- Buscar camino más corto ---");

        String origen = leerCiudad("Ciudad origen: ");
        if (origen == null)
            return;

        String destino = leerCiudad("Ciudad destino: ");
        if (destino == null)
            return;

        if (!mapa.containsVertex(origen)) {
            System.out.println("❌ La ciudad de origen no existe en el mapa.");
            return;
        }

        if (!mapa.containsVertex(destino)) {
            System.out.println("❌ La ciudad de destino no existe en el mapa.");
            return;
        }

        if (origen.equalsIgnoreCase(destino)) {
            System.out.println("✓ Ya te encuentras en el destino. Distancia: 0 km");
            return;
        }

        int distancia = dijkstra(origen, destino);

        if (distancia == Integer.MAX_VALUE) {
            System.out.println("❌ No existe un camino entre ambas ciudades.");
        } else {
            String camino = reconstruirCamino(origen, destino);
            System.out.println("✓ Camino más corto: " + camino);
            System.out.println("✓ Distancia mínima: " + distancia + " km");
        }
    }

    private int dijkstra(String origen, String destino) {
        String[] vertices = obtenerVertices();

        SimpleDictionary<String, Integer> distancia = new SimpleArrayDictionary<>();
        SimpleDictionary<String, Boolean> visitado = new SimpleArrayDictionary<>();
        SimpleDictionary<String, String> predecesor = new SimpleArrayDictionary<>();

        for (int i = 0; i < vertices.length; i++) {
            distancia.put(vertices[i], Integer.MAX_VALUE);
            visitado.put(vertices[i], false);
            predecesor.put(vertices[i], null);
        }

        distancia.put(origen, 0);

        while (true) {
            String actual = null;
            int menor = Integer.MAX_VALUE;

            for (int i = 0; i < vertices.length; i++) {
                if (!visitado.get(vertices[i]) && distancia.get(vertices[i]) < menor) {
                    menor = distancia.get(vertices[i]);
                    actual = vertices[i];
                }
            }

            if (actual == null || actual.equals(destino))
                break;

            visitado.put(actual, true);

            SimpleList<Edge<String>> vecinos = mapa.getNeighbors(actual);

            for (int i = 0; i < vecinos.size(); i++) {
                Edge<String> edge = vecinos.get(i);
                String destinoVecino = edge.getDestination();

                if (visitado.get(destinoVecino))
                    continue;

                int nuevaDistancia = distancia.get(actual) + edge.getWeight();

                if (nuevaDistancia < distancia.get(destinoVecino)) {
                    distancia.put(destinoVecino, nuevaDistancia);
                    predecesor.put(destinoVecino, actual);
                }
            }
        }

        caminoCalculado = predecesor;
        return distancia.get(destino);
    }

    private SimpleDictionary<String, String> caminoCalculado;

    private String reconstruirCamino(String origen, String destino) {
        if (caminoCalculado == null)
            return origen;

        String[] partes = new String[obtenerVertices().length];
        int cantidad = 0;
        String actual = destino;

        while (actual != null) {
            partes[cantidad++] = actual;
            actual = caminoCalculado.get(actual);
        }

        StringBuilder camino = new StringBuilder();
        for (int i = cantidad - 1; i >= 0; i--) {
            if (camino.length() > 0)
                camino.append(" → ");
            camino.append(partes[i]);
        }

        return camino.toString();
    }

    private void agregarRuta() {
        System.out.println("\n--- Agregar ruta ---");

        String origen = leerCiudad("Ciudad origen: ");
        if (origen == null)
            return;

        String destino = leerCiudad("Ciudad destino: ");
        if (destino == null)
            return;

        if (origen.equalsIgnoreCase(destino)) {
            System.out.println("❌ Origen y destino deben ser ciudades distintas.");
            return;
        }

        int distancia = leerDistanciaPositiva("Distancia (km): ");
        if (distancia < 0)
            return;

        boolean existia = mapa.containsEdge(origen, destino);
        boolean resultado = mapa.addEdge(origen, destino, distancia);

        if (existia) {
            System.out.println("✓ La ruta ya existía. Se actualizó la distancia.");
        } else if (resultado) {
            System.out.println("✓ Ruta agregada correctamente.");
        } else {
            System.out.println("❌ No se pudo agregar la ruta.");
        }

        imprimirMapaActualizado();
    }

    private void editarRuta() {
        System.out.println("\n--- Editar ruta ---");

        String origen = leerCiudad("Ciudad origen: ");
        if (origen == null)
            return;

        String destino = leerCiudad("Ciudad destino: ");
        if (destino == null)
            return;

        if (!mapa.containsEdge(origen, destino)) {
            System.out.println("❌ La ruta indicada no existe en el mapa.");
            return;
        }

        int distanciaActual = mapa.getWeight(origen, destino);
        System.out.println("Distancia actual: " + distanciaActual + " km");

        int nuevaDistancia = leerDistanciaPositiva("Nueva distancia (km): ");
        if (nuevaDistancia < 0)
            return;

        mapa.addEdge(origen, destino, nuevaDistancia);
        System.out.println("✓ Ruta editada correctamente.");

        imprimirMapaActualizado();
    }

    private void eliminarRuta() {
        System.out.println("\n--- Eliminar ruta ---");

        String origen = leerCiudad("Ciudad origen: ");
        if (origen == null)
            return;

        String destino = leerCiudad("Ciudad destino: ");
        if (destino == null)
            return;

        boolean resultado = mapa.removeEdge(origen, destino);

        if (resultado) {
            System.out.println("✓ Ruta eliminada correctamente.");
        } else {
            System.out.println("❌ La ruta no existe en el mapa.");
        }

        imprimirMapaActualizado();
    }

    private String leerCiudad(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String ciudad = scanner.nextLine().trim();

            if (!ciudad.isEmpty())
                return ciudad;

            System.out.println("❌ El nombre de la ciudad no puede estar vacío.");
        }
    }

    private int leerDistanciaPositiva(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            int distancia = readSafeInt();
            scanner.nextLine();

            if (distancia > 0)
                return distancia;

            System.out.println("❌ La distancia debe ser un número entero positivo.");
        }
    }

    private void imprimirMapaActualizado() {
        System.out.println("\n--- Mapa actualizado ---");
        mapa.printGraph();
    }
}
