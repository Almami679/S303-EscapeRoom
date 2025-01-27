package org.example.Services.CommunicatesServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.CertificateNotFoundException;
import org.example.Exceptions.GiftNotFoundException;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.CommunicatesEntities.Gift;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;
import org.example.Repository.Common.RepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;


public class CertificateService {
    private static final Logger logger = LogManager.getLogger(CertificateService.class);

    private final CommunicateFactory mainFactory = new CommunicateFactory();
    private final Repository repository;
    private final Entity entity = new Entity();


    public CertificateService() {
        this.repository = new RepositoryImpl();
    }


    public void createCertificate(
            int playerId
    ) {
        try {
            Certificate certificate = (Certificate) mainFactory.createCommunicate(CommunicateType.CERTIFICATE, playerId);
            this
                    .repository
                    .add(certificate, EntityAttributes.certificate);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public Certificate getCertificateById(
            int id
    ) {
        try {
            Certificate certificate = (Certificate) this.repository.getById(id, EntityAttributes.certificate);

            if (certificate == null) {
                throw new CertificateNotFoundException("Certificate with id " + id + " not found");
            } else {
                return (Certificate) this.repository
                        .getById(id, EntityAttributes.certificate);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void deleteCertificate(
            int id
    ) {
        try {
            Certificate certificate = (Certificate) this.repository.getById(id, EntityAttributes.certificate);

            if (certificate == null) {
                throw new CertificateNotFoundException("Certificate with id " + id + " not found");
            } else {
                this.repository
                        .delete(id, EntityAttributes.certificate);
            }
        } catch (CertificateNotFoundException | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void updateCertificate(
            int id,
            int player,
            String text,
            int gameId
    ) {
        try {
            Certificate certificate = (Certificate) this.repository.getById(id, EntityAttributes.certificate);

            if (certificate == null) {
                throw new CertificateNotFoundException("Certificate with id " + id + " not found");
            } else {
                certificate.setPlayer(player);
                certificate.setText(text);
                certificate.setGame(gameId);

                this.repository
                        .update(certificate, EntityAttributes.certificate);

            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

            }

    public ArrayList<Certificate> getAllCertificate() {
        ArrayList<Certificate> certificateArrayList = new ArrayList<>();
        try {

            this.repository
                    .getAll(EntityAttributes.certificate)
                    .forEach(certificate -> certificateArrayList.add((Certificate) certificate));
            return certificateArrayList;
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

}
