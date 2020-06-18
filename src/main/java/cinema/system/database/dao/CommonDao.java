package cinema.system.database.dao;

import cinema.system.database.models.Category;
import cinema.system.utils.expections.AppExpections;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import cinema.system.database.dbuitls.DbManager;
import cinema.system.database.models.BaseModel;
import cinema.system.utils.FxmlUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// abstrakcyjna klasy odpowiadająca za dodawanie danych do bazy
public abstract class CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected final ConnectionSource connectionSource;

    public CommonDao() {
        this.connectionSource = DbManager.getConnectionSource();
    }

    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel) throws AppExpections {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            // wyjatek podczas wprowadzania tej samej nazwy
            throw new AppExpections("Nie mozna wprowadzic tej samej kategorii");
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws AppExpections {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new AppExpections("Nie mozna odswiezyc");
        }finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws AppExpections {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new AppExpections("Nie mozna usunac");
        }finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id) throws AppExpections {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new AppExpections("Nie mozna usunac");
        }finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws AppExpections {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new AppExpections("Nie mozna znalezc wpisu");
        }finally {
            this.closeDbConnection();
        }
    }


    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws AppExpections {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new AppExpections("Nie mozna znalezc wpisow");
        }finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws AppExpections {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new AppExpections("Nie mozna nawiazac polaczenia z baza danych");
        }finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls) throws AppExpections {
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }

    private void closeDbConnection()  {
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
