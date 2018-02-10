package com.myonic.rishibhv.upload_three;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class StorageActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "StorageActivity";
    //track Choosing Image Intent
    private static final int CHOOSING_IMAGE_REQUEST = 1234;

    private TextView tvFileName;
    private ImageView imageView;
    private EditText edtFileName;
    String id_main;
    private Uri fileUri;
    DatabaseReference databaseImages;
    private Bitmap bitmap;
    private StorageReference imageReference,fileRef;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        Intent intent = getIntent();
        id_main = intent.getStringExtra("uid");
        imageView = (ImageView) findViewById(R.id.img_file);
        edtFileName = (EditText) findViewById(R.id.edt_file_name);
        tvFileName = (TextView) findViewById(R.id.tv_file_name);
        tvFileName.setText("");
        databaseImages = FirebaseDatabase.getInstance().getReference("contacts").child(id_main);
fileRef=null;
        imageReference = FirebaseStorage.getInstance().getReference().child("images").child(id_main);
        progressDialog = new ProgressDialog(this);

        findViewById(R.id.btn_choose_file).setOnClickListener(this);
        findViewById(R.id.btn_upload_byte).setOnClickListener(this);
        findViewById(R.id.btn_upload_file).setOnClickListener(this);
        findViewById(R.id.btn_upload_stream).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.btn_download_byte).setOnClickListener(this);
        findViewById(R.id.btn_download_file).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (i == R.id.btn_choose_file) {
            showChoosingFile();
        } else if (i == R.id.btn_upload_byte) {
            uploadBytes();
        } else if (i == R.id.btn_upload_file) {
            uploadFile();
        } else if (i == R.id.btn_upload_stream) {
            uploadStream();
        } else if (i == R.id.btn_back) {
            finish();
        } else if (i == R.id.btn_download_byte) {
                downloadInMemory();
            } else if (i == R.id.btn_download_file) {
                downloadToLocalFile();
            }
        }
    private void downloadToLocalFile() {
        if (imageReference != null) {
            progressDialog.setTitle("Downloading...");
            progressDialog.setMessage(null);
            progressDialog.show();
Log.d("demo","inside");
            try {
                final File localFile = File.createTempFile("resw", "jpg");
                StorageReference fileRef = imageReference.child("stream" + "." + "jpg");
                fileRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bmp = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        imageView.setImageBitmap(bmp);
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        progressDialog.dismiss();
                        Toast.makeText(StorageActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // progress percentage
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                        // percentage in progress dialog
                        progressDialog.setMessage("Downloaded " + ((int) progress) + "%...");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(StorageActivity.this, "Upload file before downloading", Toast.LENGTH_LONG).show();
        }
    }
    private void downloadInMemory() {
        StorageReference fileRef = imageReference.child("resw" + "." + "jpg");
        fileRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(StorageActivity.this, "Deleted", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void showChoosingFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), CHOOSING_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (bitmap != null) {
            bitmap.recycle();
        }

        if (requestCode == CHOOSING_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            fileUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), fileUri);
                //  imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();

        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private boolean validateInputFileName(String fileName) {

        if (TextUtils.isEmpty(fileName)) {
            Toast.makeText(StorageActivity.this, "Enter file name!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void uploadBytes() {

        if (fileUri != null) {
            final String fileName = edtFileName.getText().toString();

            if (!validateInputFileName(fileName)) {
                return;
            }
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            byte[] data = baos.toByteArray();
            final StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType("image/jpeg")
                    .setCustomMetadata("myCustomProperty", "myValue")
                    .build();
            final StorageReference fileRef = imageReference.child(fileName + "." + getFileExtension(fileUri));
            fileRef.putBytes(data,metadata).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.e(TAG, "Uri: " + taskSnapshot.getDownloadUrl());
                    Log.e(TAG, "Name: " + taskSnapshot.getMetadata().getName());

                    tvFileName.setText(taskSnapshot.getMetadata().getPath() + " - "
                            + taskSnapshot.getMetadata().getSizeBytes() / 1024 + " KBs");
                  /*  fileRef.updateMetadata(metadata).addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                        @Override
                        public void onSuccess(StorageMetadata storageMetadata) {
                            Toast.makeText(StorageActivity.this, "Meta Data Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    });*/
                    Log.e(TAG, "Uri: " + taskSnapshot.getDownloadUrl());
                    Log.e(TAG, "Name: " + taskSnapshot.getMetadata().getName());
                    Toast.makeText(StorageActivity.this, "File Uploaded ", Toast.LENGTH_LONG).show();
                    Upload upload = new Upload(fileName, taskSnapshot.getDownloadUrl().toString());
                    progressDialog.dismiss();
                    //adding an upload to firebase database
                    String uploadId = databaseImages.push().getKey();
                    databaseImages.child(uploadId).setValue(upload);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();

                            Toast.makeText(StorageActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

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
            Toast.makeText(StorageActivity.this, "No File!", Toast.LENGTH_LONG).show();
        }
    }

    private void uploadFile() {

        if (fileUri != null) {
            final String fileName = edtFileName.getText().toString();

            if (!validateInputFileName(fileName)) {
                return;
            }
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference fileRef = imageReference.child(fileName + "." + getFileExtension(fileUri));
            fileRef.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();

                    Log.e(TAG, "Uri: " + taskSnapshot.getDownloadUrl());
                    Log.e(TAG, "Name: " + taskSnapshot.getMetadata().getName());

                    tvFileName.setText(taskSnapshot.getMetadata().getPath() + " - "
                            + taskSnapshot.getMetadata().getSizeBytes() / 1024 + " KBs");
                    Toast.makeText(StorageActivity.this, "File Uploaded ", Toast.LENGTH_LONG).show();
                    Upload upload = new Upload(fileName, taskSnapshot.getDownloadUrl().toString());

                    //adding an upload to firebase database
                    String uploadId = databaseImages.push().getKey();
                    databaseImages.child(uploadId).setValue(upload);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();

                            Toast.makeText(StorageActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            // progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            // percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    })
                    .addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("Upload is paused!");
                        }
                    });
        } else {
            Toast.makeText(StorageActivity.this, "No File!", Toast.LENGTH_LONG).show();
        }
    }

    private void uploadStream() {
        if (fileUri != null) {
            String fileName = edtFileName.getText().toString();

            if (!validateInputFileName(fileName)) {
                return;
            }

            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            try {
                InputStream stream = getContentResolver().openInputStream(fileUri);

                StorageReference fileRef = imageReference.child(fileName + "." + getFileExtension(fileUri));
                fileRef.putStream(stream)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Log.e(TAG, "Uri: " + taskSnapshot.getDownloadUrl());
                                Log.e(TAG, "Name: " + taskSnapshot.getMetadata().getName());

                                tvFileName.setText(taskSnapshot.getMetadata().getPath() + " - "
                                        + taskSnapshot.getMetadata().getSizeBytes() / 1024 + " KBs");
                                Toast.makeText(StorageActivity.this, "File Uploaded ", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                progressDialog.dismiss();

                                Toast.makeText(StorageActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                // because this is a stream so:
                                // taskSnapshot.getTotalByteCount() = -1 (always)
                                progressDialog.setMessage("Uploaded " + taskSnapshot.getBytesTransferred() + " Bytes...");
                            }
                        })
                        .addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                                System.out.println("Upload is paused!");
                            }
                        });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(StorageActivity.this, "No File!", Toast.LENGTH_LONG).show();
        }
    }

}

