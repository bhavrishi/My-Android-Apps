package com.myonic.rishibhv.inclass11_group23;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class EditSignInUserActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;String imgurl=null;
    EditText email,fname,lname,pwd1,pwd2; Button submit;
    DatabaseReference databaseUsers; ImageView imgvw;
    String id_main;
    public static final String UID = "uid";
    private Bitmap bitmap = null;
    private File destination = null;
    private InputStream inputStreamImg;
    ImageView imgreg;String key=null;
    Uri fileUri;
    private String imgPath = null, profileString;
    ProgressDialog progressDialog;


    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    private static final int MY_REQUEST_CODE = 3;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                mAuth.signOut();
                Intent i=new Intent(EditSignInUserActivity.this,MainActivity.class);
                startActivity(i);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Intent intent = getIntent();
        id_main=intent.getStringExtra(Constants.UID);
        Log.d("demo",id_main);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users").child(id_main);
        email= (EditText) findViewById(R.id.editTextEmailAct2);
        imgvw= (ImageView) findViewById(R.id.imageViewSignUpAct);
        fname= (EditText) findViewById(R.id.editTextFirstName);
        lname= (EditText) findViewById(R.id.editTextLastName);
        //users=new ArrayList<User>();
        pwd1= (EditText) findViewById(R.id.editTextPasswordAct2);
        pwd2= (EditText) findViewById(R.id.editTextConfirmPassword);
        submit= (Button) findViewById(R.id.btnsubmit);

imgvw.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        selectImage();
    }
});
        id_main = intent.getStringExtra(UID);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditSignInUserActivity.this,"Sorry!! You Can not edit Email",Toast.LENGTH_SHORT).show();
            }
        });
        mAuth = FirebaseAuth.getInstance();



       // databaseUsers = FirebaseDatabase.getInstance().getReference("users").child(intent.getStringExtra(UID));

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // users.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                    if ((postsnapshot.getKey()).equals("email"))
                        email.setText(postsnapshot.getValue(String.class));
                    else if ((postsnapshot.getKey()).equals("name"))
                        fname.setText(postsnapshot.getValue(String.class));
                    else if ((postsnapshot.getKey()).equals("password"))
                        pwd1.setText(postsnapshot.getValue(String.class));
                    else if((postsnapshot.getKey()).equals("url")){
                    imgurl=   postsnapshot.getValue(String.class);
                        Picasso.with(EditSignInUserActivity.this).load(imgurl).into(imgvw);}

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewUser();

            }
        });




    }

    private  void writeNewUser()
    {
        User user = new User(id_main,fname.getText().toString()+" "+lname.getText().toString(),email.getText().toString(),pwd1.getText().toString(),imgurl);

       databaseUsers.setValue(user);
        Intent i=new Intent(EditSignInUserActivity.this,Display_Contacts_Activity.class);
        i.putExtra(Constants.UID,id_main);
        startActivity(i);


    }

    private void selectImage() {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.d("MyApp", "SDK >= 23");
                if (EditSignInUserActivity.this.checkSelfPermission(android.Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    Log.d("MyApp", "Request permission");
                    ActivityCompat.requestPermissions(EditSignInUserActivity.this,
                            new String[]{android.Manifest.permission.CAMERA},
                            MY_REQUEST_CODE);

                    if (!shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
                        showMessageOKCancel("You need to allow camera usage",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(EditSignInUserActivity.this, new String[]{android.Manifest.permission.CAMERA},
                                                MY_REQUEST_CODE);
                                    }
                                });
                    }
                }
            }
            PackageManager pm = EditSignInUserActivity.this.getPackageManager();
            int hasPerm = pm.checkPermission(android.Manifest.permission.CAMERA, EditSignInUserActivity.this.getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(EditSignInUserActivity.this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(EditSignInUserActivity.this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(EditSignInUserActivity.this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(EditSignInUserActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (requestCode == PICK_IMAGE_CAMERA&&data!=null) {
            try {

                bitmap = (Bitmap) data.getExtras().get("data");
                imgvw.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY&&data!=null) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(EditSignInUserActivity.this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                imgPath = getRealPathFromURI(selectedImage);
                destination = new File(imgPath.toString());
                imgvw.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = EditSignInUserActivity.this.managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
