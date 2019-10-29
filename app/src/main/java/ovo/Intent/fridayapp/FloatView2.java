package ovo.Intent.fridayapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class FloatView2  {

    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    View view;
    EditText editText;
    ImageView closeImg;
    public View getView(final Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.float_view, null);
        btn1 = (Button)view.findViewById(R.id.btn1);
        final String keyword1 = "激戰";
        String k = btn1.getText()+"("+keyword1+")";
        btn1.setText(k);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,keyword1);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });
        btn2 = (Button)view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = editText.getText().toString();
                Toast.makeText(context,"the float btn1 is clicked.("+keyword+")",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,keyword);
                intent.putExtra(MainActivity.LinkType,"12");
                intent.putExtra(MainActivity.LinkValue,"5");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });
        btn3 = (Button)view.findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,"");
                intent.putExtra(MainActivity.LinkType,"12");
                intent.putExtra(MainActivity.LinkValue,"2");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });
        btn4 = (Button)view.findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,"");
                intent.putExtra(MainActivity.LinkType,"12");
                intent.putExtra(MainActivity.LinkValue,"4_3");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });
        btn5 = (Button)view.findViewById(R.id.btn5);
        final String keyword5 = "18歲的瞬間";
        final String Episode5 = "SP";
        String k5 = btn5.getText()+"("+keyword5+":"+Episode5+")";
        btn5.setText(k5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.Episode,Episode5);
                intent.putExtra(MainActivity.KeyWord,keyword5);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });

        btn6 = (Button)view.findViewById(R.id.btn6);
        final String keyword6 = "鬼滅之刃";
        final String Episode6 = "SP";
        String k6 = btn6.getText()+"("+keyword6+":"+Episode6+")";
        btn6.setText(k6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,keyword6);
                intent.putExtra(MainActivity.Episode,Episode6);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });

        btn7 = (Button)view.findViewById(R.id.btn7);
        final String keyword7 = "鬼滅之刃";
        final String Episode7 = "7";
        String k7 = btn7.getText()+"("+keyword6+":"+Episode7+")";
        btn7.setText(k7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,keyword7);
                intent.putExtra(MainActivity.Episode,Episode7);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });

        btn8 = (Button)view.findViewById(R.id.btn8);
        final String keyword8 = "鬼滅之刃";
        final String Episode8 = "";
        String k8 = btn8.getText()+"("+keyword6+":"+Episode8+")";
        btn8.setText(k8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,keyword8);
                intent.putExtra(MainActivity.Episode,Episode8);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });

        btn9 = (Button)view.findViewById(R.id.btn9);
        final String keyword9 = "玩命關頭";
        final String Episode9 = "";
        String k9 = btn9.getText()+"("+keyword9+":"+Episode9+")";
        btn9.setText(k9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"the float btn1 is clicked.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(MainActivity.callFriDayAction);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(MainActivity.KeyWord,keyword9);
                intent.putExtra(MainActivity.Episode,Episode9);
                intent.putExtra(MainActivity.LinkType,"");
                intent.putExtra(MainActivity.LinkValue,"");
//                startActivity(intent);
                context.sendBroadcast(intent);
            }
        });


        editText = (EditText) view.findViewById(R.id.edit_query);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        closeImg = (ImageView)view.findViewById(R.id.closeImg);
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.stopService(new Intent(context, FloatService.class));
            }
        });
        return view;
    }
}
