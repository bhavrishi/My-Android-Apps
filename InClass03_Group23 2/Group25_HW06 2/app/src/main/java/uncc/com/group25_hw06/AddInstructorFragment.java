package uncc.com.group25_hw06;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
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


public class AddInstructorFragment extends Fragment {
    private static final int MY_REQUEST_CODE = 3;
    OnFragmentInteractionListener mListener;
    private Bitmap bitmap = null;
    private File destination = null;
    private InputStream inputStreamImg;
    private String imgPath = null, profileString="";
    ImageView captureImg;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "You  allow camera usage :)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "You did not allow camera usage :(", Toast.LENGTH_SHORT).show();

                }
                return;
            }
        }
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
                Toast.makeText(getActivity(), "List of Instructors", Toast.LENGTH_SHORT).show();
                mListener.getListInstructors();
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
                getActivity().finish();
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_instructor, container, false);
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
        final EditText email = (EditText) getView().findViewById(R.id.tuname);
        final EditText website = (EditText) getView().findViewById(R.id.tpwd);
        captureImg = (ImageView) getView().findViewById(R.id.imguser);
        captureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });

        getView().findViewById(R.id.buttonReset).setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname.setText("");
                lname.setText("");
                email.setText("");
                website.setText("");
                profileString = "";
                captureImg.setImageResource(R.drawable.add_photo);
            }
        }));

        getView().findViewById(R.id.btninsregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fname.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please enter the first name", Toast.LENGTH_SHORT).show();
                }
                else if(lname.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please enter the last name", Toast.LENGTH_SHORT).show();
                }
                else if(email.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please enter the email", Toast.LENGTH_SHORT).show();
                }
                else if(website.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please enter the personal website", Toast.LENGTH_SHORT).show();
                }
                else if(profileString.equals("")){
                    Toast.makeText(getActivity(), "Please choose the image", Toast.LENGTH_SHORT).show();
                }
                else {
                    mListener.gotoSaveInstructorProfile(fname.getText().toString(), lname.getText().toString(), email.getText().toString(),
                            website.getText().toString(), profileString, MainActivity.currentUser);
                    Toast.makeText(getActivity(), "Instructor added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public interface OnFragmentInteractionListener {
        void gotoSaveInstructorProfile(String fname, String lname, String email, String website, String profileString, String returnuname);
        void gotoAddInsfrag();
        void getListInstructors();
        void gotoLogoutListener();
        void gotoCourseManagerfrag();
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
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {

                bitmap = (Bitmap) data.getExtras().get("data");
                captureImg.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                imgPath = getRealPathFromURI(selectedImage);
                destination = new File(imgPath.toString());
                captureImg.setImageBitmap(bitmap);

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
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
