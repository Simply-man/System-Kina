package cinema.system.database.dao;

import cinema.system.database.models.Films;
import cinema.system.utils.expections.AppExpections;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;


import java.sql.SQLException;

public class FilmsDao extends CommonDao {

    public FilmsDao() {

        super();
    }
}
