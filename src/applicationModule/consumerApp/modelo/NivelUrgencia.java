package applicationModule.consumerApp.modelo;

/**
 * Enumeración que define los niveles de urgencia para un reclamo.
 * CRÍTICO: máxima prioridad (peso 1)
 * ALTO: alta prioridad (peso 2)
 * MEDIO: prioridad media (peso 3)
 * BAJO: baja prioridad (peso 4)
 */
public enum NivelUrgencia {
    CRITICO(1, "🔴 CRÍTICO"),
    ALTO(2, "🟠 ALTO"),
    MEDIO(3, "🟡 MEDIO"),
    BAJO(4, "🟢 BAJO");

    private final int peso;
    private final String etiqueta;

    NivelUrgencia(int peso, String etiqueta) {
        this.peso = peso;
        this.etiqueta = etiqueta;
    }

    /**
     * Obtiene el valor numérico de prioridad (menor número = mayor prioridad).
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Obtiene la etiqueta visual del nivel de urgencia.
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * Convierte un índice numérico a NivelUrgencia.
     * @param index índice de 1 a 4
     * @return el NivelUrgencia correspondiente o null si es inválido
     */
    public static NivelUrgencia fromIndex(int index) {
        switch (index) {
            case 1: return CRITICO;
            case 2: return ALTO;
            case 3: return MEDIO;
            case 4: return BAJO;
            default: return null;
        }
    }
}
