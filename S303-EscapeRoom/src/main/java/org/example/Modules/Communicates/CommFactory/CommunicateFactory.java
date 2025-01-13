package org.example.Modules.Communicates.CommFactory;

import org.example.Modules.Communicates.*;
import org.example.Modules.CLASESTESTS.PlayerTEST;


public class CommunicateFactory {

    public Object createCommunicate(CommunicateType communicate, PlayerTEST player) {

        return switch (communicate) {
            case TICKET -> new TicketFactory().createCommunicate(player);
            case NOTIFICATION -> new NotificationFactory().createCommunicate(player);
            case GIFT -> new GiftFactory().createCommunicate(player);
            case CERTIFICATE -> new CertificateFactory().createCommunicate(player);
        };

    }
}
