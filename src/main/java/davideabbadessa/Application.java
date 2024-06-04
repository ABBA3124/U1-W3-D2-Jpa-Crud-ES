package davideabbadessa;


import davideabbadessa.dao.EventoDAO;
import davideabbadessa.entities.EventType;
import davideabbadessa.entities.Evento;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U1-W3-D1-jpa-crud");

    public static void main(String[] args) {
        EventoDAO eventoDAO = new EventoDAO();

        //creo un nuovo evento
        Evento evento = new Evento("Tomorrowland 2024", LocalDate.now(), "Nel pittoresco parco De Schorre in Belgio, dal 19 al 21 luglio e dal 26 al 28 luglio, oltre 400.000 appassionati di musica elettronica da più di 200 paesi assisteranno a performance di oltre 400 artisti di fama mondiale, distribuiti su 16 palcoscenici.", EventType.PRIVATO, 1000000);
        eventoDAO.save(evento);
        System.out.println("Evento creato con successo");

        //recupero evento per id
        Evento retrievedEvento = eventoDAO.getById(evento.getId());
        System.out.println("Evento recuperato per id: " + retrievedEvento.getTitle());

        //elimino l'evento
        eventoDAO.delete(evento.getId());
        System.out.println("Evento eliminato");
    }
}

