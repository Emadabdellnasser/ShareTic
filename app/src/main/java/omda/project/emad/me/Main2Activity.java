package omda.project.emad.me;



import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    private  ArrayList<String> numbers  = new  ArrayList<String>();
    public static ArrayList<String> passwords  = new  ArrayList<String>();
    private  ArrayList<String> froms  = new  ArrayList<String>();
    private  ArrayList<String> tos  = new  ArrayList<String>();
    private  ArrayList<String> phons  = new  ArrayList<String>();
    private  ArrayList<String> prices  = new  ArrayList<String>();
    private  ArrayList<String> degrees  = new  ArrayList<String>();
    private  ArrayList<String> tims  = new  ArrayList<String>();
    private  ArrayList<String> dats  = new  ArrayList<String>();

    static String valuetel="";
    static String valuepass="",valuefrom="",valueto="",valuephone="",valueprice="",valuedegree,valuetime="",valuetodate="";
   boolean mRunning=true;
    Button btAdd;
    DatabaseReference dref;
    private RecyclerView rec;
    TextView emptytxt;
    ProgressDialog pr;
    private AdView mAdView;

      int total;
        static int t;

    CountDownTimer cdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // adding ads
        MobileAds.initialize(this, "ca-app-pub-2003869293764152/7859342093");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);

        setTitle("التذاكر المتوفرة");
         emptytxt = (TextView) findViewById(R.id.emptyview);
          pr = new ProgressDialog(this);
         pr.setTitle("جاري التحميل...... ");
         pr.getWindow().setGravity(Gravity.CENTER);
        dref = FirebaseDatabase.getInstance().getReference().child("Blog");

        rec = (RecyclerView) findViewById(R.id.mainData);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setItemViewCacheSize(9);
        rec.setHasFixedSize(true);
        rec.addOnItemTouchListener(
                new RecyclerItemClickListener(Main2Activity.this, rec, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        Button buy = (Button) view.findViewById(R.id.call);
                        final Button Edit = (Button) view.findViewById(R.id.edit);
                          Edit.setOnTouchListener(new View.OnTouchListener() {
                      @Override
                       public boolean onTouch(View v, MotionEvent event) {

//                          ViewDialog cdd=new ViewDialog(Main2Activity.this);
//                          cdd.show();
                          final Dialog dialog = new Dialog(Main2Activity.this);
                          dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                          dialog.setCancelable(false);
                          dialog.setContentView(R.layout.custom_dialogbox_otp);
                          dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                          TextView text = (TextView) dialog.findViewById(R.id.txt_file_path);
                          text.setText("مسح هذه التزكرة؟؟");
                          LinearLayout LAY = (LinearLayout) dialog.findViewById(R.id.layOK);

                          Button  dialogBtn_okay = (Button) dialog.findViewById(R.id.btn_okay);

                          dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                  EditText passD = (EditText) dialog.findViewById(R.id.passDialoge);
                                  String res=passD.getText().toString().trim();
                                  if (res.equalsIgnoreCase(passwords.get(position)))
                                  {
                                      delete(res);
                                      Toast.makeText(Main2Activity.this,"deleted  " ,Toast.LENGTH_SHORT).show();
                                      finish();
                                      Intent intent = new Intent(Main2Activity.this,Main2Activity.class);
                                      startActivity(intent);

                                  }
                                  else
                                  {
                                      if(t==0)
                                      {
                                          Toast.makeText(Main2Activity.this,"الرقم السري غلط" ,Toast.LENGTH_SHORT).show();
                                          t++;
                                          finish();
                                          Intent intent = new Intent(Main2Activity.this,Main2Activity.class);
                                          startActivity(intent);

                                      }
                                      else
                                      {
                                          Toast.makeText(Main2Activity.this,"لا انت باين عليك مش صاحب التزكرة" ,Toast.LENGTH_SHORT).show();
                                          t=0;
                                          finish();
                                          Intent intent = new Intent(Main2Activity.this,Main2Activity.class);
                                          startActivity(intent);


                                      }


                                  }
                                  dialog.dismiss();

                              }
                          });
                          Button dialogBtn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
                          dialogBtn_cancel.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                                  dialog.dismiss();

                          Intent intent = new Intent(Main2Activity.this,Main2Activity.class);
                                  startActivity(intent);
                                 // Toast.makeText(Main2Activity.this,passwords.get(position), Toast.LENGTH_LONG).show();
//                          intent.putExtra("numbers",numbers.get(position));
//                          intent.putExtra("passwords",passwords.get(position));
//                          intent.putExtra("froms",froms.get(position));
//                          intent.putExtra("tos",tos.get(position));
//                          intent.putExtra("prices",prices.get(position));
//                          intent.putExtra("degrees",degrees.get(position));
//                          intent.putExtra("tims",tims.get(position));
//                          intent.putExtra("dats",dats.get(position));
//                          startActivity(intent);

                              }
                          });
                          //   Toast.makeText(activity,"Okay"+res ,Toast.LENGTH_SHORT).show();
                          dialog.show();









//                          Intent intent = new Intent(Main2Activity.this,Edit.class);
//                          intent.putExtra("numbers",numbers.get(position));
//                          intent.putExtra("passwords",passwords.get(position));
//                          intent.putExtra("froms",froms.get(position));
//                          intent.putExtra("tos",tos.get(position));
//                          intent.putExtra("prices",prices.get(position));
//                          intent.putExtra("degrees",degrees.get(position));
//                          intent.putExtra("tims",tims.get(position));
//                          intent.putExtra("dats",dats.get(position));
//                          startActivity(intent);

                         return true;
                            }
                        });

//                        buy.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(Intent.ACTION_DIAL);
//                                intent.setData(Uri.parse("tel:" + numbers.get(position)));
//                                startActivity(intent);
//                            }
//                        });
                        buy.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
//                                Intent in = new Intent(NewsAcc.this, Buy.class);
//                                startActivity(in);
//                                return true;
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" +numbers.get(position)));
                                startActivity(intent);
                                return false;
                            }
                        });



                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, Details.class));

            }
        });


    }
    public void myThread() {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    while (mRunning) {
                        Thread.sleep(20L);//10s wait
                        Main2Activity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                //DISMISS PROGRESS BAR HERE
                                pr.dismiss();

                                mRunning = false;
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
            }
        };
        th.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

            final SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
            search.setQueryHint("ادخل محطة الوصول ....");


            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                   Query Q = dref.orderByChild("toch").startAt(newText).endAt(newText+"\uf8ff");

                    // Query Q = dref.orderByChild("nameCh").startAt("[a-zA-Z0-9]*").endAt(newText);

                    numbers.clear();
                    passwords.clear();
                    froms.clear();
                    tos.clear();
                    prices.clear();
                    degrees.clear();
                    tims.clear();
                    dats.clear();
                    FirebaseRecyclerAdapter<Object_Class, Main2Activity.recycleClass> fireRec = new FirebaseRecyclerAdapter<Object_Class, Main2Activity.recycleClass>(Object_Class.class, R.layout.list_item, Main2Activity.recycleClass.class, Q)

                    {

                        @Override
                        protected void populateViewHolder(Main2Activity.recycleClass viewHolder, Object_Class model, int position) {

                            viewHolder.setFrom(model.getfromCh());
                            viewHolder.setPrice(model.getPriceCh());
                            viewHolder.setTo(model.gettoCh());
                            viewHolder.setDegree(model.getdegeeCh());
                            viewHolder.setdate(model.getdateCh());
                            viewHolder.setTime(model.gettimeCh());
                            viewHolder.setTxtedit("مسح");
                            viewHolder.setPhone(model.getphoneCh());
                            viewHolder.setpassword(model.getPassCh());
                            model.getfromCh();
                            pr.dismiss();
                            //    myThread();
                            passwords.add(model.getPassCh());
                            froms.add(model.getfromCh());
                            tos.add(model.gettoCh());
                            prices.add(model.getPriceCh());
                            degrees.add(model.getdegeeCh());
                            numbers.add(model.getphoneCh());
                            tims.add(model.gettimeCh());
                            dats.add(model.getdateCh());

                            Log.d("passs", passwords.toString());
                        }

                    };

                    rec.setAdapter(fireRec);


                    return true;
                }
            });
        }
        return true;

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        loaddata();


    }

    public void loaddata()
    {
        numbers.clear();
        passwords.clear();
        froms.clear();
        tos.clear();
        prices.clear();
        degrees.clear();
        tims.clear();
        dats.clear();

        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //checking network connection
        if (networkInfo != null && networkInfo.isConnected()) {
            //loading data

            pr.show();

            FirebaseRecyclerAdapter<Object_Class, Main2Activity.recycleClass> fireRec = new FirebaseRecyclerAdapter<Object_Class, Main2Activity.recycleClass>(Object_Class.class, R.layout.list_item, Main2Activity.recycleClass.class, dref)

            {

                @Override
                protected void populateViewHolder(Main2Activity.recycleClass viewHolder,  final Object_Class model, int position) {

                    viewHolder.setFrom(model.getfromCh());
                    viewHolder.setPrice(model.getPriceCh());
                    viewHolder.setTo(model.gettoCh());
                    viewHolder.setDegree(model.getdegeeCh());
                    viewHolder.setdate(model.getdateCh());
                    viewHolder.setTime(model.gettimeCh());
                    viewHolder.setTxtedit("مسح");
                    viewHolder.setPhone(model.getphoneCh());
                    viewHolder.setpassword(model.getPassCh());
                    pr.dismiss();
                    passwords.add(model.getPassCh());
                    froms.add(model.getfromCh());
                    tos.add(model.gettoCh());
                    prices.add(model.getPriceCh());
                    degrees.add(model.getdegeeCh());
                    numbers.add(model.getphoneCh());
                    tims.add(model.gettimeCh());
                    dats.add(model.getdateCh());

                  //  Toast.makeText(Main2Activity.this,passwords.toString(), Toast.LENGTH_LONG).show();

                 //  Log.d("passs", passwords.toString());

                }
            };

            rec.setAdapter(fireRec);

        } else {

            emptytxt.setText("check your network connection");

        }

    }

    public void delete(String valp) {


        dref = FirebaseDatabase.getInstance().getReference();
        dref.child("Blog").orderByChild("passch").equalTo(valp).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dref.child("Blog").child(dataSnapshot.getKey()).setValue(null);
                //also work
                //dataSnapshot.getRef().setValue(null);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onClick(View v) {
        if (v == btAdd) {

            // Intent in=new Intent(MainActivity.this,Details.class);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        return super.onOptionsItemSelected(item);
    }


    public static class recycleClass extends RecyclerView.ViewHolder {
        View mv;
        Context con;
        String var,varp;

        public recycleClass(View itemView) {
            super(itemView);
            mv = itemView;


        }

        String txt = "تعديل ";
        public void setTxtedit( String ed) {

            Button txtedit = (Button) mv.findViewById(R.id.edit);
            LinearLayout lay= (LinearLayout) mv.findViewById(R.id.edit_lay);
            txtedit.setText(ed);
          //  txtedit.setRotation(-45);
            lay.setRotation(-45);

        }

        //emptytxt.setRotation(90);
        public void setFrom(String From) {
            TextView txtFrom = (TextView) mv.findViewById(R.id.from);
            txtFrom.setText(From);

        }
        public void setPrice(String Price) {
            TextView txtprice = (TextView) mv.findViewById(R.id.price);
            txtprice.setText(Price+" جنية ");


        }
        public void setTo(String To) {
            TextView txtto = (TextView) mv.findViewById(R.id.to);
            txtto.setText(To);
        }
        public void setdate(String Date ) {
            TextView txtdate = (TextView) mv.findViewById(R.id.date);
            txtdate.setText(Date);
        }
        public void setTime(String Time) {
            TextView txtTime = (TextView) mv.findViewById(R.id.time);
            txtTime.setText(Time);

        }
        public void setDegree(String Degree) {
            TextView txtDegree = (TextView) mv.findViewById(R.id.degree);
            txtDegree.setText(Degree);
        }

        public  void setPhone( final String Phone) {
//          TextView txtphone = (TextView) mv.findViewById(R.id.call);
//            txtphone.setText(Phone);
            var=Phone;

        }
        public  void setpassword( final String pass) {
            varp=pass;


        }


    }

}
