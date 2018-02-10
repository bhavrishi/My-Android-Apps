package com.example.rishi.inclass02;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutnew);
        final EditText line1 = (EditText) findViewById(R.id.line1);
        final EditText line2 = (EditText) findViewById(R.id.line2);
        final TextView shape = (TextView) findViewById(R.id.shapeName);
        final TextView result = (TextView) findViewById(R.id.resulttext);

        ImageView selectedTriangle = (ImageView) findViewById(R.id.triangle);
        ImageView selectedCircle = (ImageView) findViewById(R.id.circle);
        ImageView selectedSquare = (ImageView) findViewById(R.id.square);
        Button calButton= (Button) findViewById(R.id.button);
        int[] imageIds = {R.id.triangle, R.id.square, R.id.circle};
        for (int img : imageIds) {
            findViewById(img).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view.getId() == R.id.triangle) {
                        shape.setText("Triangle");

                    }
                    if (view.getId() == R.id.circle) {
                        shape.setText("Circle");line2.setVisibility(view.INVISIBLE);
                    }
                    if (view.getId() == R.id.square) {
                        shape.setText("Square");line2.setVisibility(view.INVISIBLE);
                    }

                }
            });
        }

        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double d1=Double.parseDouble(line1.getText().toString());
                   Double d2=Double.parseDouble(line2.getText().toString());
if(shape.getText().toString().equals("Square"))
{
    result.setText(Double.toString(d1*d2));
}
                if(shape.getText().toString().equals("Circle"))
                {
                    result.setText(Double.toString(d1*d2*3.1416));
                }
                if(shape.getText().toString().equals("Triangle"))
                {
                    result.setText(Double.toString(d1*d2*0.5));
                }
            }
        });




    }


}