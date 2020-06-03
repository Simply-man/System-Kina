package cinema.system.modelFx;

import cinema.system.database.dao.AuthorDao;
import cinema.system.database.dao.CategoryDao;
import cinema.system.database.dao.FilmsDao;
import cinema.system.database.dbuitls.DbManager;
import cinema.system.database.models.Author;
import cinema.system.database.models.Category;
import cinema.system.database.models.Films;
import cinema.system.utils.converters.ConvertCategory;
import cinema.system.utils.converters.ConverterAuthor;
import cinema.system.utils.converters.ConverterFilm;
import cinema.system.utils.expections.AppExpections;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class FilmModel {

    private ObjectProperty<FilmFx> filmFxObjectProperty = new SimpleObjectProperty<>(new FilmFx());
    //zainicjalizowanie list
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();



    public void init() throws AppExpections {
        initAuthorList();
        initCategoryList();

    }
    // pobieranie kategorii
    private void initCategoryList() throws AppExpections {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList= categoryDao.queryForAll(Category.class);
        //czyszczenie listy
        categoryFxObservableList.clear();
        //petla
        categoryList.forEach(c->{
            CategoryFx categoryFx = ConvertCategory.convertToCategoryFx(c);
            categoryFxObservableList.add(categoryFx);
        });
    }
    // Pobieranie authora
    private void initAuthorList() throws AppExpections {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        authorFxObservableList.clear();
        authorList.forEach(author -> {
            AuthorFx authorFx = ConverterAuthor.convertToAuthorFx(author);
            authorFxObservableList.add(authorFx);
        });
    }
    //zapis ksiazki do bazy danych
    public void saveFilmInDatabase() throws AppExpections {
        Films films = ConverterFilm.convertToFilm(this.getFilmFxObjectProperty());

        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findById(Category.class, this.getFilmFxObjectProperty().getCategoryFx().getId());

        AuthorDao authorDao = new AuthorDao();
        Author author = authorDao.findById(Author.class, this.getFilmFxObjectProperty().getAuthorFx().getId());

        films.setCategory(category);
        films.setAuthor(author);

        FilmsDao filmsDao = new FilmsDao();
        filmsDao.createOrUpdate(films);
    }

    public FilmFx getFilmFxObjectProperty() {
        return filmFxObjectProperty.get();
    }

    public ObjectProperty<FilmFx> filmFxObjectPropertyProperty() {
        return filmFxObjectProperty;
    }

    public void setFilmFxObjectProperty(FilmFx filmFxObjectProperty) {
        this.filmFxObjectProperty.set(filmFxObjectProperty);
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }
}
