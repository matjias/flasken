package dk.dtu.gruppe9.flaskehalsenpegerp.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;

import dk.dtu.gruppe9.flaskehalsenpegerp.R;


public class BottleView extends View {

    static final double BOTTLE_SCALE = 0.3;
    int width, height, posX, posY, rotate;
    Bitmap bottleBitmap, scaledBottleBitmap;
    Paint painter;
    Context context;

    public BottleView(Context context, int displayWidth, int displayHeight, int posX, int posY){

        super(context);
        this.context = context;

        bottleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottle);

        setRelativeSize(displayWidth, displayHeight);

        scaledBottleBitmap = Bitmap.createScaledBitmap(bottleBitmap, width, height, false);

        this.posX = posX + displayWidth/2 - scaledBottleBitmap.getWidth()/2;
        this.posY = posY + displayHeight/2 - scaledBottleBitmap.getHeight()/2;
    }

    private void setRelativeSize(int displayWidth, int displayHeight){





        width = (int) (bottleBitmap.getWidth() * BOTTLE_SCALE);
        height = (int) (bottleBitmap.getHeight() * BOTTLE_SCALE);
        System.out.println(width + " " + height);
    }



    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        rotate += rotate;

        canvas.rotate(rotate, posX, posY);

        canvas.drawBitmap(scaledBottleBitmap, posX, posY, painter);

        canvas.restore();

    }
}
