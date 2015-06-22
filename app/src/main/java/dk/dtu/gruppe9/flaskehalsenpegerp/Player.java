package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Player {
    // Picture
    int id, data;
    String name;
    int numberOfDrinks = 0;
    float bac = 0f;
    Bitmap image;


    public Player(int id) {

        this.id = id;
        name = "Player " + (id + 1);

    }

    public void setImage(Bitmap image){
        this.image = image;
    }

    public Bitmap getImage(){
        return image;
    }

    public int getID(){
        return id;
    }


    public String getName() {
        return name;
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
