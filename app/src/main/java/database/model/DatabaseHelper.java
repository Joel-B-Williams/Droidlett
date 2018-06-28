package database.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create users table
        db.execSQL(User.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        // Create new tables
        onCreate(db);
    }

    public long insertUser(String email, String password) {
        // get writable db
        SQLiteDatabase db = this.getWritableDatabase();

        // ContentValues() defines the column name and data to be stored
        ContentValues values = new ContentValues();
        // id & timestamp added automatically
        values.put(User.COLUMN_EMAIL, email);
        values.put(User.COLUMN_PASSWORD, password);

        // insert row
        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted id
        return id;
    }

    public User getUser(String email) {
        // get readable db
        SQLiteDatabase db = this.getReadableDatabase();

        /* a Cursor is the object that contains the db query result in Android
            has its own API
            Below is example of sterlized query
            db.query(USER_TABLE_NAME, ALL_COLUMNS, "user_id = ?", new String[] {userId}, null, null, null);
        */
        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_EMAIL, User.COLUMN_PASSWORD, User.COLUMN_TIMESTAMP},
                User.COLUMN_EMAIL + "=?",
                new String[]{String.valueOf(email)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare User object
        User user = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_TIMESTAMP)));

        // close db
        cursor.close();

        return user;
    }
}
