package pl.nataliana.foreignersinbydgoszcz.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class TaskContract {

    public static final String CONTENT_AUTHORITY = "pl.nataliana.foreignersinbydgoszcz";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PRODUCT = "myTasks";

    // private constructor can be empty
    private TaskContract() {
    }

    public static class TaskEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCT);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;

        public static final String TABLE_NAME = "myTasks";
        public static final String _ID = BaseColumns._ID;
        public static final String KEY_TASKNAME = "taskName";
        public static final String KEY_STATUS = "status";
    }
}
