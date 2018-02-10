package com.hwork.uncc.hw2_groups25;

import android.app.DatePickerDialog;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditSaveActivity extends AppCompatActivity {
    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );
    private DatePickerDialog.OnDateSetListener mDateListener;
    ImageView takePhoto, defaultphoto;boolean click;
    public static final int REQ_CODE = 10;
    Bitmap camImg = null, bm;
    String date;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                camImg = (Bitmap) data.getExtras().get("data");
                takePhoto.setImageBitmap(camImg);
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_input);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        List<Contact> selectedCont=
                (List<Contact>)bundle.getSerializable("value");
        final List<Contact> storedCont=CreateContactActivity.retrieveContactDetails();

        final int position = (int) bundle.getSerializable("position");

        final EditText lastname = (EditText) findViewById(R.id.inputlast);
        final EditText firstname = (EditText) findViewById(R.id.inputfirst);
        final EditText inputDate = (EditText) findViewById(R.id.inputBDate);

        final EditText email = (EditText) findViewById(R.id.inputEmail);
        final EditText phone = (EditText) findViewById(R.id.inputPhone);
        final EditText company = (EditText) findViewById(R.id.inputCompany);
        final EditText url = (EditText) findViewById(R.id.inputURL);
        final EditText address = (EditText) findViewById(R.id.inputAddress);
        final EditText nickname = (EditText) findViewById(R.id.inputNickName);
        final EditText fb = (EditText) findViewById(R.id.inputFBURL);
        final EditText utub = (EditText) findViewById(R.id.inputUtube);
        final EditText skype = (EditText) findViewById(R.id.inputSkype);
        final EditText twitter = (EditText) findViewById(R.id.inputTwitter);
        Button btnsave= (Button) findViewById(R.id.btnSave);

        lastname.setText(selectedCont.get(0).getLastname());
        firstname.setText(selectedCont.get(0).getFirstName());
        inputDate.setText(selectedCont.get(0).getBday());
        email.setText(selectedCont.get(0).getEmail());
        phone.setText(selectedCont.get(0).getPhoneNumber());
        company.setText(selectedCont.get(0).getCompany());
        url.setText(selectedCont.get(0).getUrl());
        address.setText(selectedCont.get(0).getAddress());
        nickname.setText(selectedCont.get(0).getNickname());
        fb.setText(selectedCont.get(0).getFburl());
        utub.setText(selectedCont.get(0).getUtb());
        skype.setText(selectedCont.get(0).getSkype());
        twitter.setText(selectedCont.get(0).getTwitURL());
        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                // cal.set(1850, 12, 01);
                inputDate.setText(new StringBuilder()
                        // Month is 0 based, just add 1
                        .append(month + 1).append("-").append(day).append("-")
                        .append(year).append(" "));


                DatePickerDialog dialog = new DatePickerDialog(EditSaveActivity.this, android.R.style.Theme_Holo_Light_Dialog, mDateListener, day, month, year);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                final DatePicker datePicker = dialog.getDatePicker();

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                Date date = null;
                try {
                    date = (Date)formatter.parse("01-December-1850");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long mills = date.getTime();
                datePicker.setMinDate(mills);
                // dialog.)init(2011, 7, 17, null);
                dialog.show();
            }
        });
        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int mon, int day) {
                mon += 1;

                date = year + "/" + mon + "/" + day;
                inputDate.setText(date);

                Log.d("demo", date);
            }
        };

        takePhoto = (ImageView) findViewById(R.id.imageView);
        defaultphoto = (ImageView) findViewById(R.id.imageView);
        /*takePhoto.setImageBitmap(selectedCont.get(0).getImg());
        if(selectedCont.get(0).getImg()==null) {

            takePhoto.setImageResource(R.drawable.default_image);
            //img.setImageBitmap(entry.getImg());
        }*/

        //
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click = true;
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camIntent, REQ_CODE);
            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap mBitmap;
                if (click == false) {
                    defaultphoto.setImageResource(R.drawable.default_image);
                    Bitmap bitmap = defaultphoto.getDrawingCache();
                    bm = ((BitmapDrawable) defaultphoto.getDrawable()).getBitmap();

                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bmImage;
                if (camImg != null){ camImg.compress(Bitmap.CompressFormat.JPEG, 80, baos); bmImage=camImg;}

                else
                {bm.compress(Bitmap.CompressFormat.JPEG, 80, baos);bmImage=null;}
                try {
                    if (checkEmail(email.getText().toString()) && isValidPhone(phone.getText().toString())&&!(firstname.getText().toString().isEmpty())&&!(lastname.getText().toString().isEmpty())&&!(phone.getText().toString().isEmpty())) {
                        Contact contact = new Contact(firstname.getText().toString(),lastname.getText().toString(),email.getText().toString(),company.getText().toString(),
                                url.getText().toString(),address.getText().toString(),date,nickname.getText().toString(),fb.getText().toString(),twitter.getText().toString(),skype.getText().toString(),utub.getText().toString(),
                                phone.getText().toString(),bmImage);

                        storedCont.set(position,contact);
                        Log.d("democ",contact.toString());

                        finish();
                    }
                    else
                    {Log.d("demostring",""+firstname.getText().toString().isEmpty());
                        if(firstname.getText().toString().isEmpty()) Toast.makeText(EditSaveActivity.this, "Please enter First Name", Toast.LENGTH_SHORT).show();
                        if(lastname.getText().toString().isEmpty()) Toast.makeText(EditSaveActivity.this, "Please enter Last Name", Toast.LENGTH_SHORT).show();
                        if(phone.getText().toString().isEmpty()) Toast.makeText(EditSaveActivity.this, "Please enter Phone", Toast.LENGTH_SHORT).show();
                        if(checkEmail(email.getText().toString())==false) Toast.makeText(EditSaveActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                        if(isValidPhone(phone.getText().toString())==false)Toast.makeText(EditSaveActivity.this, "Enter valid phone", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private boolean checkEmail(String email) {
        if(email.isEmpty())return true;
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
    public static boolean isValidPhone(String phone)
    {
        Log.d("demonull",""+phone);
        if(phone.isEmpty())
            return true;
        String expression = "^([0-9\\+]|\\(\\d{1,3}\\))[0-9\\-\\. ]{3,15}$";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            Log.d("demophone","true");
            return true;
        }
        else{
            Log.d("demophone","false");
            return false;
        }
    }

}
