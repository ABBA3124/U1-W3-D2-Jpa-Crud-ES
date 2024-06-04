package davideabbadessa;

import davideabbadessa.dao.EventoDAO;
import davideabbadessa.entities.EventType;
import davideabbadessa.entities.Evento;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U1-W3-D1-jpa-crud");
    private static EventoDAO eventoDAO = new EventoDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Scegli un'opzione:");
            System.out.println("1. Crea un nuovo evento");
            System.out.println("2. Visualizza tutti gli eventi disponibili");
            System.out.println("3. Mostra un evento per ID");
            System.out.println("4. Elimina un evento per ID");
            System.out.println("5. Esci");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createEvent(scanner);
                    break;
                case 2:
                    showAllEvents();
                    break;
                case 3:
                    showEventById(scanner);
                    break;
                case 4:
                    deleteEventById(scanner);
                    break;
                case 5:
                    System.out.println("Uscita...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova!");
            }
        }
    }

    private static void createEvent(Scanner scanner) {
        System.out.println("Inserisci il titolo dell'evento:");
        String title = scanner.nextLine();

        System.out.println("Inserisci la data dell'evento E.G.(yyyy-mm-dd):");
        LocalDate eventDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Inserisci la descrizione dell'evento:");
        String description = scanner.nextLine();

        System.out.println("Inserisci il tipo di evento (PUBBLICO/PRIVATO):");
        EventType eventType = EventType.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Inserisci il numero massimo di partecipanti:");
        int maxParticipants = scanner.nextInt();
        scanner.nextLine();

        Evento evento = new Evento(title, eventDate, description, eventType, maxParticipants);
        eventoDAO.save(evento);

        System.out.println("Evento creato con successo!");
    }

    private static void showEventById(Scanner scanner) {
        System.out.println("Inserisci ID dell'evento da mostrare:");
        long id = scanner.nextLong();
        scanner.nextLine();

        Evento evento = eventoDAO.getById(id);
        if (evento != null) {
            System.out.println("Evento cercato: " + evento.getTitle() + ", Data: " + evento.getEventDate() + ", Descrizione: " + evento.getDescription() + ", Tipo: " + evento.getEventType() + ", Max Partecipanti: " + evento.getMaxParticipants());
        } else {
            System.out.println("Evento non trovato. Assicurati che ID inserito sia corretto e riprova.");
        }
    }

    private static void deleteEventById(Scanner scanner) {
        System.out.println("Inserisci ID dell'evento da eliminare:");
        long id = scanner.nextLong();
        scanner.nextLine();

        eventoDAO.delete(id);
        System.out.println("Evento eliminato.");
    }

    private static void showAllEvents() {
        List<Evento> eventi = eventoDAO.findAll();
        if (eventi.isEmpty()) {
            System.out.println("Non ci sono eventi disponibili.");
        } else {
            System.out.println("Eventi disponibili:");
            for (Evento evento : eventi) {
                System.out.println("ID: " + evento.getId() + ", Titolo: " + evento.getTitle() + ", Data: " + evento.getEventDate() + ", Descrizione: " + evento.getDescription() + ", Tipo: " + evento.getEventType() + ", Max Partecipanti: " + evento.getMaxParticipants());
            }
        }
    }
}
