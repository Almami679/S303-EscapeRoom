package org.example.Services.EscapeRoomServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.EscapeRoomNotFoundException;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
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
    private static Logger logger = LogManager.getLogger(RoomService.class);
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

    }

    public void addObserver(Observer observer) {
        notifier.addObserver(observer);
    }

    private EscapeRoom castToEscapeRoom(Entity entity) {
        EscapeRoom escapeRoom = null;
        if (entity instanceof EscapeRoom) {
            escapeRoom = (EscapeRoom) entity;
        }
        return escapeRoom;
    }

    private Notification castToNotification(Entity entity) {
        Notification notification = null;
        if (entity instanceof Notification) {
            notification = (Notification) entity;
        }
        return notification;
    }


    private void assertIfEscapeRoomIdNotFound(int id) throws SQLException {
        this.repository
                .getAll(EntityAttributes.escaperoom)
                .stream()
                .map(this::castToEscapeRoom)
                .forEach(escaperoom -> {
                    if (escaperoom.getId() != id) {
                        throw new EscapeRoomNotFoundException();
                    }
                });
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
            this
                    .repository
                    .add(escaperoom, EntityAttributes.escaperoom);
            String message = "Se ha creado un nuevo EscapeRoom: " + name + " con el tema " + theme;
            notifier.notifyObservers(message);

            List<Player> players = new ArrayList<>();
            this.repository.getAll(EntityAttributes.player).forEach(entity -> {
                if (entity instanceof Player) {
                    players.add((Player) entity);
                }
            });

//            for (Player player : players) {
//                if (player.getConsentNotif() == 1) {
//                    notificationService.createNotification(notification);
//                    logger.info("Notificación enviada a: " + player.getName());
//                }
//            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public EscapeRoom getEscapeRoomById(
            int id
    ) {
        try {
            //this.assertIfEscapeRoomIdNotFound(id);
            return (EscapeRoom) this.repository
                    .getById(id, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteEscapeRoom(
            int id
    ) {
        try {
            this.assertIfEscapeRoomIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.escaperoom);
        } catch (PlayerNotFound | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateEscapeRoom(
            int id,
            String name,
            String theme,
            double price
    ) {
        try {

            this.assertIfEscapeRoomIdNotFound(id);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        EscapeRoom escaperoom = this.castToEscapeRoom(entity);
        escaperoom.setPrice(price);
        escaperoom.setName(name);
        escaperoom.setTheme(theme);
        this.repository
                .update(escaperoom, EntityAttributes.escaperoom);
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

    public ArrayList<Room> getRoomInEscapeRoom(int roomId) {
        RepositoryEscapeHasRoom repositoryEscapeHasRoom = new RepositoryEscapeHasRoom();
        try {
            return repositoryEscapeHasRoom.getAllRoomsByEscapeRoomId(roomId);
        } catch (SQLException e) {
            logger.info("Fail to get Objects in Room[id: " + roomId + "]");
        }
        return null;
    }
}
