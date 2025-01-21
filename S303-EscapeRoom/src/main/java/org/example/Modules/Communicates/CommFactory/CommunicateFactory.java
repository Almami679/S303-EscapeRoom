package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Communicates.*;
import java.sql.SQLException;

import java.sql.SQLException;


public class CommunicateFactory {

    public Object createCommunicate(CommunicateType communicate, int idPlayer) throws SQLException {

        return switch (communicate) {
            case TICKET -> new TicketFactory().createCommunicate(idPlayer);
            case NOTIFICATION -> new NotificationFactory().createCommunicate(idPlayer);
            case GIFT -> new GiftFactory().createCommunicate(idPlayer);
            case CERTIFICATE -> new CertificateFactory().createCommunicate(idPlayer);
        };

    }
}
