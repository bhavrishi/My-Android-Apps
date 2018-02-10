package com.hw.uncc.group25_hw06;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.name;


public class CreateCourseFragment extends Fragment {
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;
    ArrayList<Instructor> instructors;  TextView noitems;
    InstructorAdapter.RecyclerViewClickListener mlistner;
    RecyclerView rv;
   private OnFragmentInteractionListener mListener;
    InstructorDBManager dm;
    int position;String credit;
    public CreateCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_create_course, container, false);
        //final ListView pwd=view.findViewById(R.id.rv);


        // msgs=MainActivity.returnlist();

        dm = new InstructorDBManager(getActivity());
        instructors = (ArrayList<Instructor>) dm.getAllNotes(MainActivity.returnuname());
        Log.d("demomsg", "" + instructors.toString());
        /*ListViewAdapter cadapt;
        cadapt = new ListViewAdapter(getActivity(), msgs,dm);
        pwd.setAdapter(cadapt);*/

        rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        noitems = (TextView) view.findViewById(R.id.noitems);
        noitems.setVisibility(View.INVISIBLE);
        mlayoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(mlayoutmanager);
        madapter = new InstructorAdapter(instructors, dm, new InstructorAdapter.RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                position=position;
                Log.d("clicked pos",""+position);
            }
        });
if(instructors.size()>0){
    //rv.setVisibility(View.INVISIBLE);
    rv.setAdapter(madapter);
    setRetainInstance(true);
}
      else
{
    noitems.setVisibility(View.VISIBLE);
}
        return view;
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




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String coursename, String selectedDay, String timeseq, String credit, String semester, String fname);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
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
                 credit = radioButton.getText().toString();

            }
        });
     // final RadioButton radioButton = (RadioButton)getView().findViewById(radioGroup.getCheckedRadioButtonId());
        final Spinner semester= (Spinner) getView().findViewById(R.id.spinsem);

        getView().findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
String selectedDay=dayspin.getSelectedItem().toString();
                String timeseq=time1.getText().toString()+time2.getText().toString()+am_pm.getSelectedItem().toString();
                String course=courseName.getText().toString();
               // String credit=radioButton.getText().toString();
                String sem=semester.getSelectedItem().toString();
                mListener.onFragmentInteraction(course,selectedDay,timeseq,credit,
                       sem ,instructors.get(position).getFname());
            }
        });

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
