package com.timj.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.timj.c196.Database.C196Repository;
import com.timj.c196.R;
import com.timj.c196.entities.Course;
import com.timj.c196.entities.Term;

import java.util.ArrayList;
import java.util.List;

public class TermList extends AppCompatActivity {

    int id;

    private C196Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        repository = new C196Repository(getApplication());

        // if no terms in recycler view then show text saying " Click + to add term"

        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Term> allTerms = repository.getAllTerms();
        termAdapter.setTerms(allTerms);

//        List<Term> filteredTerms = new ArrayList<>();
//        for (Term t : repository.getAllTerms()){
//            if(t.getTermID() == id) {
//                filteredTerms.add(t);
//            }
//        }
//        termAdapter.setTerms(filteredTerms);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermList.this, TermDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Term> allTerms = repository.getAllTerms();
        termAdapter.setTerms(allTerms);
    }
}