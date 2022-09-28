package se.miun.taja1900.dt031g.dailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import org.apache.commons.lang3.StringUtils;

public class Dialpad extends ConstraintLayout {
    DialpadButton dpb1,dpb2,dpb3,dpb4,dpb5,dpb6,dpb7,dpb8,dpb9,dpb0,dpb10,dpb11, btNumber;
    ImageButton del;
    TextView textView;
    private String textNumber; //Display number of clicks

    public Dialpad(Context context) {
        super(context);
        init(context,null);
    }


    public Dialpad(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context,attributeSet);
    }

    public Dialpad(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);
        init(context, attributeSet);

    }

    @SuppressLint("SetTextI18n")
    private void init(Context context, AttributeSet attribute) {
        inflate(getContext(), R.layout.dialpad, this);
        textView = findViewById(R.id.editTextPhone);
        del = findViewById(R.id.del_button);
        dpb0 = findViewById(R.id.dialpad_button_0);
        dpb1 =  findViewById(R.id.dialpad_button_1);
        dpb2 =  findViewById(R.id.dialpad_button_2);
        dpb3 =  findViewById(R.id.dialpad_button_3);
        dpb4 =  findViewById(R.id.dialpad_button_4);
        dpb5 =  findViewById(R.id.dialpad_button_5);
        dpb6 =  findViewById(R.id.dialpad_button_6);
        dpb7 =  findViewById(R.id.dialpad_button_7);
        dpb8 =  findViewById(R.id.dialpad_button_8);
        dpb9 =  findViewById(R.id.dialpad_button_9);
        dpb10 =  findViewById(R.id.dialpad_button_star);
        dpb11 =  findViewById(R.id.dialpad_button_pound);

        del.setOnClickListener(v -> {
            animation(v);
            textView.setText(StringUtils.substring((String) textView.getText(), 0, -1));
        });
        del.setOnLongClickListener(v -> {
            textView.setText("");
            animation(v);
            return true;
        });

       // textView.setText();
      //  OnClickListener
      //  btNumber.setTextChangeListener(v -> textView.setText(text() + ""+ btNumber.getTextNumber()));

        dpb0.setOnClickListener(view -> {
            textView.setText(text() + ""+ dpb0.getTitle());
            voice(dpb0, view);
        });
        dpb1.setOnClickListener(view -> {
            textView.setText(text()+""+ dpb1.getTitle());
            voice(dpb1, view);
        });
        dpb2.setOnClickListener(view -> {
            textView.setText(text()+""+ dpb2.getTitle());
            voice(dpb2, view);
        });
        dpb3.setOnClickListener(view -> {
            textView.setText(text()+""+ dpb3.getTitle());
            voice(dpb3, view);
        });

       dpb4.setOnClickListener(view -> {
            textView.setText(text()+""+ dpb4.getTitle());
           voice(dpb4, view);
        });
        dpb5.setOnClickListener(view -> {
            textView.setText(text()+""+ dpb5.getTitle());
            voice(dpb5, view);
        });
        dpb6.setOnClickListener(view -> {
            textView.setText(text()+""+ dpb6.getTitle());
            voice(dpb6, view);
        });
        dpb7.setOnClickListener(view -> {
            textView.setText(text()+""+ dpb7.getTitle());
            voice(dpb7, view);
        });
         dpb8.setOnClickListener(view -> {
            textView.setText(text()+""+ dpb8.getTitle());
             voice(dpb8, view);
        });
        dpb9.setOnClickListener(view -> {
            textView.setText(text()+""+ dpb9.getTitle());
            voice(dpb9, view);
        });
        dpb10.setOnClickListener(view -> {
            textView.setText(text()+"✱");
            voice(dpb10, view);
        });
        dpb11.setOnClickListener(view -> {
            textView.setText(text()+"#");
            voice(dpb11, view);
        });  /* */
    }

    public void getButton(DialpadButton dial){
        switch (dial.getTitle()){
            case 0:
                btNumber = findViewById(R.id.dialpad_button_0);
                break;
            case 1:
                btNumber = findViewById(R.id.dialpad_button_1);
                break;
            case 2:
                btNumber = findViewById(R.id.dialpad_button_2);
                break;
            case 3:
                btNumber = findViewById(R.id.dialpad_button_3);
                break;
            case 4:
                btNumber = findViewById(R.id.dialpad_button_4);
                break;
            case 5:
                btNumber = findViewById(R.id.dialpad_button_5);
                break;
            case 6:
                btNumber = findViewById(R.id.dialpad_button_6);
                break;
            case 7:
                btNumber = findViewById(R.id.dialpad_button_7);
                break;
            case 8:
                btNumber = findViewById(R.id.dialpad_button_8);
                break;
            case 9:
                btNumber = findViewById(R.id.dialpad_button_9);
                break;
            case 10:
                btNumber = findViewById(R.id.dialpad_button_star);
                break;
            case 11:
                btNumber = findViewById(R.id.dialpad_button_pound);
                break;
        }
    }

    public void animation(View v){
        Animation animation = AnimationUtils.loadAnimation(v.getContext(),R.anim.blink_anim);
        v.startAnimation(animation);
    }

    public void voice(DialpadButton dial, View v){
        SoundPlayer.getInstance(getContext()).playSound(dial);
        Animation animation = AnimationUtils.loadAnimation(v.getContext(),R.anim.blink_anim);
        v.startAnimation(animation);
    }

    public String text(){
        return (String) textView.getText();
    }
}