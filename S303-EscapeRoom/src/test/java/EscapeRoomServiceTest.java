

import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomNotifier;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Services.EscapeRoomServices.EscapeRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EscapeRoomServiceTest {
    private Repository repository;
    private EscapeRoomService escapeRoomService;
    private EscapeRoomNotifier notifier;
    private ObserverTest observerTest;
    private EscapeRoomBuilder builder;

    @BeforeEach
    void setUp() {
        repository = new RepositoryImpl();
        notifier = new EscapeRoomNotifier();
        builder = new EscapeRoomBuilder(notifier);
        escapeRoomService = new EscapeRoomService();
        observerTest = new ObserverTest();
        escapeRoomService.addObserver(observerTest);
    }

    @Test
    void observerTestReceivesNotificationWhenEscapeRoomIsCreated() {
        String name = "Escape Room 1";
        double price = 50.0;
        String theme = "Mystery";
        escapeRoomService.createEscapeRoom(name, price, theme);

        String expectedMessage = "Se ha creado un nuevo EscapeRoom: " + name + " con el tema " + theme;
        assertEquals(expectedMessage, observerTest.getReceivedMessage(), "El observador no recibió el mensaje esperado");
    }

    @Test
    void notificationIsSavedInDatabaseWhenEscapeRoomIsCreated() throws SQLException {
        String name = "Escape Room 2";
        double price = 70.0;
        String theme = "Adventure";

        // Crear un nuevo EscapeRoom
        escapeRoomService.createEscapeRoom(name, price, theme);

            List<Notification> notifications = new ArrayList<>();
            for (Entity entityNotif : repository.getAll(EntityAttributes.notification)) {
                if (entityNotif instanceof Notification) {
                    notifications.add((Notification) entityNotif); // Castear correctamente a EscapeRoom
                }
                assertFalse(notifications.isEmpty(), "No se encontró ninguna notificación en la base de datos");

                // Verificar que solo el jugador con consentimiento recibió la notificación
                Player player1 = (Player) repository.getById(1, EntityAttributes.player);
                Player player2 = (Player) repository.getById(6, EntityAttributes.player);

                // Notificación para player1 (con consentimiento)
                String expectedMessage1 = "News flash!\nTest Player 1 You can't miss the new Christmas Escaperoom from November 12 to January 10.\nNotification sent to email: test1@example.com";
                boolean notificationExistsForPlayer1 = notifications.stream()
                        .anyMatch(notification -> notification.getText().equals(expectedMessage1));
                assertFalse(notificationExistsForPlayer1, "La notificación para player1 no fue guardada correctamente en la base de datos");

                // No debe haber notificación para player2 (sin consentimiento)
                boolean notificationExistsForPlayer2 = notifications.stream()
                        .anyMatch(notification -> notification.getPlayer().getId() == player2.getId());
                assertFalse(notificationExistsForPlayer2, "No debería haberse creado una notificación para player2.");

                // Verificar que el observer fue notificado
                String expectedObserverMessage = "Se ha creado un nuevo EscapeRoom: " + name + " con el tema " + theme;
                assertEquals(expectedObserverMessage, observerTest.getReceivedMessage(), "El observador no recibió el mensaje esperado.");
            }
        }


    @Test
    void observerTestDoesNotReceiveNotificationIfNotAdded() {
        // Crear un nuevo observador pero no agregarlo al servicio
        ObserverTest anotherObserver = new ObserverTest();

        // Crear un EscapeRoom
        String name = "Escape Room 2";
        double price = 70.0;
        String theme = "Adventure";
        escapeRoomService.createEscapeRoom(name, price, theme);

        // Verificar que el segundo observador no recibió notificación
        assertNull(anotherObserver.getReceivedMessage(), "El segundo observador no debería haber recibido una notificación");
    }

    @Test
    void observerTestReceivesUpdatedNotification() {
        // Crear un nuevo EscapeRoom
        String name = "Escape Room 3";
        double price = 60.0;
        String theme = "Puzzle";
        escapeRoomService.createEscapeRoom(name, price, theme);

        // Limpiar y volver a enviar la notificación
        observerTest.clearReceivedMessage();
        escapeRoomService.createEscapeRoom("Escape Room 4", 80.0, "Horror");

        // Verificar que el mensaje ha sido actualizado
        String expectedMessage = "Se ha creado un nuevo EscapeRoom: Escape Room 4 con el tema Horror";
        assertEquals(expectedMessage, observerTest.getReceivedMessage(), "El observador no recibió la notificación actualizada");
    }

}

