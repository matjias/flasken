package dk.dtu.gruppe9.flaskehalsenpegerp.views;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import dk.dtu.gruppe9.flaskehalsenpegerp.GameActivity;
import dk.dtu.gruppe9.flaskehalsenpegerp.PlayerActivity;
import dk.dtu.gruppe9.flaskehalsenpegerp.R;

public class PlayerView extends View{

    final double SCALE = 0.5;
    final float IMAGE_TEXT_MARGIN = 10, PLAYER_TEXT_SIZE = 50, IMAGE_TEXT_SIZE = 140;
    int radius, posX, posY;
    boolean hasCustomImage;
    Bitmap playerBitmap, scaledPlayerBitmap;
    Paint imagePainter, textPainter, borderPainter;
    Context context;
    String name, fullname;

    public PlayerView(Context context, int posX, int posY, String name){
        super(context);
        this.context = context;

        this.posX = posX;
        this.posY = posY;

        this.name = name;
        fullname = "player" + name;

        imagePainter = new Paint();
        textPainter = new Paint();
        borderPainter = new Paint();

        setImage(R.drawable.player_default);

        textPainter.setColor(Color.WHITE);
    }

    public void setImage(int source){

        playerBitmap = BitmapFactory.decodeResource(getResources(), source);

        setCustomImage(playerBitmap, false);
    }

    public void setCustomImage(Bitmap customImage, boolean isCustom){

        //System.out.print("image: " + customImage);

        hasCustomImage = isCustom;

        radius = (int) (customImage.getWidth() * SCALE / 2);

        scaledPlayerBitmap = Bitmap.createScaledBitmap(customImage, 2*radius, 2*radius, false);

        textPainter.setTextAlign(Paint.Align.CENTER);

    }


    public void setBorder(){

        this.setLayerType(LAYER_TYPE_SOFTWARE, borderPainter);
        borderPainter.setShadowLayer(radius / 6, 0, 0, Color.argb(255, 0, 0, 0));
        borderPainter.setColor(Color.argb(0,0,0,0));

        if(hasCustomImage){
            borderPainter.setColor(Color.WHITE);
            borderPainter.setStyle(Paint.Style.STROKE);
            borderPainter.setStrokeWidth(10f);
        }

    }

    public void setWin(boolean hasWon){
        int color = hasWon ? Color.argb(255, 172, 211, 115) : Color.WHITE;
        textPainter.setColor(color);
    }

    public boolean intersects(float x, float y){


        return Math.pow(x - posX, 2) + Math.pow(y - posY, 2) <= Math.pow(radius,2);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        //canvas.drawCircle(posX, posY, radius - borderPainter.getStrokeWidth(), borderPainter);

        canvas.drawBitmap(scaledPlayerBitmap, posX - radius, posY - radius, imagePainter);

        if(hasCustomImage){


            textPainter.setTextSize(30f);
            canvas.drawText(fullname, posX, posY + radius + IMAGE_TEXT_MARGIN, textPainter);

        }
        else{


            textPainter.setTextSize(IMAGE_TEXT_SIZE);

            Rect textBounds = new Rect();
            textPainter.getTextBounds(name, 0, name.length(), textBounds);

            canvas.drawText(name, posX, posY - textBounds.exactCenterY(), textPainter);

        }

        canvas.restore();

    }


}
