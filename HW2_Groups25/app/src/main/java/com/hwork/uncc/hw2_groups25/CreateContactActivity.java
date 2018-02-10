package com.hwork.uncc.hw2_groups25;


import android.app.DatePickerDialog;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Context;
import android.content.Intent;

import static android.provider.ContactsContract.*;

import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateContactActivity extends AppCompatActivity {
    public static final int REQ_CODE = 10;
   public static List<Contact> contactList=new ArrayList<Contact>();
    Context cntx;
    boolean click;   EditText email;
    Bitmap camImg = null, bm;
    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );
    String date;
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private DatePickerDialog.OnDateSetListener mDateListener;
    ImageView takePhoto, defaultphoto;

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
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                }
                return;
            }
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Button btnSave;
        boolean emailcheck,phonecheck,commoncheck;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_input);
        cntx = getApplicationContext();
        final EditText lastname = (EditText) findViewById(R.id.inputlast);
        final EditText firstname = (EditText) findViewById(R.id.inputfirst);
        final EditText inputDate = (EditText) findViewById(R.id.inputBDate);

      email = (EditText) findViewById(R.id.inputEmail);
        final EditText phone = (EditText) findViewById(R.id.inputPhone);
        final EditText company = (EditText) findViewById(R.id.inputCompany);
        final EditText url = (EditText) findViewById(R.id.inputURL);
        final EditText address = (EditText) findViewById(R.id.inputAddress);
        final EditText nickname = (EditText) findViewById(R.id.inputNickName);
        final EditText fb = (EditText) findViewById(R.id.inputFBURL);
        final EditText utub = (EditText) findViewById(R.id.inputUtube);
        final EditText skype = (EditText) findViewById(R.id.inputSkype);
        final EditText twitter = (EditText) findViewById(R.id.inputTwitter);
//final DatePicker dp= (DatePicker) findViewById(R.id.inputBDate);;

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


                DatePickerDialog dialog = new DatePickerDialog(CreateContactActivity.this, android.R.style.Theme_Holo_Light_Dialog, mDateListener, day, month, year);
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
        //
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click = true;
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camIntent, REQ_CODE);
            }
        });




           findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Bitmap mBitmap;
                    if (click == false) {
                        defaultphoto.setImageResource(R.drawable.default_image);
                        Bitmap bitmap = defaultphoto.getDrawingCache();
                        bm = ((BitmapDrawable) defaultphoto.getDrawable()).getBitmap();

                    }

                    ArrayList<ContentProviderOperation> cpos = new ArrayList<ContentProviderOperation>();
                    int cindex_ID = cpos.size();//ContactSize

                    cpos.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)//Step1
                            .withValue(RawContacts.ACCOUNT_TYPE, "com.google")
                            .withValue(RawContacts.ACCOUNT_NAME, "ÿour google account").build());

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Bitmap bmImage;
                    if (camImg != null){ camImg.compress(Bitmap.CompressFormat.JPEG, 80, baos); bmImage=camImg;}

                    else
                    {bm.compress(Bitmap.CompressFormat.JPEG, 80, baos);bmImage=null;}

                    byte[] b = baos.toByteArray();

                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)//Step2
                            .withValueBackReference(Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Photo.DATA15, b) // Name of the contact
                            .build());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)//Step2
                            .withValueBackReference(Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.StructuredName.GIVEN_NAME, firstname.getText().toString())
                            .build());
                    Log.d("demo", firstname.toString());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)//Step2
                            .withValueBackReference(Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.StructuredName.FAMILY_NAME, lastname.getText().toString())
                            .build());
                    Log.d("demo", lastname.toString());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)//Step2
                            .withValueBackReference(Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Organization.COMPANY, company.getText().toString())
                            .build());
                    Log.d("demo", company.toString());
                    //Mobile number will be inserted in ContactsContract.Data table
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)//Step 3
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Phone.NUMBER, phone.getText().toString())
                            .withValue(CommonDataKinds.Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE).build());
                    Log.d("demo", phone.toString());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)//Step 3
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(CommonDataKinds.Email.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Email.ADDRESS, email.getText().toString())
                            .withValue(ContactsContract.CommonDataKinds.Email.TYPE, CommonDataKinds.Email.TYPE_HOME).build());
                    Log.d("demo", email.toString());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.Website.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Website.URL, url.getText().toString())
                            .withValue(CommonDataKinds.Website.TYPE, CommonDataKinds.Website.TYPE_HOME).build());
                    Log.d("demo", url.toString());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS, address.getText().toString())
                            .withValue(CommonDataKinds.StructuredPostal.TYPE, CommonDataKinds.StructuredPostal.TYPE_HOME).build());
                    Log.d("demo", address.toString());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                            .withValueBackReference(Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.Event.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Event.START_DATE, date)
                            .withValue(CommonDataKinds.Event.TYPE, CommonDataKinds.Event.TYPE_BIRTHDAY)
                            .build());
                    Log.d("demo", "" + date);
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                            .withValueBackReference(Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Nickname.NAME, nickname.getText().toString())
                            .withValue(CommonDataKinds.Nickname.TYPE, CommonDataKinds.Nickname.TYPE_CUSTOM)
                            .build());
                    Log.d("demo", nickname.toString());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.Website.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Website.URL, utub.getText().toString())
                            .withValue(CommonDataKinds.Website.TYPE, CommonDataKinds.Website.TYPE).build());
                    Log.d("demo", utub.toString());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.Website.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Website.URL, skype.getText().toString())
                            .withValue(CommonDataKinds.Website.TYPE, CommonDataKinds.Website.TYPE_BLOG).build());
                    Log.d("demo", skype.toString());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.Website.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Website.URL, twitter.getText().toString())
                            .withValue(CommonDataKinds.Website.TYPE, CommonDataKinds.Website.TYPE_CUSTOM).build());
                    Log.d("demo", twitter.toString());
                    cpos.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, cindex_ID)
                            .withValue(Data.MIMETYPE, CommonDataKinds.Website.CONTENT_ITEM_TYPE)
                            .withValue(CommonDataKinds.Website.URL, fb.getText().toString())
                            .withValue(CommonDataKinds.Website.TYPE, CommonDataKinds.Website.TYPE_PROFILE).build());
                    Log.d("demo", fb.toString());
                    Log.d("demostring",""+firstname.getText().toString().isEmpty());

                    try {
                        if (checkEmail(email.getText().toString()) && isValidPhone(phone.getText().toString())&&!(firstname.getText().toString().isEmpty())&&!(lastname.getText().toString().isEmpty())&&!(phone.getText().toString().isEmpty())) {
                            Contact contact = new Contact(firstname.getText().toString(),lastname.getText().toString(),email.getText().toString(),company.getText().toString(),
                                    url.getText().toString(),address.getText().toString(),date,nickname.getText().toString(),fb.getText().toString(),twitter.getText().toString(),skype.getText().toString(),utub.getText().toString(),
                                    phone.getText().toString(),bmImage);

                            contactList.add(contact);
                            Log.d("democ",contact.toString());
                            ContentProviderResult[] contentProresult = null;
                            contentProresult = getApplicationContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, cpos);
                            finish();
                        }
                        else
                        {Log.d("demostring",""+firstname.getText().toString().isEmpty());
                            if(firstname.getText().toString().isEmpty()) Toast.makeText(CreateContactActivity.this, "Please enter First Name", Toast.LENGTH_SHORT).show();
                            if(lastname.getText().toString().isEmpty()) Toast.makeText(CreateContactActivity.this, "Please enter Last Name", Toast.LENGTH_SHORT).show();
                            if(phone.getText().toString().isEmpty()) Toast.makeText(CreateContactActivity.this, "Please enter Phone", Toast.LENGTH_SHORT).show();
                            if(checkEmail(email.getText().toString())==false) Toast.makeText(CreateContactActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                            if(isValidPhone(phone.getText().toString())==false)Toast.makeText(CreateContactActivity.this, "Enter valid phone", Toast.LENGTH_SHORT).show();

                        }

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (OperationApplicationException e) {
                        e.printStackTrace();
                    }
                }

            });


    }
    public static List<Contact> retrieveContactDetails(){
        return contactList;
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

    /*public boolean isValidEmail(String email) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();
        Log.d("demoemail", "" + check);

        return check;

    }*/


}
/*
 Intent saveintent = new Intent(Intents.Insert.ACTION);
              //  saveintent.setData(Uri.parse(String.valueOf(firstname.getText())));
             saveintent.setType(ContactsContract.RawContacts.CONTENT_TYPE);


   saveintent
                        .putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText())
                        .putExtra(ContactsContract.Intents.Insert.NAME, firstname.getText() + " " + lastname.getText())
                        .putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText())
                .putExtra(ContactsContract.Intents.Insert.COMPANY,company.getText())
                       // .putExtra(ContactsContract.Intents.Insert.Website,address.getText())
                        .putExtra(CommonDataKinds.Website.URL,url.getText());

                startActivity(saveintent);

               /* ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
                int raw_Contact_ID = 2;
                try {
                    ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
                            .withValue(RawContacts.ACCOUNT_TYPE, null)
                            .withValue(RawContacts.ACCOUNT_NAME, null)
                            .build());

                } catch (Exception e) {
                    Log.e("Ädd", "Could not find account type null");
                }

                ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
                        .withValueBackReference(Data.RAW_CONTACT_ID, raw_Contact_ID)
                        .withValue(Data.MIMETYPE, CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(CommonDataKinds.StructuredName.GIVEN_NAME, firstname.getText().toString())
                        .withValue(CommonDataKinds.StructuredName.FAMILY_NAME, lastname.getText().toString())

                        .build()
                );
            //    ContentProviderResult[] result = new ContentProviderResult[2];


               *//* ContentProviderResult[] res = new ContentProviderResult[0];
                try {
                    res = getContentResolver().applyBatch(AUTHORITY, ops);
                } catch (RemoteException e) {
//e.printStackTrace();
                    Log.e("getContentResolver()",Log.getStackTraceString(e));
                } catch (OperationApplicationException e) {
//e.printStackTrace();
                    Log.e("getContentResolver()",Log.getStackTraceString(e));
                } catch (Exception e) {
//e.printStackTrace();
                    Log.e("getContentResolver()",Log.getStackTraceString(e));
                } finally {
                    Log.d("getContentResolver.","..finally");
                }


                if (res != null && res[0] != null) {
                    Uri newContactUri = res[0].uri;
//02-20 22:21:09 URI added contact:content://com.android.contacts/raw_contacts/612
                    Log.d("AddContact", "URI added contact:" + newContactUri);
                } else Log.e("AddContact", "Contact not added.");*//*
                try {
                    ContentProviderResult[] results = getContentResolver().applyBatch(AUTHORITY, ops);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }

}
 */