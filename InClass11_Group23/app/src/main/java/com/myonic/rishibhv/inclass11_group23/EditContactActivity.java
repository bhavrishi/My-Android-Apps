package com.myonic.rishibhv.inclass11_group23;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class EditContactActivity extends AppCompatActivity {
String id_main; Contact contact;
    EditText fname,lname,email,phone;
    ImageView imgcont;  Button btnsave;
    DatabaseReference databaseContacts;
    ProgressDialog progressDialog;  Uri fileUri;
    private StorageReference imageReference;
    private FirebaseAuth mAuth;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    private static final int MY_REQUEST_CODE = 3;

    ImageView imgreg;String key=null;
    private String imgPath = null, profileString;
    private File destination = null;
    private InputStream inputStreamImg;


    private Bitmap bitmap = null;
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
                Intent i=new Intent(EditContactActivity.this,MainActivity.class);
                startActivity(i);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        contact=new Contact();
        Intent intent = getIntent();
        id_main=intent.getStringExtra(Constants.UID);
        contact= (Contact) intent.getSerializableExtra("ALIST");
        Log.d("demo",contact.toString());
        mAuth=FirebaseAuth.getInstance();
        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts").child(id_main).child(contact.getId());
        imageReference = FirebaseStorage.getInstance().getReference().child("images").child(id_main);


        progressDialog=new ProgressDialog(this);
        imgcont  = (ImageView) findViewById(R.id.imgchoosePhoto);
        fname= (EditText) findViewById(R.id.fname);
        lname= (EditText) findViewById(R.id.lname);
        phone= (EditText) findViewById(R.id.phonenum);
        email= (EditText) findViewById(R.id.email);
    btnsave  = (Button) findViewById(R.id.btnsave);
        Picasso.with(EditContactActivity.this).load(contact.getImgurl()).into(imgcont);

        fname.setText(contact.getFname());
        lname.setText(contact.getLname());
        email.setText(contact.getEmail());
        phone.setText(contact.getPhone());
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if(bitmap==null){
                Toast.makeText(EditContactActivity.this,"Select Image",Toast.LENGTH_SHORT).show();
            }
            else if(fname.getText().toString()==null)
            {
                Toast.makeText(EditContactActivity.this,"Enter First Name",Toast.LENGTH_SHORT).show();
            }
            else if(lname.getText().toString()==null)
            {
                Toast.makeText(EditContactActivity.this,"Enter Last Name",Toast.LENGTH_SHORT).show();
            }
            else if(email.getText().toString()==null)
            {
                Toast.makeText(EditContactActivity.this,"Enter Email ",Toast.LENGTH_SHORT).show();
            }
            else if(phone.getText().toString()==null)
            {
                Toast.makeText(EditContactActivity.this,"Enter Phone",Toast.LENGTH_SHORT).show();
            }
            else {
                writeToStorage(bitmap);
                Intent i = new Intent(EditContactActivity.this, Display_Contacts_Activity.class);
                i.putExtra(Constants.UID, id_main);
                startActivity(i);
            }
            }

        });
        imgcont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

    }
 public void   writeToStorage(Bitmap bitmap) {
        if (bitmap != null) {

            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            byte[] data = baos.toByteArray();
           /* final StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType("image/jpeg")
                    .setCustomMetadata("myCustomProperty", "myValue")
                    .build();*/
            final StorageReference fileRef = imageReference.child(phone.getText().toString() + "." + "jpg");
            fileRef.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.e("demo", "Uri: " + taskSnapshot.getDownloadUrl());
                    Log.e("demo", "Name: " + taskSnapshot.getMetadata().getName());

                    Toast.makeText(EditContactActivity.this, "File Uploaded ", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    //adding an upload to firebase database
                 //   String uploadId = databaseContacts.push().getKey();
                    Contact contact2 = new Contact(contact.getId(),fname.getText().toString(),lname.getText().toString(),email.getText().toString(),phone.getText().toString(), taskSnapshot.getDownloadUrl().toString());

                    databaseContacts.setValue(contact2);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();

                            Toast.makeText(EditContactActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    })
                   /* .addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("Upload is paused!");
                        }
                    })*/
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            // percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        } else {
            Toast.makeText(EditContactActivity.this, "No File!", Toast.LENGTH_LONG).show();
        }
    }
    private void selectImage() {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.d("MyApp", "SDK >= 23");
                if (EditContactActivity.this.checkSelfPermission(android.Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    Log.d("MyApp", "Request permission");
                    ActivityCompat.requestPermissions(EditContactActivity.this,
                            new String[]{android.Manifest.permission.CAMERA},
                            MY_REQUEST_CODE);

                    if (!shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
                        showMessageOKCancel("You need to allow camera usage",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(EditContactActivity.this, new String[]{android.Manifest.permission.CAMERA},
                                                MY_REQUEST_CODE);
                                    }
                                });
                    }
                }
            }
            PackageManager pm = EditContactActivity.this.getPackageManager();
            int hasPerm = pm.checkPermission(android.Manifest.permission.CAMERA, EditContactActivity.this.getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(EditContactActivity.this);
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
                Toast.makeText(EditContactActivity.this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(EditContactActivity.this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(EditContactActivity.this)
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
                imgreg.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY&&data!=null) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(EditContactActivity.this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                imgPath = getRealPathFromURI(selectedImage);
                destination = new File(imgPath.toString());
                imgcont.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (bitmap != null) {
            profileString = BitMapToString(bitmap);
            Log.d("demodimg", profileString);
        }
    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = EditContactActivity.this.managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();

        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
