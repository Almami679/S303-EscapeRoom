package org.example.Modules.Communicates;

import org.example.Modules.CLASESTESTS.PlayerTEST;

public class Communicate {

    private static Long nextId = 1L;
    private Long id;
    private PlayerTEST player;
    private CommunicateType type;

    public Communicate(PlayerTEST player) {
        this.player = player;
        this.id = nextId;
        nextId++;
        ///Tema id?? lo genera SQL? o lo asignamos desde la logica del constructor?
    }

    public Long getId() {
        return id;
    }

    public Communicate setId(Long id) {
        this.id = id;
        return this;
    }

    public PlayerTEST getPlayer() {
        return player;
    }

    public Communicate setPlayer(PlayerTEST player) {
        this.player = player;
        return this;
    }
}
