package org.example.Services.CommunicatesServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.NotificationNotFoundException;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationService {
    private static final Logger logger = LogManager.getLogger(NotificationService.class);
    private final CommunicateFactory mainFactory = new CommunicateFactory();
    private final Repository repository;
    private final Entity entity = new Entity();


    public NotificationService() {
        this.repository = new RepositoryImpl();
    }

    private Notification castToNotification(Entity entity) {
        Notification notification = null;
        if (entity instanceof Notification) {
            notification = (Notification) entity;
        }
        return notification;
    }


    private void assertIfNotificationIdNotFound(int id) throws SQLException {
        this.repository
                .getAll(EntityAttributes.notification)
                .stream()
                .map(this::castToNotification)
                .forEach(notification -> {
                    if (notification.getId() != id) {
                        throw new NotificationNotFoundException("Notification with id: " + id + " not found");
                    }
                });
    }

    public void createNotification(
            Notification notification
    )  {
        try {
            if (notification != null) {
                this.repository.add(notification, EntityAttributes.notification);
                logger.info("Notificación creada y guardada: " + notification.getText());
            } else {
                logger.info("Error: La notificación es nula.");
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public Notification getNotificationById(
            int id
    ) {
        try {
            //this.assertIfNotificationIdNotFound(id);
            return (Notification) this.repository
                    .getById(id, EntityAttributes.notification);
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteNotification(
            int id
    ) {
        try {
            this.assertIfNotificationIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.notification);
        } catch (PlayerNotFound | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateNotification(
            int id,
            int player,
            String text
    ) {
        try {

            this.assertIfNotificationIdNotFound(id);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        Notification notification = this.castToNotification(entity);
        notification.setPlayer(player);
        notification.setText(text);

        this.repository
                .update(notification, EntityAttributes.notification);
    }

    //Todo verificar estos metodos
    public ArrayList<Notification> getAllNotification() {
        ArrayList<Notification> notifications = new ArrayList<>();
        try {

            this.repository
                    .getAll(EntityAttributes.notification)
                    .forEach(notification -> notifications.add((Notification) notification));
            return notifications;
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }
}
