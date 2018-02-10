package uncc.com.group25_hw06;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListInstructorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ListInstructorFragment extends Fragment {
    RecyclerView rv;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;

    ArrayList<Instructor> instructors;
    TextView noitems;

    private OnFragmentInteractionListener mListener;

    public ListInstructorFragment() {
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
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list_instructor, container, false);

        RealmResults<Instructor> result = MainActivity.realm.where(Instructor.class)
                .equalTo("userName",MainActivity.currentUser)
                .findAllAsync();
        result.load();
        instructors=new ArrayList<>(result);

        rv = (RecyclerView) view.findViewById(R.id.recycleViewInsListFrag);
        noitems = (TextView) view.findViewById(R.id.textViewnoinst);
        noitems.setVisibility(View.INVISIBLE);
        mlayoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mlayoutmanager);
        rv.setHasFixedSize(true);
        setRetainInstance(true);
        madapter =new DetailedInsAdapter(instructors);

        /*madapter = new InstructorAdapter(instructors, new InstructorAdapter.RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                CreateCourseFragment.position=position;
                Log.d("clicked pos",""+position);
            }
        });*/
        if(instructors.size()>0){
            //rv.setVisibility(View.INVISIBLE);
            rv.setAdapter(madapter);
            //view.findViewById(R.id.btnCreate).setEnabled(true);
            //setRetainInstance(true);
        }
        else
        {
            noitems.setVisibility(View.VISIBLE);
            //view.findViewById(R.id.btnCreate).setEnabled(false);
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
        //void onFragmentInteraction(Uri uri);
        void gotoAddInsfrag();
        void getListInstructors();
        void gotoLogoutListener();
        void gotoCourseManagerfrag();
    }
}
