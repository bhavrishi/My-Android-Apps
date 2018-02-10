package com.myonic.rishibhv.inclass11_group23;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import static com.myonic.rishibhv.inclass11_group23.R.id.phone;

public class SignUpActivity extends AppCompatActivity {
    int mYear,mMonth,mDay;          DatePickerDialog mDatePicker;    Calendar myCalendar;
    EditText email,fname,lname,pwd1,pwd2; Button submit;
    DatabaseReference databaseUsers;String uname;
    ArrayList<User> users;
    private FirebaseAuth mAuth;public static final String UID="uid";
    public static final String NAME="name";
    Uri fileUri;
    ProgressDialog progressDialog;

    private StorageReference imageReference,fileRef;




    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    private static final int MY_REQUEST_CODE = 3;

    ImageView imgreg;String key=null;
    private String imgPath = null, profileString;
    private File destination = null;
    private InputStream inputStreamImg;

    private Bitmap bitmap = null;


    @Override
    protected void onStart() {
        super.onStart();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                    users.add(postsnapshot.getValue(User.class));

                    //   Log.d("demot", postsnapshot.getKey() + uname);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        email= (EditText) findViewById(R.id.editTextEmailAct2);
        fname= (EditText) findViewById(R.id.editTextFirstName);
        lname= (EditText) findViewById(R.id.editTextLastName);
        users=new ArrayList<User>();
        pwd1= (EditText) findViewById(R.id.editTextPasswordAct2);
        pwd2= (EditText) findViewById(R.id.editTextConfirmPassword);
        submit= (Button) findViewById(R.id.btnsubmit);
        progressDialog=new ProgressDialog(this);
        imgreg= (ImageView) findViewById(R.id.imageViewSignUpAct);
        mAuth = FirebaseAuth.getInstance();
        //databaseContacts = FirebaseDatabase.getInstance().getReference("contacts").child(id_main);

        fileRef=null;
        imageReference = FirebaseStorage.getInstance().getReference().child("images");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName =fname.getText().toString();
                String lastName = lname.getText().toString();
                String emailst = email.getText().toString();

                String password =pwd1.getText().toString();
                String confirmPassword =pwd2.getText().toString();
                if(firstName.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter first name", Toast.LENGTH_SHORT).show();
                }
                else if(lastName.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter last name", Toast.LENGTH_SHORT).show();
                }
                else if(emailst.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }
                else if(confirmPassword.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(confirmPassword)){
                    Toast.makeText(SignUpActivity.this, "There is a password mismatch", Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<8){
                    Toast.makeText(SignUpActivity.this, "Password should be of minimum 8 characters", Toast.LENGTH_SHORT).show();
                }

                //Log.d("demo_ra", "ClickedSignUpInMain");
                else
                    createNewUser(fname.getText().toString()+" " +lname.getText().toString(),email.getText().toString(),pwd1.getText().toString());


            }
        });

        ImageView imgSignUpPhoto = (ImageView) findViewById(R.id.imageViewSignUpAct);
        imgSignUpPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });
    }
   /* private boolean writetoStorage(Bitmap bitmap, final String userId, final String name, final String email, final String pwd) {
        if (bitmap != null) {

            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            byte[] data = baos.toByteArray();

            final StorageReference fileRef = imageReference.child(email + "." + "jpg");
            fileRef.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.e("demo", "Uri: " + taskSnapshot.getDownloadUrl());
                    Log.e("demo", "Name: " + taskSnapshot.getMetadata().getName());

                    //Toast.makeText(AddContactActivity.this, "File Uploaded ", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    //adding an upload to firebase database
                    User user = new User(userId,name,email,pwd,taskSnapshot.getDownloadUrl().toString());

                    FirebaseDatabase.getInstance().getReference().child("users").child(userId).setValue(user);

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();

                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("Upload is paused!");
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            // percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        } else {
            Toast.makeText(SignUpActivity.this, "No File!", Toast.LENGTH_LONG).show();
     }
    }*/
    private void createNewUser(final String name, final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("demo", "createAccount: Success!");

                            // update UI with the signed-in user's information
                            final FirebaseUser user1 = mAuth.getCurrentUser();
                            //writeNewUser(user.getUid(), getUsernameFromEmail(user.getEmail()), user.getEmail());
                           // writeNewUser(user.getUid(),name,user.getEmail(), password);

                            if (bitmap != null) {

                                progressDialog.setTitle("Uploading...");
                                progressDialog.show();

                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                                byte[] data = baos.toByteArray();

                                final StorageReference fileRef = imageReference.child(email + "." + "jpg");
                                fileRef.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Log.e("demo", "Uri: " + taskSnapshot.getDownloadUrl());
                                        Log.e("demo", "Name: " + taskSnapshot.getMetadata().getName());

                                        //Toast.makeText(AddContactActivity.this, "File Uploaded ", Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                        //adding an upload to firebase database
                                        User user = new User(user1.getUid(),name,email,password,taskSnapshot.getDownloadUrl().toString());

                                        FirebaseDatabase.getInstance().getReference().child("users").child(user1.getUid()).setValue(user);

                                    }
                                })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressDialog.dismiss();

                                                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                                            }
                                        })
                                        .addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                                                System.out.println("Upload is paused!");
                                            }
                                        })
                                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                                                // percentage in progress dialog
                                                progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                                            }
                                        });
                            } else {
                                Toast.makeText(SignUpActivity.this, "No File!", Toast.LENGTH_LONG).show();
                            }//write
                            if(user1!=null&&bitmap!=null)
                            {
                                for(int i=0;i<users.size();i++)
                                {
                                    if((users.get(i).email).equals(email))
                                    {
                                        uname=users.get(i).getName();
                                    }
                                }

                                Intent i=new Intent(SignUpActivity.this,Display_Contacts_Activity.class);
                                i.putExtra(Constants.UID,user1.getUid());
                                i.putExtra(Constants.NAME,uname);
                                Log.d("ra_demoUserUid",user1.getUid().toString());
                                startActivity(i);
                                Toast.makeText(SignUpActivity.this, "SignUp Successfulllll", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.e("demo", "createAccount: Fail!", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }







    private  void writeNewUser(String userId, String name,String email, String password)
    {
        User user = new User(userId,name,email,password);

        FirebaseDatabase.getInstance().getReference().child("users").child(userId).setValue(user);
    }

    private void selectImage() {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.d("MyApp", "SDK >= 23");
                if (SignUpActivity.this.checkSelfPermission(android.Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    Log.d("MyApp", "Request permission");
                    ActivityCompat.requestPermissions(SignUpActivity.this,
                            new String[]{android.Manifest.permission.CAMERA},
                            MY_REQUEST_CODE);

                    if (!shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
                        showMessageOKCancel("You need to allow camera usage",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(SignUpActivity.this, new String[]{android.Manifest.permission.CAMERA},
                                                MY_REQUEST_CODE);
                                    }
                                });
                    }
                }
            }
            PackageManager pm = SignUpActivity.this.getPackageManager();
            int hasPerm = pm.checkPermission(android.Manifest.permission.CAMERA, SignUpActivity.this.getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(SignUpActivity.this);
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
                Toast.makeText(SignUpActivity.this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(SignUpActivity.this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(SignUpActivity.this)
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
                fileUri = data.getData();

                bitmap = (Bitmap) data.getExtras().get("data");
                imgreg.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY&&data!=null) {
            Uri selectedImage = data.getData();
            try {
                fileUri = data.getData();

                bitmap = MediaStore.Images.Media.getBitmap(SignUpActivity.this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                imgPath = getRealPathFromURI(selectedImage);
                destination = new File(imgPath.toString());
                imgreg.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = SignUpActivity.this.managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


}