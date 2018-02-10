package uncc.com.inclass02;

/*Assignment# InClassAssignment02
FileName InClass02
FullName Group23*/

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_main_relative);
        final TextView textViewLength1 = (TextView) findViewById(R.id.textView_length1);
        final TextView textViewLength2 = (TextView) findViewById(R.id.textView_length2);

        final EditText editTextLength1 = (EditText) findViewById(R.id.editText_length1);
        final EditText editTextLength2 = (EditText) findViewById(R.id.editText_length2);

        ImageView imageViewTriangle = (ImageView) findViewById(R.id.imageView_triangle);
        ImageView imageViewSquare = (ImageView) findViewById(R.id.imageView_square);
        ImageView imageViewCircle = (ImageView) findViewById(R.id.imageView_circle);

        final TextView textViewShape = (TextView) findViewById(R.id.textView_display_shape);
        final TextView textViewResult = (TextView) findViewById(R.id.textView_result);

        Button buttonCalculate = (Button) findViewById(R.id.button_calculate);
        Button buttonClear = (Button) findViewById(R.id.button_clear);
        //int selectedButtonId = id;

        int[] imageIds = {R.id.imageView_triangle, R.id.imageView_square, R.id.imageView_circle};
        for (int imageId : imageIds
                ) {
            findViewById(imageId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //selectedButtonId =v.getId();
                    if (v.getId() == R.id.imageView_triangle) {
                        textViewShape.setText(R.string.triangle);
                        textViewLength2.setVisibility(View.VISIBLE);
                        editTextLength2.setVisibility(View.VISIBLE);
                    }
                    if (v.getId() == R.id.imageView_square) {
                        textViewShape.setText(R.string.square);
                        textViewLength2.setVisibility(View.INVISIBLE);
                        editTextLength2.setVisibility(View.INVISIBLE);
                    }
                    if (v.getId() == R.id.imageView_circle) {
                        textViewShape.setText(R.string.circle);
                        textViewLength2.setVisibility(View.INVISIBLE);
                        editTextLength2.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        try {
            buttonCalculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double inputLength1 = Double.parseDouble(editTextLength1.getText().toString());
                    double inputLength2 = Double.parseDouble(editTextLength2.getText().toString());
                    double output = 0;
                    if (textViewShape.getText().toString().equals("Triangle")) {
                        output = (inputLength1 * inputLength2) / 2;
                        //Log.d("demoinner", Double.toString(output));
                    }
                    if (textViewShape.getText().toString().equals("Square")) {
                        output = (inputLength1 * inputLength1);
                    }
                    if (textViewShape.getText().toString().equals("Circle")) {
                        output = (inputLength1 * inputLength1) * 3.1416;
                    }
                    //Log.d("demo", Double.toString(output));
                    //Log.d("demo1", textViewShape.getText().toString());
                    //Log.d("demo2", Double.toString(inputLength2));
                    textViewResult.setText(Double.toString(output));
                }

            });
        } catch (Exception ex) {
            Toast.makeText(this, "Please enter valid input",
                    Toast.LENGTH_SHORT).show();
        }


        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewShape.setText(R.string.defaul_select_shape);
                textViewLength2.setVisibility(View.VISIBLE);
                editTextLength2.setVisibility(View.VISIBLE);
                textViewResult.setText("");
                editTextLength1.setText("");
                editTextLength2.setText("");
            }
        });


    }
}
