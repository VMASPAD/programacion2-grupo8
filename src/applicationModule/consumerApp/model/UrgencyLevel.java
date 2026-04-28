package applicationModule.consumerApp.model;

/**
 * Enumeración que define los niveles de urgencia de un reclamo.
 * CRITICAL: máxima prioridad (peso 1)
 * HIGH: alta prioridad (peso 2)
 * MEDIUM: prioridad media (peso 3)
 * LOW: baja prioridad (peso 4)
 */
public enum UrgencyLevel {
    CRITICAL(1, "CRÍTICO"),
    HIGH(2, "ALTO"),
    MEDIUM(3, "MEDIO"),
    LOW(4, "BAJO");

    private final int weight;
    private final String label;

    UrgencyLevel(int weight, String label) {
        this.weight = weight;
        this.label = label;
    }

    /**
     * Obtiene el valor numérico de prioridad (menor número = mayor prioridad).
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Obtiene la etiqueta visual del nivel de urgencia.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Convierte un índice numérico a UrgencyLevel.
     * @param index índice del 1 al 4
     * @return el UrgencyLevel correspondiente o null si es inválido
     */
    public static UrgencyLevel fromIndex(int index) {
        switch (index) {
            case 1: return CRITICAL;
            case 2: return HIGH;
            case 3: return MEDIUM;
            case 4: return LOW;
            default: return null;
        }
    }
}
