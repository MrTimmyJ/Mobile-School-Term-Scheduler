package com.timj.c196.UI;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.timj.c196.Database.C196Repository;
import com.timj.c196.R;
import com.timj.c196.entities.Assessment;
import com.timj.c196.entities.Course;
import com.timj.c196.entities.Term;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);

        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{POST_NOTIFICATIONS}, 1);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, TermList.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addSampleData) {
            C196Repository repository = new C196Repository(getApplication());
            Term term = new Term(0, "fall", "09/15/2023", "11/15/2023");
            repository.insert(term);

            Course course = new Course(0, 1, 1, "Math", "09/15/2023", "11/15/2023", "Alberto", "555-555-5555", "alberto@email.com", repository.getAllTerms().toString());
            repository.insert(course);

            Assessment assessment = new Assessment(0, 1, "test", "09/15/2023", "11/15/2023", 0);
            repository.insert(assessment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}