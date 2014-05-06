package org.fredy.learningandroid;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    private DBHelper dbHelper;
    private static final UriMatcher uriMatcher = new UriMatcher(
        UriMatcher.NO_MATCH);
    
    static {
        uriMatcher.addURI(Constants.AUTHORITY, Constants.TABLE, Constants.DIR);
        uriMatcher.addURI(Constants.AUTHORITY, Constants.TABLE + "/#", Constants.ITEM);
    }
    
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret = db.delete(Constants.TABLE, Constants.Column.ROW_ID + " = ?",
            selectionArgs);
        if (ret > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return ret;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
        case Constants.DIR:
            return Constants.TYPE_DIR;
        case Constants.ITEM:
            return Constants.TYPE_ITEM;
        default:
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri ret = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insertWithOnConflict(Constants.TABLE, null, values,
            SQLiteDatabase.CONFLICT_IGNORE);
        if (rowId != -1) {
            ret = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return ret;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE,
            new String[] {
                Constants.Column.ROW_ID + " as " + Constants.Column.ID,
                Constants.Column.MESSAGE
            },
            selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
        String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret = db.update(Constants.TABLE, values, Constants.Column.ROW_ID + " = ?",
            selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return ret;
    }
}
