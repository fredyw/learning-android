package org.fredy.learningandroid;

import android.net.Uri;
import android.provider.BaseColumns;

public class Constants {
    public static final String AUTHORITY = "org.fredy.learningandroid.MyContentProvider";
    public static final String TABLE = "mytable";
    public static final int ITEM = 1;
    public static final int DIR = 2;
    public static final String TYPE_ITEM = "vnd.android.cursor.item/vnd.org.learndingandroid";
    public static final String TYPE_DIR = "vnd.android.cursor.dir/vnd.org.learndingandroid";
    public static final Uri URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE);
    
    public static class Column {
        public static final String ROW_ID = "rowid";
        public static final String ID = BaseColumns._ID;
        public static final String MESSAGE = "message";
    }
}
