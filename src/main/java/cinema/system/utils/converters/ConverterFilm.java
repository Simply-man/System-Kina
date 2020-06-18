package cinema.system.utils.converters;

import cinema.system.database.models.Films;
import cinema.system.modelFx.FilmFx;
import cinema.system.utils.utils;

public class ConverterFilm {
    //converter filmufx na film
    public static Films convertToFilm(FilmFx filmFx){
        Films film = new Films();
        film.setId(filmFx.getId());
        film.setTitle(filmFx.getTitle());
        film.setDescription(filmFx.getDescription());
        film.setRating(filmFx.getRating());
        film.setReleaseDate(utils.convertToDate(filmFx.getReleaseDate()));
        film.setAddedDate(utils.convertToDate(filmFx.getAddedDate()));
        return film;
    }
    //converter filmu na filmfx
    public static FilmFx convertToFilmFx(Films film){
        FilmFx filmFx = new FilmFx();
        filmFx.setId(film.getId());
        filmFx.setTitle(film.getTitle());
        filmFx.setDescription(film.getDescription());
        filmFx.setRating(film.getRating());
        filmFx.setReleaseDate(utils.convertToLocalDate(film.getReleaseDate()));
        filmFx.setAuthorFx(ConverterAuthor.convertToAuthorFx(film.getAuthor()));
        filmFx.setCategoryFx(ConvertCategory.convertToCategoryFx(film.getCategory()));
        return filmFx;
    }
}
