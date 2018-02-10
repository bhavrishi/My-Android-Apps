package uncc.com.hw3_groups25;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class listItemUILayout extends LinearLayout {
    //LinearLayout linearLayout;
    EditText editText;
    ImageButton imageButton;
    public listItemUILayout(Context context) {
        super(context);
        inflateXML(context);
    }

    private void inflateXML(Context context){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.add_button, this, true);
        this.editText = (EditText) findViewById(R.id.editTextMainOther);
        this.imageButton = (ImageButton) findViewById(R.id.imageButtonMainOther);
        this.imageButton.setImageResource(R.drawable.add);

    }
}
