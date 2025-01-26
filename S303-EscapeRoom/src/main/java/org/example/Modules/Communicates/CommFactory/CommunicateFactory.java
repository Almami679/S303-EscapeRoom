package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Communicates.*;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Player;

import java.sql.SQLException;

import java.sql.SQLException;


public class CommunicateFactory {

    public Entity createCommunicate(CommunicateType communicate, int playerId) {

        return switch (communicate) {
            case TICKET -> new TicketFactory().createCommunicate(playerId);
            case NOTIFICATION -> new NotificationFactory().createCommunicate(playerId);
            case GIFT -> new GiftFactory().createCommunicate(playerId);
            case CERTIFICATE -> new CertificateFactory().createCommunicate(playerId);
        };

    }
}
