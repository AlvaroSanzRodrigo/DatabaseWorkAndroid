package io.github.alvarosanzrodrigo.databasejavawork;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class SubjectRepository {

    private SubjectDao mSubjectDao;
    private LiveData<List<Subject>> mAllSubjects;

    SubjectRepository(Application application) {
        SubjectRoomDatabase db = SubjectRoomDatabase.getDatabase(application);
        mSubjectDao = db.subjectDao();
        mAllSubjects = mSubjectDao.getAllWords();
    }

    LiveData<List<Subject>> getAllWords() {
        return mAllSubjects;
    }

    public void insert (Subject word) {

        new insertAsyncTask(mSubjectDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Subject, Void, Void> {

        private SubjectDao mAsyncTaskDao;

        insertAsyncTask(SubjectDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Subject... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}