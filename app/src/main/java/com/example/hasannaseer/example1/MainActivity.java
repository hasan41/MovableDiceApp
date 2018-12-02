package com.example.hasannaseer.example1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public static final Random RANDOM = new Random();
    private ViewGroup mainLayout;
    private ImageView image;
    private Button rollDices;
    private int xDelta;
    private int yDelta;

    int cpu  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (RelativeLayout) findViewById(R.id.main);
        image = (ImageView) findViewById(R.id.image);
        rollDices = (Button) findViewById(R.id.rollDices);
        image.setOnTouchListener(onTouchListener());

        rollDices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {


                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int value = randomDiceValue();
                        int res = getResources().getIdentifier("dice_" + value, "drawable", "com.example.hasannaseer.example1");

                        if (animation == anim1) {
                            image.setImageResource(res);

                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub
                    }
                };

                anim1.setAnimationListener(animationListener);

                image.startAnimation(anim1);
            }
        });
    }



    private OnTouchListener onTouchListener(){
        return new OnTouchListener(){
@SuppressLint("ClickableViewAccessibility")
@Override
public boolean onTouch(View view,MotionEvent event){
final int x=(int)event.getRawX();
final int y=(int)event.getRawY();
        switch(event.getAction()&MotionEvent.ACTION_MASK){
        case MotionEvent.ACTION_DOWN:
        RelativeLayout.LayoutParams lParams=(RelativeLayout.LayoutParams)
                view.getLayoutParams();
        xDelta=x-lParams.leftMargin;
        yDelta=y-lParams.topMargin;
        break;
        case MotionEvent.ACTION_UP:
        Toast.makeText(MainActivity.this,
        "Hey, Programmer!",Toast.LENGTH_SHORT)
        .show();
        break;
        case MotionEvent.ACTION_MOVE:
        RelativeLayout.LayoutParams layoutParams=(RelativeLayout.LayoutParams)view.getLayoutParams();
        layoutParams.leftMargin=x-xDelta;
        layoutParams.topMargin=y-yDelta;
        layoutParams.rightMargin=0;
        layoutParams.bottomMargin=0;
        view.setLayoutParams(layoutParams);
        break;
        }
        mainLayout.invalidate();
        return true;
        }
        };
        }


    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }
}

