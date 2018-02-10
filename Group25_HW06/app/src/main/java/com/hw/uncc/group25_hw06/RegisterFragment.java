package com.hw.uncc.group25_hw06;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment {
    private static final int MY_REQUEST_CODE = 3;
    private OnFragmentInteractionListener mListener;
    private Bitmap bitmap = null;
    private File destination = null;
    private InputStream inputStreamImg;
     ImageView imgreg;String key=null;
    private String imgPath = null, profileString;
    UserDBManager dm;

    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dm=new UserDBManager(getActivity());
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                // TODO put your code here to respond to the button tap
                Toast.makeText(getActivity(), "Home", Toast.LENGTH_SHORT).show();
                mListener.gotoCourseManagerfrag();
                return true;
            case R.id.item_inst:
                Toast.makeText(getActivity(), "Please log in/Sign Up to Continue", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_add_ins:
                mListener.gotoAddInsfrag();
                Toast.makeText(getActivity(), "ADD Instructor!",Toast.LENGTH_SHORT).show();

                return true;
            case R.id.item_logout:
                Toast.makeText(getActivity(), "Logging out!!",Toast.LENGTH_SHORT).show();
                mListener.gotoLogoutListener();
                return true;
            case R.id.item_exit:
                //   
                getActivity().finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText fname = (EditText) getView().findViewById(R.id.tfname);
        final EditText lname = (EditText) getView().findViewById(R.id.tlanme);
        final EditText uname = (EditText) getView().findViewById(R.id.tuname);
        final EditText pwd = (EditText) getView().findViewById(R.id.tpwd);
       imgreg= (ImageView) getView().findViewById(R.id.imguser);
        imgreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

                ;

            }
        });
        getView().findViewById(R.id.btnuserreg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
boolean flag=false;
                ArrayList<User> notes = (ArrayList<User>) dm.getAllNotes();

                Log.d("demo", "" + notes.toString());
                for (int i = 0; i < notes.size(); i++) {
                    HashMap<String, String> tempmap = notes.get(i).getUpwd();
                    for (Map.Entry<String, String> entry : tempmap.entrySet()) {
                        key = entry.getKey();
                        if ((uname.getText().toString()).equals(key)) {
                            flag = true;
                    }

                    }
                }

                if (flag) {
                    flag = true;
                    Toast.makeText(getActivity(), "Please select another User Name", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Please Sign Up", Toast.LENGTH_SHORT).show();
                    mListener.getRegister(profileString,fname.getText().toString(),lname.getText().toString(),uname.getText().toString(),pwd.getText().toString());

                }


                       }
        });

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name


        void getRegister(String profileString, String fname, String lname, String uname, String pwd);

        void gotoCourseManagerfrag();

        void gotoAddInsfrag();

        void gotoLogoutListener();
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void selectImage() {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.d("MyApp", "SDK >= 23");
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    Log.d("MyApp", "Request permission");
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            MY_REQUEST_CODE);

                    if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        showMessageOKCancel("You need to allow camera usage",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                                                MY_REQUEST_CODE);
                                    }
                                });
                    }
                }
            }
            PackageManager pm = getActivity().getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getActivity().getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
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
                Toast.makeText(getActivity(), "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
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
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                    Log.e("Activity", "Pick from Gallery::>>> ");

                    imgPath = getRealPathFromURI(selectedImage);
                    destination = new File(imgPath.toString());
                    imgreg.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            if (bitmap != null) {
                profileString = BitMapToString(bitmap);
                Log.d("demodimg", profileString);
            }
        }}

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}

