package cinema.system.modelFx;

import cinema.system.database.dao.FilmsDao;
import cinema.system.database.models.Films;
import cinema.system.utils.converters.ConverterFilm;
import cinema.system.utils.expections.AppExpections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ListFilmsModel {

    private ObservableList<FilmFx> filmFxObservableList = FXCollections.observableArrayList();

    public void init() throws AppExpections {
        FilmsDao filmsDao = new FilmsDao();
        List<Films> films = filmsDao.queryForAll(Films.class);
        filmFxObservableList.clear();
        films.forEach(film ->{
            this.filmFxObservableList.add(ConverterFilm.convertToFilmFx(film));
        } );
    }

    public ObservableList<FilmFx> getFilmFxObservableList() {
        return filmFxObservableList;
    }

    public void setFilmFxObservableList(ObservableList<FilmFx> filmFxObservableList) {
        this.filmFxObservableList = filmFxObservableList;
    }

    public void deleteFilm(FilmFx filmFx) throws AppExpections {
        FilmsDao filmsDao = new FilmsDao();
        filmsDao.deleteById(Films.class, filmFx.getId());
        init();
    }
}
