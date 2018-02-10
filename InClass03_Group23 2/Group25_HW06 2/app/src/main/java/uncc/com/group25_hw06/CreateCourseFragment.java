package uncc.com.group25_hw06;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.RealmResults;



public class CreateCourseFragment extends Fragment {
    RecyclerView rv;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;

    ArrayList<Instructor> instructors;
    TextView noitems;

    static int position;
    String credit ="";

    private OnFragmentInteractionListener mListener;

    public CreateCourseFragment() {
        // Required empty public constructor
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
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_create_course, container, false);

       // dm = new InstructorDBManager(getActivity());
        //instructors = (ArrayList<Instructor>) dm.getAllNotes(MainActivity.returnuname());
        //Log.d("demomsg", "" + instructors.toString());
        /*ListViewAdapter cadapt;
        cadapt = new ListViewAdapter(getActivity(), msgs,dm);
        pwd.setAdapter(cadapt);*/

        /*Instructor instructor =new Instructor();
        instructor.setInstructorName("a");
        instructor.setLname("a");
        instructor.setEmail("a");
        instructor.setWebsite("a");
        instructor.setInstructorName("a");

        instructors.add(instructor);*/

        RealmResults<Instructor> result = MainActivity.realm.where(Instructor.class)
                .equalTo("userName",MainActivity.currentUser)
                .findAllAsync();
        result.load();

        //instructors = (ArrayList<Instructor>) result.toArray();
        instructors=new ArrayList<>(result);

        rv = (RecyclerView) view.findViewById(R.id.recycler);
        noitems = (TextView) view.findViewById(R.id.noitems);
        noitems.setVisibility(View.INVISIBLE);
        mlayoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(mlayoutmanager);
        rv.setHasFixedSize(true);
        setRetainInstance(true);

        madapter = new InstructorAdapter(instructors, new InstructorAdapter.RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                //v.findViewById(R.id.radioButtonInst).

                CreateCourseFragment.position=position;
                Log.d("clicked pos",""+position);
            }
        });

       /* madapter = new InstructorAdapter(instructors, new InstructorAdapter.itemListener() {
            @Override
            public void View.OnClickListener(View v, int position) {
                CreateCourseFragment.position=position;
                Log.d("clicked pos",""+position);
            }
        });*/
        if(instructors.size()>0){
            //rv.setVisibility(View.INVISIBLE);
            rv.setAdapter(madapter);
            view.findViewById(R.id.btnCreate).setEnabled(true);
            //setRetainInstance(true);
        }
        else
        {
            noitems.setVisibility(View.VISIBLE);
            view.findViewById(R.id.btnCreate).setEnabled(false);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
        final Spinner dayspin= (Spinner) getView().findViewById(R.id.dayspin);
        final EditText courseName= (EditText) getView().findViewById(R.id.textcoursename);
        final EditText time1= (EditText) getView().findViewById(R.id.texttime1);

        time1.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "12")});
        final EditText time2= (EditText) getView().findViewById(R.id.txttime2);
        time2.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "59")});
        final Spinner am_pm= (Spinner) getView().findViewById(R.id.spinampm);
        final RadioGroup radioGroup = (RadioGroup) getView().findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                if(radioGroup.getCheckedRadioButtonId() !=-1)
                credit = radioButton.getText().toString();

            }
        });
        // final RadioButton radioButton = (RadioButton)getView().findViewById(radioGroup.getCheckedRadioButtonId());
        final Spinner semester= (Spinner) getView().findViewById(R.id.spinsem);

        final String selectedDay=dayspin.getSelectedItem().toString();
        final String timeseq=time1.getText().toString()+":"+time2.getText().toString()+" "+am_pm.getSelectedItem().toString();
        final String course=courseName.getText().toString();
        // String credit=radioButton.getText().toString();
        final String sem=semester.getSelectedItem().toString();

        getView().findViewById(R.id.btnreset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time1.setText("");
                time2.setText("");
                courseName.setText("");
                //position =-1;

                radioGroup.clearCheck();
                madapter = new InstructorAdapter(instructors, new InstructorAdapter.RecyclerViewClickListener() {
                    @Override
                    public void recyclerViewListClicked(View v, int position) {
                        //v.findViewById(R.id.radioButtonInst).

                        CreateCourseFragment.position=position;
                        Log.d("clicked pos",""+position);
                    }
                });
                if(instructors.size()>0){
                    //rv.setVisibility(View.INVISIBLE);
                    rv.setAdapter(madapter);
                    getView().findViewById(R.id.btnCreate).setEnabled(true);
                    //setRetainInstance(true);
                }
                else
                {
                    noitems.setVisibility(View.VISIBLE);
                    getView().findViewById(R.id.btnCreate).setEnabled(false);
                }
            }
        });

        getView().findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner semester= (Spinner) getView().findViewById(R.id.spinsem);
                String selectedDay=dayspin.getSelectedItem().toString();
                String timeseq=time1.getText().toString()+time2.getText().toString()+am_pm.getSelectedItem().toString();
                String course=courseName.getText().toString();
                // String credit=radioButton.getText().toString();
                String sem=semester.getSelectedItem().toString();
                if(course.equals(null) || course.equals("")){
                    Toast.makeText(getActivity(), "Please enter the course name", Toast.LENGTH_SHORT).show();
                }
                else if(instructors.size()==0 ) {
                    Toast.makeText(getActivity(), "Please select the instructor", Toast.LENGTH_SHORT).show();

                }
                else if(time1.getText().toString().equals(null) || time1.getText().toString().equals("") ) {
                    Toast.makeText(getActivity(), "Please select the time stamp for hours", Toast.LENGTH_SHORT).show();

                }
                else if(time2.getText().toString().equals(null) ||time2.getText().toString().equals("") ) {
                    Toast.makeText(getActivity(), "Please select the time stamp for minutes", Toast.LENGTH_SHORT).show();

                }
                /*else if(credit.equals("") || credit.equals(null)){
                    Toast.makeText(getActivity(), "Please select the credit hours", Toast.LENGTH_SHORT).show();
                }*/
                else if(radioGroup.getCheckedRadioButtonId() ==-1)
                {
                    Toast.makeText(getActivity(), "Please select the credit hours", Toast.LENGTH_SHORT).show();
                }
                /*else if(position == -1){
                    Toast.makeText(getActivity(), "Please select the instructor", Toast.LENGTH_SHORT).show();
                }*/
                else
                {
                    mListener.onFragmentInteraction(course, selectedDay, timeseq, credit,
                            sem, instructors.get(position).getFname());
                    Toast.makeText(getActivity(), "Course successfully created", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   /* // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String coursename, String selectedDay, String timeseq, String credit, String semester, String fname);
        void gotoAddInsfrag();
        void getListInstructors();
        void gotoLogoutListener();
        void gotoCourseManagerfrag();
    }
    private class InputFilterMinMax implements InputFilter {private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
