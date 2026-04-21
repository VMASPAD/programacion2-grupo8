package applicationModule.consumerApp.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un reclamo registrado por un cliente.
 * Contiene título, descripción, nivel de urgencia y timestamp de creación.
 */
public class Reclamo {
    private final String titulo;
    private final String descripcion;
    private final NivelUrgencia urgencia;
    private final LocalDateTime fechaCreacion;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Reclamo(String titulo, String descripcion, NivelUrgencia urgencia) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urgencia = urgencia;
        this.fechaCreacion = LocalDateTime.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public NivelUrgencia getUrgencia() {
        return urgencia;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public String toString() {
        return String.format(
            "%s\n  Título: %s\n  Descripción: %s\n  Registrado: %s",
            urgencia.getEtiqueta(),
            titulo,
            descripcion,
            fechaCreacion.format(formatter)
        );
    }
}
