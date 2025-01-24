// EscapeRoomTesting.java
import org.assertj.core.api.Assertions;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;

public class EscapeRoomTesting {

    @Test
    ///Arreglado el Null de Timestamp
    public void testEscapeRoomTesting() {
        EscapeRoom escapeRoom1 = new EscapeRoomBuilder()
                .setName("Pedos House")
                .setPrice(50.0)
                .setTheme("Smelly")
                .build();
        RepositoryImpl repository = new RepositoryImpl();

        // Insert
        try {
            repository.add(escapeRoom1, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            Assertions.fail("Failed to add escape room", e);
        }



        // GetById
        EscapeRoom escapeRoom2 = null;
        try {
            escapeRoom2 = (EscapeRoom) repository.getById(5, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            Assertions.fail("Failed to retrieve escape room", e);
        }

        Assertions.assertThat(escapeRoom2).isNotNull();
        Assertions.assertThat(escapeRoom2.getName()).isEqualTo("Pedos House");
        Assertions.assertThat(escapeRoom2.getPrice()).isEqualTo(50.0);
        Assertions.assertThat(escapeRoom2.getTheme()).isEqualTo("Smelly");
        Assertions.assertThat(escapeRoom2.getDeleted()).isEqualTo(0);

        // Delete
        repository.delete(5, EntityAttributes.escaperoom);
        try {
            escapeRoom2 = (EscapeRoom) repository.getById(5, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            Assertions.fail("Failed to retrieve escape room", e);
        }

        Assertions.assertThat(escapeRoom2).isNotNull();
        Assertions.assertThat(escapeRoom2.getDeleted()).isEqualTo(1);
    }
}