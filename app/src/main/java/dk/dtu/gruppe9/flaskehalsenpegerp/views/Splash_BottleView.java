package dk.dtu.gruppe9.flaskehalsenpegerp.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import dk.dtu.gruppe9.flaskehalsenpegerp.R;

public class Splash_BottleView extends View {
    static final double BOTTLE_SCALE = 0.6;
    Bitmap splashBottle, scaledSplashBottle;
    Paint painter = new Paint();
    int width, height, posX, posY;

    public Splash_BottleView(Context context, int posX, int posY) {
        super(context);

        splashBottle = BitmapFactory.decodeResource(getResources(), R.drawable.splash_bottle);

        width = (int) (splashBottle.getWidth() * BOTTLE_SCALE);
        height = (int) (splashBottle.getHeight() * BOTTLE_SCALE);

        scaledSplashBottle = Bitmap.createScaledBitmap(splashBottle, width, height, false);

        this.posX = posX;
        this.posY = posY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.drawBitmap(scaledSplashBottle,
                posX - scaledSplashBottle.getWidth() / 2,
                posY - scaledSplashBottle.getHeight() / 2,
                painter);
        canvas.restore();
    }
}
