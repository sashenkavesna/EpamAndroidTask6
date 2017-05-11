package com.epam.androidlab.epamandroidtask6;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private int clickCount = 0;

    private int marginTop = 0;
    private int marginLeft = 0;

    private int marginHorizontal;
    private int marginVertical;

    private TextView text;
    private FrameLayout.LayoutParams params;
    private View.OnLongClickListener textListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            clickCount++;
            switch (clickCount) {
                case 1: {
                    marginLeft = marginHorizontal;
                    params.leftMargin = marginLeft;
                    break;
                }
                case 2: {
                    marginTop = marginVertical;
                    params.topMargin = marginTop;
                    break;
                }
                case 3: {
                    marginLeft = 0;
                    params.leftMargin = marginLeft;
                    break;
                }
                case 4: {
                    marginTop = 0;
                    params.topMargin = marginTop;
                    clickCount = 0;
                    break;
                }
            }
            text.setLayoutParams(params);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = (ImageView) findViewById(R.id.image);
        image.setOnLongClickListener(textListener);
        Picasso.with(this)
                .load("https://loyl.me/wp-content/uploads/2013/08/loyalty500x500.png")
                .into(image);

        text = (TextView) findViewById(R.id.text);

        params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);

        if (this.getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE) {
            marginHorizontal = pxFromDp(getResources().getDimension(R.dimen.text_horizontal_margin_land));
            marginVertical = pxFromDp(getResources().getDimension(R.dimen.text_vertical_margin_land));
        } else {
            marginHorizontal = pxFromDp(getResources().getDimension(R.dimen.text_horizontal_margin));
            marginVertical = pxFromDp(getResources().getDimension(R.dimen.text_vertical_margin));
        }
    }

    public int pxFromDp(float dp) {
        return (int) (dp * this.getResources().getDisplayMetrics().density);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        marginTop = savedInstanceState.getInt("marginTop");
        marginLeft = savedInstanceState.getInt("marginLeft");
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (marginTop != 0) {
                marginTop = pxFromDp(getResources().getDimension(R.dimen.text_vertical_margin_land));
            }
            if (marginLeft != 0) {
                marginLeft = pxFromDp(getResources().getDimension(R.dimen.text_horizontal_margin_land));
            }
        } else {
            if (marginTop != 0) {
                marginTop = pxFromDp(getResources().getDimension(R.dimen.text_vertical_margin));
            }
            if (marginLeft != 0) {
                marginLeft = pxFromDp(getResources().getDimension(R.dimen.text_horizontal_margin));
            }
        }
        params.leftMargin = marginLeft;
        params.topMargin = marginTop;
        text.setLayoutParams(params);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("marginTop", marginTop);
        outState.putInt("marginLeft", marginLeft);
    }
}
