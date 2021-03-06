package jlmd.dev.android.dataaccess;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

/**
 * {@inheritDoc}
 *
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {
    private final DaoConfig movieDaoConfig;

    private final MovieDao movieDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        movieDaoConfig = daoConfigMap.get(MovieDao.class).clone();
        movieDaoConfig.initIdentityScope(type);

        movieDao = new MovieDao(movieDaoConfig, this);

        registerDao(Movie.class, movieDao);
    }

    public void clear() {
        movieDaoConfig.clearIdentityScope();
    }

    public MovieDao getMovieDao() {
        return movieDao;
    }
}
