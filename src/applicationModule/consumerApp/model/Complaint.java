package applicationModule.consumerApp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un reclamo registrado por un cliente.
 * Contiene título, descripción, nivel de urgencia y marca de tiempo de creación.
 */
public class Complaint {
    private final String title;
    private final String description;
    private final UrgencyLevel urgency;
    private final LocalDateTime creationDate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Complaint(String title, String description, UrgencyLevel urgency) {
        this.title = title;
        this.description = description;
        this.urgency = urgency;
        this.creationDate = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UrgencyLevel getUrgency() {
        return urgency;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return String.format(
            "%s\n  Título: %s\n  Descripción: %s\n  Registrado: %s",
            urgency.getLabel(),
            title,
            description,
            creationDate.format(formatter)
        );
    }
}
