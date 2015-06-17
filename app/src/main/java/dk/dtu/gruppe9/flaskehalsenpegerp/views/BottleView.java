package dk.dtu.gruppe9.flaskehalsenpegerp.views;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.concurrent.ScheduledFuture;

import dk.dtu.gruppe9.flaskehalsenpegerp.R;


public class BottleView extends View {

    static final double BOTTLE_SCALE = 0.5;
    int width, height, posX, posY;
    float rotate, canRot;
    Bitmap bottleBitmap, scaledBottleBitmap;
    Paint painter;
    Context context;

    public BottleView(Context context, int posX, int posY){

        super(context);
        this.context = context;

        bottleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottle);

        setRelativeSize();

        scaledBottleBitmap = Bitmap.createScaledBitmap(bottleBitmap, width, height, false);

        this.posX = posX;
        this.posY = posY;
    }

    private void setRelativeSize(){

        width = (int) (bottleBitmap.getWidth() * BOTTLE_SCALE);
        height = (int) (bottleBitmap.getHeight() * BOTTLE_SCALE);
        System.out.println(width + " " + height);
    }

    public boolean intersects(float x, float y){
        return true;
        //return (x < posX + scaledBottleBitmap.getWidth()/2
          //      && x > posX - scaledBottleBitmap.getWidth()/2
            //    && y < posY + scaledBottleBitmap.getHeight()/2
              //  && y > posY - scaledBottleBitmap.getHeight()/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        canvas.drawBitmap(scaledBottleBitmap, posX - scaledBottleBitmap.getWidth()/2, posY - scaledBottleBitmap.getHeight()/2, painter);

        canvas.restore();

    }

    public float getCenterX(){
        return posX;
    }

    public float getCenterY(){
        return posY;
    }
}
