package dk.dtu.gruppe9.flaskehalsenpegerp;

public class Player {
    // Picture
    String name = "";
    int numberOfDrinks = 0;
    float bac = 0f;

    public Player() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void playerDrink() {
        numberOfDrinks++;
    }

    public void updatePicture() {

    }

    public float calculateBac() {
        // Get a reference to when the game started

        return bac;
    }



}
