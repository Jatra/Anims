package uk.co.jatra.animbutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class ReadyableButton extends ImageButton {

    private static final int[] READYING_STATE_SET = {
            R.attr.state_readying
    };

    private static final int[] OPERATING_STATE_SET = {
            R.attr.state_operating
    };

    private static final int[] COMPLETING_STATE_SET = {
            R.attr.state_completing
    };

    private static final int[] READY_STATE_SET = {
            R.attr.state_ready
    };
    private boolean readying;
    private boolean operating;
    private boolean completing;
    private boolean ready;

    public ReadyableButton(Context context) {
        super(context);
    }

    public ReadyableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReadyableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ReadyableButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 4);
        if (isReadying()) {
            mergeDrawableStates(drawableState, READYING_STATE_SET);
        }
        if (isOperating()) {
            mergeDrawableStates(drawableState, OPERATING_STATE_SET);
        }
        if (isCompleting()) {
            mergeDrawableStates(drawableState, COMPLETING_STATE_SET);
        }
        if (isReady()) {
            mergeDrawableStates(drawableState, READY_STATE_SET);
        }
        return drawableState;
    }

    public boolean isReadying() {
        return readying;
    }

    public void setReadying(boolean readying) {
        this.readying = readying;
        refreshDrawableState();
    }

    public boolean isOperating() {
        return operating;
    }

    public void setOperating(boolean operating) {
        this.operating = operating;
        refreshDrawableState();
    }

    public boolean isCompleting() {
        return completing;
    }

    public void setCompleting(boolean completing) {
        this.completing = completing;
        refreshDrawableState();
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
        refreshDrawableState();
    }
}
