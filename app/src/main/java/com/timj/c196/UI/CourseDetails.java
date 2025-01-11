package com.timj.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.timj.c196.Database.C196Repository;
import com.timj.c196.R;
import com.timj.c196.entities.Assessment;
import com.timj.c196.entities.Course;
import com.timj.c196.entities.Term;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseDetails extends AppCompatActivity {

    EditText editName;
    EditText editStartDate;
    EditText editEndDate;
    EditText editMentorName;
    EditText editMentorPhone;
    EditText editMentorEmail;
    EditText editNote;
    TextView statusOfCourse;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();
    int id;
    int termID;
    String curNote;
    String courseNote;
    String finalCourseNote;
    public static int status;
    String name;
    String mentorName;
    String mentorPhone;
    String mentorEmail;
    Course course;
    Course currentCourse;
    int numAssessments;
    C196Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        repository = new C196Repository(getApplication());

        // Get status from spinner
        editName = findViewById(R.id.courseName);
        editStartDate = findViewById(R.id.courseStartDate);
        editEndDate = findViewById(R.id.courseEndDate);
        editMentorName = findViewById(R.id.courseMentorName);
        editMentorPhone = findViewById(R.id.courseMentorPhone);
        editMentorEmail = findViewById(R.id.courseMentorEmail);
        editNote = findViewById(R.id.courseNote);
        statusOfCourse = findViewById(R.id.courseStatus);

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        id = getIntent().getIntExtra("courseID", -1);
        termID = getIntent().getIntExtra("termID", -1);
        status = getIntent().getIntExtra("status", -1);
        name = getIntent().getStringExtra("courseName");
        mentorName = getIntent().getStringExtra("mentorName");
        mentorPhone = getIntent().getStringExtra("mentorPhone");
        mentorEmail = getIntent().getStringExtra("mentorEmail");
        status = getIntent().getIntExtra("status", -1);
        curNote = getIntent().getStringExtra("courseNote");

        if(id == -1) {
            editStartDate.setText(sdf.format(new Date()));
            editEndDate.setText(sdf.format(new Date()));
        } else {
            editStartDate.setText(getIntent().getStringExtra("startDate"));
            editEndDate.setText(getIntent().getStringExtra("endDate"));
        }

        editName.setText(name);
        editMentorName.setText(mentorName);
        editMentorPhone.setText(mentorPhone);
        editMentorEmail.setText(mentorEmail);

        List<String> courseStatusList = new ArrayList<String>();
        courseStatusList.add("Dropped");
        courseStatusList.add("Planned");
        courseStatusList.add("In-Progress");
        courseStatusList.add("Completed");
        if(status == 0) {
            statusOfCourse.setText(courseStatusList.get(0));
        } else if(status == 1) {
            statusOfCourse.setText(courseStatusList.get(1));
        } else if(status == 2) {
            statusOfCourse.setText(courseStatusList.get(2));
        } else if(status == 3) {
            statusOfCourse.setText(courseStatusList.get(3));
        }

        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        List<Assessment> allAssessments = repository.getAllAssessments();
//        assessmentAdapter.setAssessments(allAssessments);
        List<Assessment> filteredAssessments = new ArrayList<>();
        for (Assessment a : repository.getAllAssessments()){
            if(a.getCourseID() == id) {
                filteredAssessments.add(a);
            }
        }
        assessmentAdapter.setAssessments(filteredAssessments);

        List<String> allCourseNotes = new ArrayList<String>();

        if(curNote == null) {
            editNote.setHint("Enter New Note");
        } else {
            courseNote = editNote.getText().toString();
            if(courseNote.matches("")) {
                courseNote.replace(",", "");
            } else {
                finalCourseNote = curNote + "," + courseNote;
                curNote = finalCourseNote;
            }
            String str[] = curNote.split(",");
            allCourseNotes = Arrays.asList(str);
        }

        Spinner spinner = findViewById(R.id.courseSpinner);
        ArrayAdapter<String> noteArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allCourseNotes);
        spinner.setAdapter(noteArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                editNote.setText(noteArrayAdapter.getItem(i));
                if(editNote.getText().toString().matches("")) {
                    editNote.setHint("Enter New Note");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                editNote.setText("Nothing Selected");
            }
        });

        //Create spinner for status? shows list of each status and then displays current one?
        Spinner courseStatusSpinner = findViewById(R.id.courseStatusSpinner);
        ArrayAdapter<String> courseStatusArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseStatusList);
        courseStatusSpinner.setAdapter(courseStatusArrayAdapter);
        courseStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String statusString = courseStatusArrayAdapter.getItem(i);
                statusOfCourse.setText(statusString);
                if(statusString.equals("Dropped")){
                    status = 0;
                } else if(statusString.equals("Planned")) {
                    status = 1;
                } else if(statusString.equals("In-Progress")) {
                    status = 2;
                } else if(statusString.equals("Completed")) {
                    status = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                statusOfCourse.setText("Nothing Selected");
            }
        });

        Button button = findViewById(R.id.saveCourse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id == -1) {

                    if(curNote == null) {
                        editNote.setHint("Enter New Note");
                    } else {
                        //courseNote = editNote.getText().toString() + "," + curNote;
                        courseNote = editNote.getText().toString();
                        //courseNote = "test";
                        if(courseNote.matches("")) {
                            courseNote.replace(",", "");
                        } else {
                            if(curNote.matches("")){
                                finalCourseNote = courseNote;
                                curNote = finalCourseNote;
                            } else {
                                finalCourseNote = curNote + "," + courseNote;
                                curNote = finalCourseNote;
                            }
                        }
                    }

                    course = new Course(0, termID, status, editName.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), editMentorName.getText().toString(), editMentorPhone.getText().toString(), editMentorEmail.getText().toString(), editNote.getText().toString());
                    repository.insert(course);
                    //Toast.makeText(this, "Term is saved", Toast.LENGTH_LONG).show();
                } else {

                    if(curNote == null) {
                        editNote.setHint("Enter New Note");
                    } else {
                        //courseNote = editNote.getText().toString() + "," + curNote;
                        courseNote = editNote.getText().toString();
                        //courseNote = "test";
                        if(courseNote.matches("")) {
                            courseNote.replace(",", "");
                        } else {
                            if(curNote.matches("")){
                                finalCourseNote = courseNote;
                                curNote = finalCourseNote;
                            } else {
                                finalCourseNote = curNote + "," + courseNote;
                                curNote = finalCourseNote;
                            }
                        }
                    }

                    course = new Course(id, termID, status, editName.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), editMentorName.getText().toString(), editMentorPhone.getText().toString(), editMentorEmail.getText().toString(), curNote);
                    repository.update(course);
                    //Toast.makeText(this, "Term is updated", Toast.LENGTH_LONG).show();
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton3);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetails.this, AssessmentDetails.class);
                intent.putExtra("courseID", id);
                startActivity(intent);
            }
        });
        editStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info = editStartDate.getText().toString();
                if (info.equals("")) info = "02/10/22";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        editEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info = editEndDate.getText().toString();
                if (info.equals("")) info = "02/10/22";
                try {
                    myCalendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, endDate, myCalendarEnd
                        .get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelStart();
            }

        };
        endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelEnd();
            }
        };
    }

    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        List<Assessment> allAssessments = repository.getAllAssessments();
//        assessmentAdapter.setAssessments(allAssessments);

        if(status == 0) {
            statusOfCourse.setText("Dropped");
        } else if(status == 1) {
            statusOfCourse.setText("Planned");
        } else if(status == 2) {
            statusOfCourse.setText("In-Progress");
        } else if(status == 3) {
            statusOfCourse.setText("Completed");
        }
        List<Assessment> fileredAssessments = new ArrayList<>();
        for (Assessment a : repository.getAllAssessments()) {
            if (a.getCourseID() == id)
                fileredAssessments.add(a);
        }
        assessmentAdapter.setAssessments(fileredAssessments);

        //Toast.makeText(ProductDetails.this,"refresh list",Toast.LENGTH_LONG).show();
    }

    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editStartDate.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editEndDate.setText(sdf.format(myCalendarEnd.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            this.finish();
            return true;
        } else if (itemId == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
            sendIntent.putExtra(Intent.EXTRA_TITLE, "Message Title");
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return true;
        } else if (itemId == R.id.notifystart) {
            String dateFromScreen = editStartDate.getText().toString();
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger = myDate.getTime();
            Intent intent = new Intent(CourseDetails.this, MyReceiver.class);
            intent.putExtra("key", dateFromScreen + " should trigger");
            PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
            return true;
        } else if (itemId == R.id.notifyend) {
            String dateFromScreen = editEndDate.getText().toString();
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger = myDate.getTime();
            Intent intent = new Intent(CourseDetails.this, MyReceiver.class);
            intent.putExtra("key", dateFromScreen + " should trigger");
            PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
            return true;
        } else if (item.getItemId() == R.id.deleteCourse) {
            for (Course c : repository.getAllCourses()) {
                if (c.getCourseID() == id) currentCourse = c;
            }

            numAssessments = 0;
            for (Assessment a : repository.getAllAssessments()) {
                if (a.getCourseID() == id) ++numAssessments;
            }

            if (numAssessments == 0) {
                repository.delete(currentCourse);
                Toast.makeText(CourseDetails.this, currentCourse.getCourseName() + " was deleted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(CourseDetails.this, "Can't delete a course with assessments", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}