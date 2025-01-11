package com.timj.c196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timj.c196.R;
import com.timj.c196.entities.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseNameItemView;
        private final TextView courseStartDateItemView;
        private final TextView courseEndDateItemView;
        private final TextView courseStatusItemView;
        private final TextView courseMentorNameItemView;
        private final TextView courseMentorPhoneItemView;
        private final TextView courseMentorEmailItemView;

        private CourseViewHolder(View itemview) {
            super(itemview);
            courseNameItemView = itemview.findViewById(R.id.textViewCourseName);
            courseStartDateItemView = itemview.findViewById(R.id.textViewCourseStartDate);
            courseEndDateItemView = itemview.findViewById(R.id.textViewCourseEndDate);
            courseStatusItemView = itemview.findViewById(R.id.textViewCourseStatus);
            courseMentorNameItemView = itemview.findViewById(R.id.textViewMentorName);
            courseMentorPhoneItemView = itemview.findViewById(R.id.textViewMentorPhone);
            courseMentorEmailItemView = itemview.findViewById(R.id.textViewMentorEmail);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, CourseDetails.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("status", current.getStatus());
                    intent.putExtra("courseName", current.getCourseName());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    intent.putExtra("mentorName", current.getMentorName());
                    intent.putExtra("mentorPhone", current.getMentorPhone());
                    intent.putExtra("mentorEmail", current.getMentorEmail());
                    intent.putExtra("courseNote", current.getCourseNote());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            String name = current.getCourseName();
            String startDate = current.getStartDate();
            String endDate = current.getEndDate();
            int status = current.getStatus();
            String mentorName = current.getMentorName();
            String mentorPhone = current.getMentorPhone();
            String mentorEmail = current.getMentorEmail();
            holder.courseNameItemView.setText(name);
            holder.courseStartDateItemView.setText(startDate);
            holder.courseEndDateItemView.setText(endDate);

            if(status == 0){
                holder.courseStatusItemView.setText("Dropped");
            } else if(status == 1) {
                holder.courseStatusItemView.setText("Planned");
            } else if(status == 2) {
                holder.courseStatusItemView.setText("In-Progress");
            } else if(status == 3) {
                holder.courseStatusItemView.setText("Completed");
            }


            holder.courseMentorNameItemView.setText(mentorName);
            holder.courseMentorPhoneItemView.setText(mentorPhone);
            holder.courseMentorEmailItemView.setText(mentorEmail);
        } else {
            holder.courseNameItemView.setText("No Course Name");
            holder.courseStartDateItemView.setText("No Course Start Date");
            holder.courseEndDateItemView.setText("No Course End Date");
            holder.courseStatusItemView.setText("No Course Status");
            holder.courseMentorNameItemView.setText("No Course Mentor Name");
            holder.courseMentorPhoneItemView.setText("No Course Mentor Phone Number");
            holder.courseMentorEmailItemView.setText("No Course Mentor Email");
        }
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public void setCourses(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }
}