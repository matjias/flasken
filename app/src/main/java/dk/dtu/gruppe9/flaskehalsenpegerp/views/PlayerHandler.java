package dk.dtu.gruppe9.flaskehalsenpegerp.views;


import dk.dtu.gruppe9.flaskehalsenpegerp.Player;

public final class PlayerHandler {

    private static Player[] players;

    public void playerHandler(){

    }

    public static void setPlayers(Player[] ps){
        players = ps;
    }

    public static Player[] getPlayers() {
        return players;
    }
    public static Player getPlayer(int id){
        return players[id];
    }

}
