package dk.dtu.gruppe9.flaskehalsenpegerp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Player {
    // Picture
    int id, numberOfDrinks = 0;
    float weight = 60.0f;
    String name;
    double bac = 0f, drinksPotency=1;
    long startTime;
    Bitmap image;
    boolean isMale = true;


    public Player(int id) {
        startTime = System.currentTimeMillis();
        this.id = id;
        name = "Player " + (id + 1);
    }

    public int getId(){
        return this.id;
    }

    public float getWeight(){
        return this.weight;
    }

    public int getNumberOfDrinks(){
        return this.numberOfDrinks;
    }

    public String getName(){
        return this.name;
    }

    public double getBAC(){
        return updateBAC();
    }

    public double getDrinksPotency(){
        return this.drinksPotency;
    }

    public long getStartTime(){
        return this.startTime;
    }

    public Bitmap getImage(){
       return this.image;
    }

    public boolean isMale(){
        return this.isMale;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

    public void setNumberOfDrinks(int numberOfDrinks){
        this.numberOfDrinks=numberOfDrinks;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setBAC(double bac){
        this.bac=bac;
    }

    public void setDrinksPotency(double drinksPotency){
        this.drinksPotency=drinksPotency;
    }

    public void setStartTime(long startTime){
        this.startTime=startTime;
    }

    public void setImage(Bitmap image){
        this.image=image;
    }

    public void setMale(boolean isMale){
        this.isMale=isMale;
    }

    public void playerDrink() {
        numberOfDrinks++;
        updateBAC();
    }

    public double updateBAC(){

        if(this.isMale){
            this.bac=((0.806*(this.numberOfDrinks*this.drinksPotency)*1.2)
                    /(this.weight*0.58))-(0.015*(System.currentTimeMillis()-this.startTime)/3600000);
        }
        else{
            this.bac=((0.806*(this.numberOfDrinks*this.drinksPotency)*1.2)
                    /(this.weight*0.49))-(0.017*(System.currentTimeMillis()-this.startTime)/3600000);
        }

        if(bac < 0){
            bac = 0;
        }

        return bac;
    }

    public double timeTillSober(){
        if(this.isMale) return this.bac/0.015;
        else return this.bac/0.017;
    }
}
