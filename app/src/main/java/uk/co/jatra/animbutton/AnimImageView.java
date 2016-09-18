package uk.co.jatra.animbutton;


import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;

public class AnimImageView extends ImageView {

    private static final int MAX_LEVEL = 10000;
    private static final boolean USE_OBJECT_ANIMATOR = false;
    private boolean inDrawing;
    private AlphaAnimation anim;
    private ObjectAnimator animator;

    public AnimImageView(Context context) {
        this(context, null, 0, 0);
    }

    public AnimImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public AnimImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AnimImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    private void setup() {
        if (USE_OBJECT_ANIMATOR) {
            setupObjectAnimation();
        } else {
            setupAlphaAnimation();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (anim != null) {
            Drawable d = getDrawable();

            final long time = getDrawingTime();
            Transformation transformation = new Transformation();
            anim.getTransformation(time, transformation);
            final float scale = transformation.getAlpha();

            try {
                inDrawing = true;
                d.setLevel((int) (scale * MAX_LEVEL));
            } finally {
                inDrawing = false;
            }
            postInvalidate();
        }

    }

    private void setupAlphaAnimation() {
        anim = new AlphaAnimation(0f, 1f);
        anim.setRepeatMode(Animation.INFINITE);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(3000);
        anim.setInterpolator(new LinearInterpolator());
        anim.setStartTime(Animation.START_ON_FIRST_FRAME);
    }

    //This has the animator running all the time. Is that necessary?
    //The other method uses the time for the interpolation.
    private void setupObjectAnimation() {
        Drawable d = getDrawable();
        animator = ObjectAnimator.ofInt(d, "level", 0, 10000);
        animator.setDuration(3000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (animator != null) {
            animator.start();
        } else if (anim != null) {
            anim.start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null) {
            animator.cancel();
        } else if (anim != null) {
            anim.cancel();
        }
    }
}
