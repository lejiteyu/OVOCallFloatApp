package ovo.Intent.fridayapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FloatView2  {

    Button btn1;
    View view;
    public View getView(final Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.float_view, null);
        btn1 = (Button)view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
