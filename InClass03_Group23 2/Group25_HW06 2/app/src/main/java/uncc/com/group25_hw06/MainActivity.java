package uncc.com.group25_hw06;

/*Assignment Name:Group25_HW06
Group 25
Full Names: Rosy Azad, Bhavya Gedi
 */

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener ,RegisterFragment.OnFragmentInteractionListener,CreateCourseFragment.OnFragmentInteractionListener,CourseManagerFragment.OnFragmentInteractionListener,AddInstructorFragment.OnFragmentInteractionListener,ListInstructorFragment.OnFragmentInteractionListener{

    static Realm realm;
    static String currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm =Realm.getDefaultInstance();


        getFragmentManager().beginTransaction().add(R.id.container, new LoginFragment(),"tag_login").commit();
    }

    @Override
    public void onFragmentInteraction(String userName, String password) {
        currentUser =userName;

        RealmResults<User> result = realm.where(User.class)
                .equalTo("username", userName)
                .equalTo("password", password)
                .findAllAsync();

        result.load();
        if(result.size() !=0){
            getFragmentManager().beginTransaction().replace(R.id.container, new CourseManagerFragment(), "tag_courseManager").addToBackStack(null)
                    .commit();
        }
        else{
            Toast.makeText(this, "Please enter valid username and password", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void goToRegisterFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, new RegisterFragment(), "tag_af5").addToBackStack(null)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onRegister(String profileString, String fname, String lname, String uname, String pwd) {
        saveToDb(profileString,fname,lname,uname,pwd);

    }

    private void saveToDb(final String profileString, final String fname, final String lname, final String uname, final String pwd){

        RealmResults<User> result = realm.where(User.class)
                .equalTo("username", uname)
                .findAllAsync();
        result.load();
        if (result.size() !=0) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            }

        else{


        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                User user = bgRealm.createObject(User.class);

                user.setFirstName(fname);
                user.setLastName(lname);
                user.setImage(profileString);
                user.setUsername(uname);
                user.setPassword(pwd);
            }

        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.d("demoSuccess","Success");
                Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.container, new CourseManagerFragment(), "tag_apploggedin").addToBackStack(null)
                        .commit();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.d("demoFailure","Failure");
            }
        });

    }}

    public  void deleteCourseFromDb(final Course course){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        Log.d("demoRealmDeleteMethod",course.toString());

        builder.setMessage("Are you sure, you want to delete the course?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final RealmResults<Course> results = realm.where(Course.class).equalTo("courseName",course.getCourseName()).findAll();

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                Log.d("demoRealmDelete",results.toString());
                                results.deleteFromRealm(0);
                                getFragmentManager().beginTransaction().replace(R.id.container, new CourseManagerFragment(), "tag_COurseMAnageragain").addToBackStack(null)
                                        .commit();


                            }
                        });
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

         AlertDialog dialog= builder.create();
         dialog.show();



    }

    @Override
    public void onFragmentInteraction(final String coursename, final String selectedDay, final String timeseq, final String credit, final String semester, final String fname) {

        RealmResults<Course> result = realm.where(Course.class)
                .equalTo("courseName",coursename)
                .findAllAsync();
        result.load();
            if (result.size() !=0) {
                Toast.makeText(this, "Course already exists!", Toast.LENGTH_SHORT).show();
            }

            else{
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Course course = bgRealm.createObject(Course.class);

                course.setCourseName(coursename);
                course.setUserName(currentUser);
                course.setDay(selectedDay);
                course.setTime(timeseq);
                course.setCreditHours(credit);
                course.setSemester(semester);
                course.setInstructor(fname);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.d("demoSuccess","Success");
                /*getFragmentManager().beginTransaction().replace(R.id.container, new CourseManagerFragment(), "tag_LoginManager2").addToBackStack(null)
                        .commit();*/
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.d("demoFailure","Failure");
            }
        });

    }}


    @Override
    public void gotoAddCourseFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, new CreateCourseFragment(), "tag_af2dd").addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoAddInsfrag() {
        getFragmentManager().beginTransaction().replace(R.id.container, new AddInstructorFragment(), "tag_af4").addToBackStack(null)
                .commit();
    }

    @Override
    public void getListInstructors() {
        getFragmentManager().beginTransaction().replace(R.id.container, new ListInstructorFragment() , "tag_af4asdadfad").addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoLogoutListener() {

        currentUser=null;
        getFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment(), "tag_af4").addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoCourseManagerfrag() {
        getFragmentManager().beginTransaction().replace(R.id.container, new CourseManagerFragment() , "tag_couser").addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoSaveInstructorProfile(final String fname, final String lname, final String email, final String website, final String profileString, final String returnuname) {
        RealmResults<Instructor> result = realm.where(Instructor.class)
                .equalTo("fname",fname)
                .equalTo("lname",lname)
                .findAllAsync();
        result.load();
        if (result.size() !=0) {
            Toast.makeText(this, "Instructor already exists!", Toast.LENGTH_SHORT).show();
        }

        else{
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    Instructor instructor = bgRealm.createObject(Instructor.class);

                    instructor.setFname(fname);
                    instructor.setLname(lname);
                    instructor.setEmail(email);
                    instructor.setWebsite(website);
                    instructor.setImg(profileString);
                    instructor.setUserName(returnuname);

                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    // Transaction was a success.
                    Log.d("demoSuccess","Success");
                    /*getFragmentManager().beginTransaction().replace(R.id.container, new CourseManagerFragment(), "tag_LoginManager2").addToBackStack(null)
                            .commit();*/
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    // Transaction failed and was automatically canceled.
                    Log.d("demoFailure","Failure");
                }
            });

        }
    }
}
