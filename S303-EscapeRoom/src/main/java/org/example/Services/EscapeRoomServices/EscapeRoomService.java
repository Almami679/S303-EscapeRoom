package org.example.Services.EscapeRoomServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.EscapeRoomNotFoundException;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomHasRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomNotifier;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Modules.Entities.RoomEntities.Room;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;
import org.example.Repository.RepositoryRelations.RepositoryEscapeHasRoom;
import org.example.Services.CommunicatesServices.NotificationService;
import org.example.observers.Observer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EscapeRoomService {
    private static Logger logger = LogManager.getLogger(EscapeRoomService.class);
    private final EscapeRoomNotifier notifier;
    private final EscapeRoomBuilder builder;
    private final Repository repository;
    private final NotificationService notificationService;
    private final Entity entity = new Entity();


    public EscapeRoomService() {
        this.notifier = new EscapeRoomNotifier();
        this.builder = new EscapeRoomBuilder(notifier);
        this.repository = new RepositoryImpl();
        this.notificationService = new NotificationService();
        //registerPlayersAsObservers();

    }

    public void registerPlayersAsObservers() {
        try {
            List<Player> players = new ArrayList<>();
            this.repository
                    .getAll(EntityAttributes.player)
                    .forEach(player ->
                            players.add((Player) player)
                    );

            players.forEach(player -> {
                if (player.getConsentNotif() == 1) {
                    notifier.addObserver(player); // Registra solo a los jugadores que consienten notificaciones
                }
            });
            logger.info("Jugadores registrados como observadores exitosamente.");
        } catch (SQLException e) {
            logger.error("Error al registrar jugadores como observadores: " + e.getMessage());
        }
    }


    public void addObserver(Observer observer) {
        notifier.addObserver(observer);
    }


    public void createEscapeRoom(
            String name,
            double price,
            String theme
    ) {
        try {
            builder.setName(name);
            builder.setPrice(price);
            builder.setTheme(theme);
            EscapeRoom escaperoom = builder.build();
            registerPlayersAsObservers();
            this    .repository
                    .add(escaperoom, EntityAttributes.escaperoom);
            String message = "Se ha creado un nuevo EscapeRoom: " + name + " con el tema " + theme;
            notifier.notifyObservers(message);

        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public EscapeRoom getEscapeRoomById(
            int id
    ) {
        try {
            EscapeRoom escapeRoom = (EscapeRoom) this.repository.getById(id, EntityAttributes.escaperoom);

            if (escapeRoom == null) {
                throw new EscapeRoomNotFoundException();
            } else {
                return (EscapeRoom) this.repository
                        .getById(id, EntityAttributes.escaperoom);
            }
        } catch (SQLException | EscapeRoomNotFoundException  e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteEscapeRoom(
            int id
    ) {
        try {
            EscapeRoom escapeRoom = (EscapeRoom) this.repository.getById(id, EntityAttributes.escaperoom);

            if (escapeRoom == null) {
                throw new EscapeRoomNotFoundException();
            } else {

            this.repository
                    .delete(id, EntityAttributes.escaperoom);
            }
        } catch (EscapeRoomNotFoundException | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void updateEscapeRoom(
            int id,
            String name,
            String theme,
            double price
    ) {
        try {
            EscapeRoom escapeRoom = (EscapeRoom) this.repository.getById(id, EntityAttributes.escaperoom);

            if (escapeRoom == null) {
                throw new EscapeRoomNotFoundException();
            } else {
                escapeRoom.setPrice(price);
                escapeRoom.setName(name);
                escapeRoom.setTheme(theme);
                this.repository
                        .update(escapeRoom, EntityAttributes.escaperoom);
            }
        } catch (EscapeRoomNotFoundException | SQLException e) {
            logger.info(e.getMessage());
        }


    }

    //Todo verificar estos metodos
    public ArrayList<EscapeRoom> getAllEscapeRooms() {
        ArrayList<EscapeRoom> outputList = new ArrayList<>();
        try {
            this.repository
                    .getAll(EntityAttributes.escaperoom).forEach(entity -> outputList.add((EscapeRoom) entity));
            return outputList;
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public ArrayList<Room> getRoomInEscapeRoom(int escapeRoomId) {
        RepositoryEscapeHasRoom repositoryEscapeHasRoom = new RepositoryEscapeHasRoom();
        try {
            return repositoryEscapeHasRoom.getAllRoomsByEscapeRoomId(escapeRoomId);
        } catch (SQLException e) {
            logger.info("Failed to get rooms in escaperoom[id: " + escapeRoomId + "]");
        }
        return null;
    }

    public void addRoomToEscapeRoom(int escapeRoomId, int roomID) {
        RepositoryEscapeHasRoom repo = new RepositoryEscapeHasRoom();
        Entity entity = new EscapeRoomHasRoom(roomID, escapeRoomId);
        try {
            repo.addEscapeRoomHasRoom(entity);
        } catch (SQLException e) {
            logger.info("Failed to add room[id: " + roomID + "] to escape room[id: " + escapeRoomId + "]");
        }
    }
}