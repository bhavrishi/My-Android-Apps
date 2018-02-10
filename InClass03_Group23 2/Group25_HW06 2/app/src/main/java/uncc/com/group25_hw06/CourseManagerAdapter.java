package uncc.com.group25_hw06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.RealmResults;


public class CourseManagerAdapter extends RecyclerView.Adapter<CourseManagerAdapter.ViewHolder>{
    private ArrayList<Course> mdata;
String tempimg=null;Bitmap bitmap=null;
   // MainActivity context;
    //private DBManager dm;
    //InstructorDBManager dmins;
    CourseManagerAdapter(ArrayList<Course> mdata) {
        this.mdata = mdata;

       // this.dm = dm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_course_display, parent, false);
        Context context = parent.getContext();
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Course course = mdata.get(position);
        RealmResults<Instructor> result = MainActivity.realm.where(Instructor.class)
                .equalTo("userName",MainActivity.currentUser)
                .findAllAsync();
        result.load();

        ArrayList<Instructor> instructors=new ArrayList<>(result);
       // dmins=new InstructorDBManager(holder.hcontext);

       // ArrayList<Instructor> instructors= (ArrayList<Instructor>) dmins.getAllNotes(MainActivity.returnuname());
        for(int i=0;i<instructors.size();i++)
        {
            if(course.getInstructor().equals(instructors.get(i).getFname()))
            {
                tempimg=instructors.get(i).getImg();

            }
        }


        Log.d("demoadap",""+mdata.toString());
        Log.d("temp",""+tempimg);
if(tempimg!=null)
{
    try {
    byte [] encodeByte= Base64.decode(tempimg, Base64.DEFAULT);
     bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        Log.d("bitmap",""+bitmap);

} catch(Exception e) {
    e.getMessage();

}
}
        holder.title.setText(course.getCourseName());
        holder.ins.setText(course.getInstructor());

holder.time.setText(course.getDay() + " "+course.getTime());

        Log.d("demoin adapter", course.toString());

       holder.deleteButton.setImageBitmap(bitmap);

        holder.relativeLayoutCourseManager.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("demoLongClick", course.toString());
                mdata.remove(course);
                //notifyDataSetChanged();
                holder.hcontext.deleteCourseFromDb(course);
                return true;

            }
        });
        /*holder.relativeLayoutCourseManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demoLongClick", course.toString());
                mdata.remove(course);
                //notifyDataSetChanged();
                holder.hcontext.deleteCourseFromDb(course);
                //return true;
            }
        });
*/
        // Picasso.with(holder.hcontext).load().into(holder.deleteButton);


         holder.course = course;
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,ins,time;

        Course course;
        ImageView deleteButton;
        MainActivity hcontext;
        RelativeLayout relativeLayoutCourseManager;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
             title = (TextView) itemView.findViewById(R.id.lytitle);
            ins = (TextView) itemView.findViewById(R.id.lyinstructor);
            time = (TextView) itemView.findViewById(R.id.lytime);

            deleteButton = (ImageView) itemView.findViewById(R.id.iimg);
            hcontext = (MainActivity) context;
            relativeLayoutCourseManager = (RelativeLayout) itemView.findViewById(R.id.RLCourseDisplay);



            itemView.findViewById(R.id.iimg).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
           /*         Log.d("demo","clicked image "+getAdapterPosition());
                    Log.d("demo","clicked image "+mdata.get(0));
                    dm.deleteNote(mdata.get(getAdapterPosition()));
                    mdata.remove(getAdapterPosition());
                    notifyDataSetChanged();

            */
                }
            });
        }
    }
}
