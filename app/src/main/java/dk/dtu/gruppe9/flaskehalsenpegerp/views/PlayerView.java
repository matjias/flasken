package dk.dtu.gruppe9.flaskehalsenpegerp.views;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import dk.dtu.gruppe9.flaskehalsenpegerp.GameActivity;
import dk.dtu.gruppe9.flaskehalsenpegerp.PlayerActivity;
import dk.dtu.gruppe9.flaskehalsenpegerp.R;

public class PlayerView extends View{

    final double SCALE = 1;
    final float IMAGE_TEXT_MARGIN = 10, PLAYER_TEXT_SIZE = 50, IMAGE_TEXT_SIZE = 140;
    int radius, posX, posY;
    boolean hasCustomImage, hasWon = false;
    Bitmap playerBitmap, scaledPlayerBitmap;
    Paint imagePainter, textPainter, borderPainter;
    Context context;
    String name, fullname;

    public PlayerView(Context context, int posX, int posY, String name){
        super(context);
        this.context = context;

        this.posX = posX;
        this.posY = posY;

        radius = 100;

        this.name = name;
        fullname = "player" + name;

        imagePainter = new Paint();
        textPainter = new Paint();
        borderPainter = new Paint();


        textPainter.setColor(Color.WHITE);
        textPainter.setTextAlign(Paint.Align.CENTER);
        imagePainter.setColor(Color.argb(255, 80, 80, 80));
        borderPainter.setColor(Color.WHITE);

        setBorder();
    }

    public void setName(String name){
        this.fullname = name;
    }


    public void setCustomImage(Bitmap customImage, boolean isCustom){

        if (customImage != null) {
            hasCustomImage = isCustom;

            scaledPlayerBitmap = Bitmap.createScaledBitmap(customImage, 2 * radius, 2 * radius, false);
        }
    }

    public void setBorder(){

        this.setLayerType(LAYER_TYPE_SOFTWARE, borderPainter);

        borderPainter.setStyle(Paint.Style.STROKE);
        borderPainter.setStrokeWidth(10f);

    }

    public void setWin(boolean hasWon){
        this.hasWon = hasWon;
        int color = hasWon ? Color.argb(255, 172, 211, 115) : Color.WHITE;
        borderPainter.setColor(color);

    }

    public boolean intersects(float x, float y){


        return Math.pow(x - posX, 2) + Math.pow(y - posY, 2) <= Math.pow(radius,2);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        if(hasCustomImage){

            canvas.drawBitmap(roundBitmap(scaledPlayerBitmap), posX - radius, posY - radius, imagePainter);

            canvas.drawCircle(posX, posY, radius, borderPainter);


        }
        else{

            canvas.drawCircle(posX, posY, radius, imagePainter);

            textPainter.setTextSize(IMAGE_TEXT_SIZE);

            Rect textBounds = new Rect();
            textPainter.getTextBounds(name, 0, name.length(), textBounds);

            canvas.drawText(name, posX, posY - textBounds.exactCenterY(), textPainter);

        }


        if(hasWon) canvas.drawCircle(posX, posY, radius, borderPainter);

        canvas.restore();

    }

    // http://stackoverflow.com/questions/11932805/cropping-circular-area-from-bitmap-in-android
    public Bitmap roundBitmap(Bitmap bitmap) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final Paint painter = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, painter);
        painter.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, painter);

        return output;
    }
}
