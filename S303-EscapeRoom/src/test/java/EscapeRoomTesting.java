import org.assertj.core.api.Assertions;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoom;
import org.example.Modules.Entities.EscapeRoomEntities.EscapeRoomBuilder;
import org.example.Repository.Common.EntityAttributes;
import org.example.Repository.Common.RepositoryImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

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

        System.out.println("Escape room adding...");
        // Insert
        try {
            repository.add(escapeRoom1, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            Assertions.fail("Failed to add escape room", e);
        }

        System.out.println("Escape room fecthing...");
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

        System.out.println("Escape room updating...");
        // Update
        escapeRoom2.setName("Updated House").setPrice(60.0);
        repository.update(escapeRoom2, EntityAttributes.escaperoom);

        EscapeRoom updatedEscapeRoom = null;
        try {
            updatedEscapeRoom = (EscapeRoom) repository.getById(5, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            Assertions.fail("Failed to retrieve updated escape room", e);
        }

        Assertions.assertThat(updatedEscapeRoom).isNotNull();
        Assertions.assertThat(updatedEscapeRoom.getName()).isEqualTo("Updated House");
        Assertions.assertThat(updatedEscapeRoom.getPrice()).isEqualTo(60.0);
        Assertions.assertThat(updatedEscapeRoom.getTheme()).isEqualTo("Fresh");

        System.out.println("Escape room deleting...");
        // Delete
        repository.delete(4, EntityAttributes.escaperoom);
        try {
            escapeRoom2 = (EscapeRoom) repository.getById(5, EntityAttributes.escaperoom);
        } catch (SQLException e) {
            Assertions.fail("Failed to retrieve escape room", e);
        }

        Assertions.assertThat(escapeRoom2).isNotNull();
        Assertions.assertThat(escapeRoom2.getDeleted()).isEqualTo(1);
    }
}