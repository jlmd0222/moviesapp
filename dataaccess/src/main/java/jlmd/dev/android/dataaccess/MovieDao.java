package jlmd.dev.android.dataaccess;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.internal.SqlUtils;

import java.util.ArrayList;
import java.util.List;

public class MovieDao extends AbstractDao<Movie, Long> {

    public static final String TABLENAME = "MOVIE";

    /**
     * Properties of entity User.
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property IdMovie = new Property(1, int.class, "idMovie", false, "ID_MOVIE");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property OriginalTitle = new Property(3, String.class, "original_title", false, "ORIGINAL_TITLE");
        public final static Property OriginalLanguage = new Property(4, String.class, "original_language", false, "ORIGINAL_LANGUAGE");
        public final static Property Overview = new Property(5, String.class, "overview", false, "OVERVIEW");
        public final static Property PosterPath = new Property(6, String.class, "poster_path", false, "POSTER_PATH");
        public final static Property BackdropPath = new Property(7, String.class, "backdrop_path", false, "BACKDROP_PATH");
        public final static Property ReleaseDate = new Property(8, String.class, "release_date", false, "RELEASE_DATE");
        public final static Property VoteAverage = new Property(9, Double.class, "vote_average", false, "VOTE_AVERAGE");
        public final static Property Popularity = new Property(10, Double.class, "popularity", false, "POPULARITY");
        public final static Property VoteCount = new Property(11, Long.class, "vote_count", false, "VOTE_COUNT");
    }

    private DaoSession daoSession;

    public MovieDao(DaoConfig config) {
        super(config);
    }

    public MovieDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MOVIE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ID_MOVIE\" INTEGER NOT NULL ," + // 1: idMovie
                "\"TITLE\" TEXT NOT NULL ," + // 2: title
                "\"ORIGINAL_TITLE\" TEXT NOT NULL ," + // 3: original_title
                "\"ORIGINAL_LANGUAGE\" TEXT NOT NULL ," + // 4: original_language
                "\"OVERVIEW\" TEXT NOT NULL ," + // 5: overview
                "\"POSTER_PATH\" TEXT NOT NULL ," + // 6: poster_path
                "\"BACKDROP_PATH\" TEXT NOT NULL ," + // 7: backdrop_path
                "\"RELEASE_DATE\" TEXT NOT NULL ," + // 8: release_date
                "\"VOTE_AVERAGE\" INTEGER  ," + // 9: vote_average
                "\"POPULARITY\" INTEGER," + // 10: popularity
                "\"VOTE_COUNT\" INTEGER);"); // 11: vote_count
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MOVIE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Movie entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIdMovie());
        stmt.bindString(3, entity.getTitle());
        stmt.bindString(4, entity.getOriginalTitle());
        stmt.bindString(5, entity.getOriginalLanguage());
        stmt.bindString(6, entity.getOverview());
        stmt.bindString(7, entity.getPosterPath());
        stmt.bindString(8, entity.getBackdropPath());
        stmt.bindString(9, entity.getReleaseDate());
        stmt.bindDouble(10, entity.getVoteAverage());
        stmt.bindDouble(11, entity.getPopularity());
        stmt.bindLong(12, entity.getVoteCount());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Movie entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIdMovie());
        stmt.bindString(3, entity.getTitle());
        stmt.bindString(4, entity.getOriginalTitle());
        stmt.bindString(5, entity.getOriginalLanguage());
        stmt.bindString(6, entity.getOverview());
        stmt.bindString(7, entity.getPosterPath());
        stmt.bindString(8, entity.getBackdropPath());
        stmt.bindString(9, entity.getReleaseDate());
        stmt.bindDouble(10, entity.getVoteAverage());
        stmt.bindDouble(11, entity.getPopularity());
        stmt.bindLong(12, entity.getVoteCount());
    }

    @Override
    protected final void attachEntity(Movie entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public Movie readEntity(Cursor cursor, int offset) {
        Movie entity = new Movie( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getLong(offset + 1), // idMovie
                cursor.getString(offset + 2), // title
                cursor.getString(offset + 3), // original_title
                cursor.getString(offset + 4), // original_language
                cursor.getString(offset + 5), // overview
                cursor.getString(offset + 6), // poster_path
                cursor.getString(offset + 7), // backdrop_path
                cursor.getString(offset + 8), // release_date
                cursor.getDouble(offset + 9), // vote_average
                cursor.getDouble(offset + 10), // popularity
                cursor.getLong(offset + 11) // vote_count
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, Movie entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIdMovie(cursor.getLong(offset + 1));
        entity.setTitle(cursor.getString(offset + 2));
        entity.setOriginalTitle(cursor.getString(offset + 3));
        entity.setOriginalLanguage(cursor.getString(offset + 4));
        entity.setOverview(cursor.getString(offset + 5));
        entity.setPosterPath(cursor.getString(offset + 6));
        entity.setBackdropPath(cursor.getString(offset + 7));
        entity.setReleaseDate(cursor.getString(offset + 8));
        entity.setVoteAverage(cursor.getDouble(offset + 9));
        entity.setPopularity(cursor.getDouble(offset + 10));
        entity.setVoteCount(cursor.getLong(offset + 11));
    }

    @Override
    protected final Long updateKeyAfterInsert(Movie entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(Movie entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Movie entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getMovieDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getMovieDao().getAllColumns());
            builder.append(" FROM MOVIE T");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }

    protected Movie loadCurrentDeep(Cursor cursor, boolean lock) {
        Movie entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Movie user = loadCurrentOther(daoSession.getMovieDao(), cursor, offset);
        //entity.setMovie(user);
        offset += daoSession.getMovieDao().getAllColumns().length;

        return entity;
    }

    public Movie loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();

        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);

        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }

    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Movie> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Movie> list = new ArrayList<>(count);

        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }

    protected List<Movie> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }


    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Movie> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
}
