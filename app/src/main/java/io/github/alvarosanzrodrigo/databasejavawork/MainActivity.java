package io.github.alvarosanzrodrigo.databasejavawork;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SubjectRoomDatabase.getDatabase(this).subjectDao().getAllWords().observe(this, new Observer<List<Subject>>() {

            @Override
            public void onChanged(@Nullable List<Subject> subjects) {

                if (subjects != null) {

                    Log.d("Database", subjects.toString());
                    for (Subject subject : subjects) {
                        System.out.println(subject.getName());
                    }

                }

            }

        });
    }
}
