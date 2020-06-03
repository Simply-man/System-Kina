package cinema.system.utils;

import cinema.system.database.dao.CategoryDao;
import cinema.system.database.dao.FilmsDao;
import cinema.system.database.dbuitls.DbManager;
import cinema.system.database.models.Author;
import cinema.system.database.models.Category;
import cinema.system.database.models.Films;
import cinema.system.utils.expections.AppExpections;

import java.util.Date;

public class FillDatabase {
    public static  void fillDatabase(){
        Category category1 = new Category();
        category1.setName("Dramat");
        Author author1 = new Author();
        author1.setName("William");
        author1.setSurname("Szekspir");
        Films films = new Films();
        films.setCategory(category1);
        films.setAuthor(author1);
        films.setTitle("Makbet");
        films.setIsbn("8386740418l");
        films.setRating(4);
        films.setReleaseDate(new Date());
        films.setAddedDate(new Date());
        films.setDescription("Byłaby to fajna książka, gdyby nie była lekturą");


        Category category2 = new Category();
        category2.setName("Sensacja");
        CategoryDao categoryDao = new CategoryDao();
        try {
            categoryDao.createOrUpdate(category2);
            DbManager.closeConnectionSource();
        } catch (AppExpections e) {
            e.printStackTrace();
        }


        Category category3 = new Category();
        category3.setName("Reportaż");
        Author author2 = new Author();
        author2.setName("Mariusz");
        author2.setSurname("Szczygieł");
        Films films2 = new Films();
        films2.setCategory(category3);
        films2.setAuthor(author2);
        films2.setTitle("Gottland");
        films2.setIsbn("8386740418l");
        films2.setRating(5);
        films2.setReleaseDate(new Date());
        films2.setAddedDate(new Date());
        films2.setDescription("Ciekawe reportaże, ze świata");

        Category category4 = new Category();
        category4.setName("Fantastyka");
        Author author3 = new Author();
        author3.setName("John Ronald Reuel");
        author3.setSurname("Tolkien");


        Films films3 = new Films();
        films3.setCategory(category4);
        films3.setAuthor(author3);
        films3.setTitle("Władca Pierścieni");
        films3.setIsbn("8386740418l");
        films3.setRating(5);
        films3.setReleaseDate(new Date());
        films3.setAddedDate(new Date());
        films3.setDescription("O dwóch takich, co nieśli pierścień");

        Author author4 = new Author();
        author4.setName("Terry ");
        author4.setSurname("Pratchett");

        Films films4 = new Films();
        films4.setCategory(category4);
        films4.setAuthor(author4);
        films4.setTitle("Kolor magii");
        films4.setIsbn("8386740418l");
        films4.setRating(3);
        films4.setReleaseDate(new Date());
        films4.setAddedDate(new Date());
        films4.setDescription("Do przeczytania");

        FilmsDao filmDao = new FilmsDao();
        try {
            filmDao.createOrUpdate(films);
            filmDao.createOrUpdate(films2);
            filmDao.createOrUpdate(films3);
            filmDao.createOrUpdate(films4);
        } catch (AppExpections appExpections) {
            appExpections.printStackTrace();
        }
        DbManager.closeConnectionSource();
    }
}
