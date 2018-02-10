package uncc.com.hw3_groups25;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class resultDynamicLayout extends LinearLayout {
    LinearLayout linearLayout;
    TextView textView;

    public resultDynamicLayout(Context context) {
        super(context);
        inflateResults(context);
    }

    private void inflateResults(Context context){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.displayfinalresults, this);
        this.textView = (TextView) findViewById(R.id.textViewdisplayResults);
        this.linearLayout = (LinearLayout) findViewById(R.id.linearLayoutResults);
    }
}
