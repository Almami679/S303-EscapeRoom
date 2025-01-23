package org.example.Main.Services.CommunicatesServices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.CertificateNotFoundException;
import org.example.Modules.Communicates.CommFactory.CommunicateFactory;
import org.example.Modules.Communicates.CommunicateType;
import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;

import java.sql.SQLException;
import java.util.ArrayList;


public class CertificateService {
    private static final Logger logger = LogManager.getLogger(CertificateService.class);

    private final CommunicateFactory mainFactory = new CommunicateFactory();
    private final Repository repository;
    private final Entity entity = new Entity();


    public CertificateService(Repository repository) {
        this.repository = repository;
    }

    private Certificate castToCertificate(Entity entity) {
        Certificate certificate = null;
        if (entity instanceof Certificate) {
            certificate = (Certificate) entity;
        }
        return certificate;
    }

    private void assertIfCertificateIdNotFound(int id) throws SQLException {
        this.repository
                .getAll(EntityAttributes.certificate)
                .stream()
                .map(this::castToCertificate)
                .forEach(certificate -> {
                    if (certificate.getId() != id) {
                        throw new CertificateNotFoundException("Certificate with " + id + " id not found");
                    }
                });
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

    public void getCertificateById(
            int id
    ) {
        try {
            this.assertIfCertificateIdNotFound(id);
            this.repository
                    .getById(id, EntityAttributes.certificate);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void deleteCertificate(
            int id
    ) {
        try {
            this.assertIfCertificateIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.certificate);
        } catch (CertificateNotFoundException | SQLException e) {
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateCertificate(
            int id,
            Player player,
            String text,
            Game game
    ) {
        try {
            this.assertIfCertificateIdNotFound(id);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        Certificate certificate = this.castToCertificate(entity);
        certificate.setPlayer(player);
        certificate.setText(text);
        certificate.setGame(game);

        this.repository
                .update(certificate, EntityAttributes.certificate);
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
