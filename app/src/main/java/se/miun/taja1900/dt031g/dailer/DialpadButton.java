package se.miun.taja1900.dt031g.dailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class DialpadButton extends ConstraintLayout{
    private TextView title1;
    private TextView message1;
    SoundPlayer soundPlayer;
   private static final String UNDEFINED = "";
    private String text = UNDEFINED; // color not set
    /**
     * Interface definition for a callback to be invoked when the color of a RandomColorButton is
     * changed. The color change happens when the button is clicked.
     */
    public interface OnClickedListener {
        void onClick(DialpadButton dialpadButton);
    }

    /**
     * Listener used to dispatch color change events to.
     */
    private DialpadButton.OnClickedListener listener;

    /**
     * Register a callback to be invoked when the text changes (when this button is clicked).
     *
     * @param listener The callback that will run when the bottom clicked
     * @see #setText(String)
     */
    public void setTextChangeListener(OnClickedListener listener) {
        this.listener = listener;
    }

    public DialpadButton(Context context) {
        super(context);
        init(context,null);
    }

    public DialpadButton(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public DialpadButton(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        inflate(getContext(), R.layout.dial_pad_button, this);
         title1 = findViewById(R.id.numb_view);
         message1 = findViewById(R.id.text_View);
        soundPlayer = SoundPlayer.getInstance(this.getContext());

        setOnClickListener(view -> {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.blink_anim);
            startAnimation(animation);
            soundPlayer.playSound(this);

           // setText(title1.getText().toString()); //title1.getText().toString()
           // setTextChangeListener
        });

        TypedArray customAttributes = getContext().getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.DialpadButton,
                0, 0);

        try {
            setTitle(customAttributes.getString(R.styleable.DialpadButton_title));
            setMessage(customAttributes.getString(R.styleable.DialpadButton_message));
        } finally {
            customAttributes.recycle();
        }
    }

    private void setText(String txt) {
       this.text = txt;
        if (listener != null) {
            listener.onClick(this);
        }
    }
/**/
    @SuppressLint("SetTextI18n")
    public void setTitle(String title) {
        title1.setText(checkTitle(title) );
    }

    public void setMessage(String message) {
        message1.setText(checkMessage(message));
    }


    public String checkTitle(String title){
        String numbs = ".*[1234567890✱#].*";
        if (title.matches(numbs))
            return "" + title.charAt(0);
        else
            return " ";
    }

    public String checkMessage(String message){
        return message;
    }

    public int getTitle(){
        if (title1.getText().toString().equals("✱"))
            return 10;
        else if (title1.getText().toString().equals("#"))
            return 11;
        else
            return Integer.parseInt(title1.getText().toString());
    }


    public void onDestroy() {
        soundPlayer.destroy();
    }

    public String getTextNumber(){
        return title1.getText().toString();
    }
}
