package dk.dtu.gruppe9.flaskehalsenpegerp.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import dk.dtu.gruppe9.flaskehalsenpegerp.R;

public class Splash_TitleView extends View {
    static final double TITLE_SCALE = 0.8;
    Bitmap splashTitle, scaledSplashTitle;
    Paint painter = new Paint();
    int width, height, posX, posY;

    public Splash_TitleView(Context context, int posX, int posY) {
        super(context);

        splashTitle = BitmapFactory.decodeResource(getResources(), R.drawable.splash_title);

        width = (int) (splashTitle.getWidth() * TITLE_SCALE);
        height = (int) (splashTitle.getHeight() * TITLE_SCALE);

        scaledSplashTitle = Bitmap.createScaledBitmap(splashTitle, width, height, false);

        this.posX = posX;
        this.posY = posY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.drawBitmap(scaledSplashTitle,
                posX - scaledSplashTitle.getWidth() / 2,
                posY - scaledSplashTitle.getHeight() / 2,
                painter);
        canvas.restore();
    }
}
