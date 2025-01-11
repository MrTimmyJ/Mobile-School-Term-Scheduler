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
import com.timj.c196.entities.Assessment;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentNameItemView;
        private final TextView assessmentStartDateItemView;
        private final TextView assessmentEndDateItemView;
        private final TextView assessmentTypeItemView;

        private AssessmentViewHolder(View itemview) {
            super(itemview);
            assessmentNameItemView = itemview.findViewById(R.id.textViewAssessmentName);
            assessmentStartDateItemView = itemview.findViewById(R.id.textViewAssessmentStartDate);
            assessmentEndDateItemView = itemview.findViewById(R.id.textViewAssessmentEndDate);
            assessmentTypeItemView = itemview.findViewById(R.id.textViewAssessmentType);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetails.class);
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("assessmentName", current.getAssessmentName());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    intent.putExtra("type", current.getType());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            Assessment current = mAssessments.get(position);
            String name = current.getAssessmentName();
            String startDate = current.getStartDate();
            String endDate = current.getEndDate();
            int type = current.getType();
            holder.assessmentNameItemView.setText(name);
            holder.assessmentStartDateItemView.setText(startDate);
            holder.assessmentEndDateItemView.setText(endDate);
            if (type == 0) {
                holder.assessmentTypeItemView.setText("OA");
            } else if (type == 1) {
                holder.assessmentTypeItemView.setText("PA");
            }


        } else {
            holder.assessmentNameItemView.setText("No Assessment Name");
            holder.assessmentStartDateItemView.setText("No Assessment Start Date");
            holder.assessmentEndDateItemView.setText("No Assessment End Date");
            holder.assessmentTypeItemView.setText("No Assessment Type");
        }
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

    public void setAssessments(List<Assessment> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }
}