package org.example.Services.CommunicatesServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.NotificationNotFoundException;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.CommunicatesEntities.Gift;
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
            Notification notification = (Notification) this.repository.getById(id, EntityAttributes.notification);

            if (notification == null) {
                throw new NotificationNotFoundException("Notification with id " + id + " not found");
            } else {
                return (Notification) this.repository
                        .getById(id, EntityAttributes.notification);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteNotification(
            int id
    ) {
        try {
            Notification notification = (Notification) this.repository.getById(id, EntityAttributes.notification);

            if (notification == null) {
                throw new NotificationNotFoundException("Notification with id " + id + " not found");
            } else {
                this.repository
                        .delete(id, EntityAttributes.notification);
            }
        } catch (NotificationNotFoundException | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void updateNotification(
            int id,
            int player,
            String text
    ) {
        try {

            Notification notification = (Notification) this.repository.getById(id, EntityAttributes.notification);

            if (notification == null) {
                throw new NotificationNotFoundException("Notification with id " + id + " not found");
            } else {
                notification.setPlayer(player);
                notification.setText(text);
                this.repository
                        .update(notification, EntityAttributes.notification);
            }
        } catch (NotificationNotFoundException | SQLException e) {
            logger.info(e.getMessage());
        }
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
