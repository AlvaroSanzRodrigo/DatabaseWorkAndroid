package io.github.alvarosanzrodrigo.databasejavawork;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Subject.class}, version = 1)
public abstract class SubjectRoomDatabase extends RoomDatabase {

    public abstract SubjectDao subjectDao();
    private static SubjectRoomDatabase INSTANCE;

    static SubjectRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SubjectRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SubjectRoomDatabase.class, "subject_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final SubjectDao mDao;
        String[] names = {"Data Access",
                "Programming",
                "Technical English",
                "PSPR",
                "SGEM",
                "Interfaces Development",
                "EIEM",
                "Generic Subject 1",
                "Generic Subject 2",
                "Generic Subject 3"};

        int[] subjectId = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        String[] teachers = {"Jaime Lanister",
                "David Jardon",
                "Pedro Camacho",
                "Carlos From iOs",
                "Generic Teacher 1",
                "Generic Teacher 2",
                "Generic Teacher 3",
                "Generic Teacher 4",
                "Generic Teacher 5",
                "Generic Teacher 6"};
        int[] nStudents = {20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

        int[] hours = {31, 31, 31, 31, 31, 31, 31, 31, 31, 31};


        PopulateDbAsync(SubjectRoomDatabase db) {
            mDao = db.subjectDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for (int i = 0; i <= names.length - 1; i++) {
                Subject subject = new Subject(names[i], subjectId[i], teachers[1], nStudents[1], hours[1]);
                mDao.insert(subject);
            }
            return null;
        }
    }
}

