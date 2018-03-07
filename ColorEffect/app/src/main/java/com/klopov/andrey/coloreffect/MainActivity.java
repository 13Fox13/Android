package com.klopov.andrey.coloreffect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ColorSpec spec = new ColorSpec();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickColorEffect(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        TextView text2 = (TextView) findViewById(R.id.textView2);

        String color = String.valueOf(spinner.getSelectedItem());
        String result = spec.getEffect(color);
        text2.setText(result);
    }
}
