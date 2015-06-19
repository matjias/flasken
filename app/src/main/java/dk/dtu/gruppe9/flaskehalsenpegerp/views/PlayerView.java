package dk.dtu.gruppe9.flaskehalsenpegerp.views;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import dk.dtu.gruppe9.flaskehalsenpegerp.R;

public class PlayerView extends View{

    final double SCALE = 0.5;
    final float IMAGE_TEXT_MARGIN = 10, PLAYER_TEXT_SIZE = 50, IMAGE_TEXT_SIZE = 140;
    int width, height, posX, posY;
    boolean hasCustomImage;
    Bitmap playerBitmap, scaledPlayerBitmap;
    Paint imagePainter, textPainter;
    Context context;
    String name;

    public PlayerView(Context context, int posX, int posY, String name){
        super(context);
        this.context = context;

        this.posX = posX;
        this.posY = posY;

        this.name = name;

        imagePainter = new Paint();
        textPainter = new Paint();

        setImage(R.drawable.player_default);


        textPainter.setColor(Color.WHITE);
    }

    public void setImage(int source){

        playerBitmap = BitmapFactory.decodeResource(getResources(), source);

        setRelativeSize();

        scaledPlayerBitmap = Bitmap.createScaledBitmap(playerBitmap, width, height, false);

        setCustomImage(playerBitmap);
    }

    public void setCustomImage(Bitmap customImage){


        hasCustomImage = false;
    }

    private void setRelativeSize(){

        width = (int) (playerBitmap.getWidth() * SCALE);
        height = (int) (playerBitmap.getHeight() * SCALE);
        System.out.println(width + " " + height);
    }

    public void setWin(boolean hasWon){
        int color = hasWon ? Color.GREEN : Color.WHITE;
        textPainter.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        textPainter.setTextAlign(Paint.Align.CENTER);

        if(hasCustomImage){


            canvas.drawBitmap(scaledPlayerBitmap, posX - scaledPlayerBitmap.getWidth() / 2, posY - scaledPlayerBitmap.getHeight() / 2, imagePainter);

            textPainter.setTextSize(PLAYER_TEXT_SIZE);
            canvas.drawText("player" + name, posX, posY + scaledPlayerBitmap.getHeight() / 2 + IMAGE_TEXT_MARGIN, textPainter);


        }
        else{

            canvas.drawBitmap(scaledPlayerBitmap, posX - scaledPlayerBitmap.getWidth() / 2, posY - scaledPlayerBitmap.getHeight() / 2, imagePainter);

            textPainter.setTextSize(IMAGE_TEXT_SIZE);

            Rect textBounds = new Rect();
            textPainter.getTextBounds(name, 0, name.length(), textBounds);

            canvas.drawText(name, posX, posY - textBounds.exactCenterY(), textPainter);

        }



        canvas.restore();

    }


}
