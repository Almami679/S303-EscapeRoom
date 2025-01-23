import org.example.Services.GameServices.PlayerService;
import org.example.Modules.Entities.GameEntities.Player;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class ServicesTesting {

    @DisplayName("TEST CREATE NEW PLAYER FROM PlayerService")
    @Test
    public void testNewPlayerFromPlayerService(){
        PlayerService playerService = new PlayerService();
        Player playerTest = new Player ("Juan", "juanito@gmail.com", 0);

        playerService.createPlayer("Juan", "juanito@gmail.com", 0);

        ArrayList<Player> playersInDb = new ArrayList<>();
        playersInDb = playerService.getAllPlayer();

        String lastPlayerInDb = playersInDb.getLast().getEmail();
        assertEquals("Player Found in Db", playerTest.getEmail(), lastPlayerInDb);


    }

}
