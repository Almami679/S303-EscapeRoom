package org.example.entity_services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Exceptions.CertificateNotFoundException;
import org.example.Modules.Entities.CommunicatesEntities.Certificate;
import org.example.Modules.Entities.Entity;
import org.example.Modules.Entities.GameEntities.Game;
import org.example.Modules.Entities.GameEntities.Player;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.Repository;

import java.sql.SQLException;


public class CertificateService {
    private static final Logger logger = LogManager.getLogger(CertificateService.class);

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

    private void assertIfCertificateIdNotFound(int id) {
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
            Player player,
            String text,
            Game game
    ) {
        // assertIfCertificateAlreadyExists(email); //todo no se si este metodo debe ir aqui o dentro de try
        try {
            this
                    .repository
                    .add(new Certificate(player, text, game), EntityAttributes.certificate);
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
    ){
        try{
            this.assertIfCertificateIdNotFound(id);
            this.repository
                    .delete(id, EntityAttributes.certificate);
        }catch (CertificateNotFoundException e){
            logger.info(e.getMessage());
        }
    }

    //Todo verificar estos metodos
    public void updateCertificate(
            int id,
            Player player,
            String text,
            Game game
    ){
        this.assertIfCertificateIdNotFound(id);

        Certificate certificate = this.castToCertificate(entity);
        certificate.setPlayer(player);
        certificate.setText(text);
        certificate.setGame(game);

        this.repository
                .update(certificate, EntityAttributes.certificate);
    }

    //Todo verificar estos metodos
    public void getAllCertificate(){
        this.repository
                .getAll(EntityAttributes.certificate);
    }

}
