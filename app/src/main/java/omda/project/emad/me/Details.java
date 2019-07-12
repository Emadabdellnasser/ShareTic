package omda.project.emad.me;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by EMAD on 3/2/2018.
 */

public class Details extends AppCompatActivity implements View.OnClickListener {
    private static final int MY_PERMITION_TO_READ_EXTERNAL_STORAGE = 1;
    private static final int PICK_IMAGE_REQUEST = 0;
    Uri currentUri = null;
    //

    EditText editTextfrom;
    EditText editTextto;
    EditText editTextprice;
    EditText editTextdegree;
    LinearLayout layouttime;
    LinearLayout layoutdate, post;
    EditText editTextphone;
    TextView date, time;
    Spinner degr;
    EditText edittextpassword;
    //
    boolean r = false;
    long recievedId;
    ProgressDialog pr;
    ImageView imageView;
    DatabaseReference dref;
    private StorageReference stref;
    private AdView mAdView;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        MobileAds.initialize(this, "ca-app-pub-2003869293764152/7859342093");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);
        setTitle("بيع تذكرة");
        pr = new ProgressDialog(this);
        stref = FirebaseStorage.getInstance().getReference();
        dref = FirebaseDatabase.getInstance().getReference().child("Blog");
        editTextfrom = (EditText) findViewById(R.id.fromText);
        editTextprice = (EditText) findViewById(R.id.priceText);
        editTextto = (EditText) findViewById(R.id.toText);
        degr = (Spinner) findViewById(R.id.spinner_degree);
        edittextpassword = (EditText) findViewById(R.id.passDetails);

        layouttime = (LinearLayout) findViewById(R.id.timeLayout);
        layoutdate = (LinearLayout) findViewById(R.id.dateLayout);
        editTextphone = (EditText) findViewById(R.id.phoneText);
        post = (LinearLayout) findViewById(R.id.sell);
        date = (TextView) findViewById(R.id.datetext);
        time = (TextView) findViewById(R.id.timetext);

        layoutdate.setOnClickListener(this);
        layouttime.setOnClickListener(this);
        post.setOnClickListener(this);

    }


    public Boolean detect(String TXT) {
        boolean res = false;
        for (int i = 0; i < Main2Activity.passwords.size(); i++) {
            if (TXT.equalsIgnoreCase(Main2Activity.passwords.get(i))) {
                res = true;
                break;
            }
        }
        return res;
    }

    public void posting() {
        pr.setMessage("جاري رفع التذكرة...");
        final String from = editTextfrom.getText().toString().toLowerCase().trim();
        final String price = editTextprice.getText().toString().toLowerCase().trim();
        final String to = editTextto.getText().toString().toLowerCase().trim();
        final String degree = degr.getSelectedItem().toString().toLowerCase().trim();
        final String timeValue = time.getText().toString().toLowerCase().trim();
        final String dateValue = date.getText().toString().toLowerCase().trim();
        final String phone = editTextphone.getText().toString().toLowerCase().trim();
        final String password = edittextpassword.getText().toString().toLowerCase().trim();

        if (!TextUtils.isEmpty(from) && !TextUtils.isEmpty(price) && !TextUtils.isEmpty(to) && !TextUtils.isEmpty(degree) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty((String.valueOf(mMonth))) && !TextUtils.isEmpty(String.valueOf(mHour))) {
            if (detect(password) == false) {
                pr.show();

                r = true;

                DatabaseReference newPost = dref.push();
                newPost.child("fromch").setValue(from);
                newPost.child("toch").setValue(to);
                newPost.child("priceCh").setValue(price);
                newPost.child("datech").setValue(dateValue);
                newPost.child("timech").setValue(timeValue);
                newPost.child("degeech").setValue(degree);
                newPost.child("phonech").setValue(phone);
                newPost.child("passch").setValue(password);
                startActivity(new Intent(Details.this, Main2Activity.class));
                finish();
                pr.dismiss();
            } else {

                edittextpassword.setError("هذا الرقم موجود بالفعل من فضلك ادخل رقم اخر...");
            }
        } else {
            Toast.makeText(Details.this, "من فضلك ادخل كل البيانات المطلوبة", Toast.LENGTH_LONG).show();
            pr.dismiss();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Ensuring();


            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void openingImageSelector() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Selecting Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMITION_TO_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openingImageSelector();

                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                currentUri = resultData.getData();
                imageView.setImageURI(currentUri);
                imageView.invalidate();
            }
        }
    }

    public void date(String dateValue) {
        String my_date = "31/12/2014";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date strDate = null;
        try {
            strDate = sdf.parse(my_date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (new Date().after(strDate)) {
            //  your_date_is_outdated = true;
        } else {
            //  your_date_is_outdated = false;
        }
    }

    @Override
    public void onClick(View v) {

        if (v == layoutdate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            //   String my_date = "31-12-2014";
                            Date todayDate = Calendar.getInstance().getTime();

                            // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            String my_date = sdf.format(todayDate);
                            // Toast.makeText(Details.this,my_date ,Toast.LENGTH_LONG).show();
                            String selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            Date strDate = null;
                            try {
                                strDate = sdf.parse(my_date);
                                todayDate = sdf.parse(selectedDate);
                            } catch (java.text.ParseException e) {
                                e.printStackTrace();
                            }
                            if (strDate.after(todayDate)) {
                                //  your_date_is_outdated = true;
                                Toast.makeText(Details.this, "يجب عليك ادخال تاريخ قادم..", Toast.LENGTH_SHORT).show();
                            } else {
                                //  your_date_is_outdated = false;
                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }


                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == layouttime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if (hourOfDay > 12) {
                                int h = hourOfDay - 12;
                                time.setText(h + ":" + minute + " PM");

                            } else if (hourOfDay == 12) {
                                time.setText(hourOfDay + ":" + minute + " PM");
                            } else if (hourOfDay < 12) {
                                time.setText(hourOfDay + ":" + minute + " AM");
                            }

                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == post) {
            posting();
        }
    }

    public void Ensuring() {
        if (r) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            super.onBackPressed();
            return;

        }
        DialogInterface.OnClickListener discardBtClick =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        // Showing dialog  to inform that there are unsaved changes
        makesureOnSavingDataDialog(discardBtClick);

    }

    @Override
    public void onBackPressed() {
        Ensuring();
    }

    private void makesureOnSavingDataDialog(
            DialogInterface.OnClickListener discardBtClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("هل تريد الخروج ؟");
        builder.setPositiveButton("خروج", discardBtClick);
        builder.setNegativeButton("ابقا هنا", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
