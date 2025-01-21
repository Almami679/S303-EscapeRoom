package org.example.entity_services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.NotificationNotFoundException;
import org.example.Exceptions.PlayerNotFound;
import org.example.Modules.Entities.CommunicatesEntities.Notification;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;

import java.sql.SQLException;

public class NotificationService {
    private static final Logger logger = LogManager.getLogger(NotificationService.class);

    private final Repository repository;
    private final Entity entity = new Entity();


    public NotificationService(Repository repository) {
        this.repository = repository;
    }

    private Notification castToNotification(Entity entity) {
        Notification notification = null;
        if (entity instanceof Notification) {
            notification = (Notification) entity;
        }
        return notification;
    }



    private void assertIfNotificationIdNotFound(int id) {
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
            Player player,
            String text
    ) {
        try {
            this
                    .repository
                    .add(new Notification(player, text), EntityAttributes.notification);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void getNotificationById(
            int id
    ) {
        try {
            this.assertIfNotificationIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.notification);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void deleteNotification(
            int id
    ){
        try{
            this.assertIfNotificationIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.notification);
        }catch (PlayerNotFound e){
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateNotification(
            int id,
            Player player,
            String text
    ) throws SQLException {
        this.assertIfNotificationIdNotFound(id);

        Notification notification = this.castToNotification(entity);
        notification.setPlayer(player);
        notification.setText(text);

        this.repository
                .update(notification, EntityAttributes.notification);
    }

    //Todo verificar estos metodos
    public void getAllNotification(){
        this.repository
                .getAll(EntityAttributes.notification);
    }
}
