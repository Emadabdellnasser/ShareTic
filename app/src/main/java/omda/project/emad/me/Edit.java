package omda.project.emad.me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Edit extends AppCompatActivity implements View.OnClickListener {
    EditText editTextfrom;
    EditText editTextto;
    EditText editTextprice;
    EditText editTextdegree;
    LinearLayout layouttime;
    LinearLayout layoutdate, EDIT_BTN;
    EditText editTextphone;
    TextView date, time;

    EditText edittextpassword;
    //
    DatabaseReference dref;
    private StorageReference stref;
    static String valuetel = "";
    static String valuepass = "", valuefrom = "", valueto = "", valuephone = "", valueprice = "", valuedegree, valuetime = "", valuetodate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setTitle("بيع تذكرة");
        stref = FirebaseStorage.getInstance().getReference();
       // dref = FirebaseDatabase.getInstance().getReference().child("Blog");
        editTextfrom = (EditText) findViewById(R.id.fromText_Edit);
        editTextprice = (EditText) findViewById(R.id.priceText_Edit);
        editTextto = (EditText) findViewById(R.id.toText_Edit);
        editTextdegree = (EditText) findViewById(R.id.degreeText_Edit);
        edittextpassword = (EditText) findViewById(R.id.pass_Edit);
        layouttime = (LinearLayout) findViewById(R.id.timeLayout_Edit);
        layoutdate = (LinearLayout) findViewById(R.id.dateLayout_Edit);
        editTextphone = (EditText) findViewById(R.id.phoneText_Edit);
        EDIT_BTN = (LinearLayout) findViewById(R.id.Edit_btn);
        date = (TextView) findViewById(R.id.datetext_Edit);
        time = (TextView) findViewById(R.id.timetext_Edit);

        layoutdate.setOnClickListener(this);
        layouttime.setOnClickListener(this);
        EDIT_BTN.setOnClickListener(this);
        //

        Intent intent = getIntent();

        valuefrom = intent.getStringExtra("froms");
        valueto = intent.getStringExtra("tos");
        valuetel = intent.getStringExtra("numbers");
        valueprice = intent.getStringExtra("prices");
        valuedegree = intent.getStringExtra("degrees");
        valuetime = intent.getStringExtra("tims");
        valuetodate = intent.getStringExtra("dats");
        valuepass = intent.getStringExtra("passwords");

        editTextfrom.setText(valuefrom);
        editTextprice.setText(valueprice);
        editTextto.setText(valueto);
        editTextdegree.setText(valuedegree);
        time.setText(valuetime);
        date.setText(valuetodate);
        editTextphone.setText(valuetel);
        edittextpassword.setText(valuepass);

    }

    @Override
    public void onClick(View v) {

        if (v == EDIT_BTN) {
            delete();
            startActivity(new Intent(Edit.this, Main2Activity.class));
            finish();

        }

    }
    public void delete() {
        dref = FirebaseDatabase.getInstance().getReference();
             dref.child("Blog").orderByChild("passch").equalTo(valuepass).addChildEventListener(new ChildEventListener() {
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

}