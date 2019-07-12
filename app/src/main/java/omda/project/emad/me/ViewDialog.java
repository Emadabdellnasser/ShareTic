package omda.project.emad.me;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by DELL on 9/18/2018.
 */

public class ViewDialog extends Dialog implements
        android.view.View.OnClickListener
{
    public Activity c;
    public Dialog d;
    public Button yes, no;

    public ViewDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.custom_dialogbox_otp);

        setCancelable(false);
      getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView text = (TextView) findViewById(R.id.txt_file_path);
        text.setText("مسح هذه التزكرة");
        LinearLayout LAY = (LinearLayout) findViewById(R.id.layOK);
        Button  dialogBtn_okay = (Button)findViewById(R.id.btn_okay);

        Button dialogBtn_cancel = (Button) findViewById(R.id.btn_cancel);

        //   Toast.makeText(activity,"Okay"+res ,Toast.LENGTH_SHORT).show();

        dialogBtn_okay.setOnClickListener(this);
        dialogBtn_cancel.setOnClickListener(this);


    }

 //   private ArrayList<String> password  = new  ArrayList<String>();

//    String res="om";
//      boolean stat=true;
//    Button dialogBtn_okay;
//    public void showDialog(final Activity activity, String msg){
//        final Dialog dialog = new Dialog(activity);
//        password.add("");
//        stat=false;
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.custom_dialogbox_otp);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//        TextView text = (TextView) dialog.findViewById(R.id.txt_file_path);
//        text.setText(msg);
//        LinearLayout LAY = (LinearLayout) dialog.findViewById(R.id.layOK);
//
//        dialogBtn_okay = (Button) dialog.findViewById(R.id.btn_okay);
//
//        dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stat=true;
//                EditText passD = (EditText) dialog.findViewById(R.id.passDialoge);
//                password.add(passD.getText().toString().trim());
//              //  Toast.makeText(activity,"Okay"+res ,Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//
//            }
//        });
//        Button dialogBtn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
//        dialogBtn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                    Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
//                 password.add("");
//                dialog.dismiss();
//
//            }
//        });
//     //   Toast.makeText(activity,"Okay"+res ,Toast.LENGTH_SHORT).show();
//
//        dialog.show();
//
//    }
//
//    public String passfun()
//    {
//        String r="";
//        for(int i=0;i<password.size();i++)
//        {
//           r= password.get(i);
//        }
//        return r;
//    }
//    public boolean chekpress()
//    {
//        boolean in=stat;
//        return in;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_okay:
                c.finish();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
    }

//    public String getpasstext(Activity activity)
//    {
//        final Dialog dialog1 = new Dialog(activity);
//
//        EditText passD = (EditText) dialog1.findViewById(R.id.passDialoge);
//        String txt=passD.getText().toString().trim();
//
//        return txt;
//    }
//     public String click( final Activity activity)
//    {
//        final Dialog dialog1 = new Dialog(activity);
//
//        final String[] r = new String[1];
//        Button dialogBtn_okay = (Button) dialog1.findViewById(R.id.btn_okay);
//        dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                    Toast.makeText(getApplicationContext(),"Okay" ,Toast.LENGTH_SHORT).show();
//               r[0] = getpasstext(activity);
//                dialog1.dismiss();
//            }
//        });
//        Button dialogBtn_cancel = (Button) dialog1.findViewById(R.id.btn_cancel);
//        dialogBtn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                    Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
//
//                r[0]="";
//                dialog1.dismiss();
//            }
//        });
//        return  r[0];
//      }

