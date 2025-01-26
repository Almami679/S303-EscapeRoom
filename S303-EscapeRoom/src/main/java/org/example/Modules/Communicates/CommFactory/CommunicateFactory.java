package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Communicates.*;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;

import java.sql.SQLException;

import java.sql.SQLException;


public class CommunicateFactory {

    public Entity createCommunicate(CommunicateType communicate, Player player) {

        return switch (communicate) {
            case TICKET -> new TicketFactory().createCommunicate(player);
            case NOTIFICATION -> new NotificationFactory().createCommunicate(player);
            case GIFT -> new GiftFactory().createCommunicate(player);
            case CERTIFICATE -> new CertificateFactory().createCommunicate(player);
        };

    }
}
