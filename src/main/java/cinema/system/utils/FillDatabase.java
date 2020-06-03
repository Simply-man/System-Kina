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
    public static void fillDatabase(){
        Category category1 = new Category();
        category1.setName("Dramat");
        Author author1 = new Author();
        author1.setName("Quentin");
        author1.setSurname("Tarantino");
        Films films1 = new Films();
        films1.setCategory(category1);
        films1.setAuthor(author1);
        films1.setTitle("Pulp Fiction");
        films1.setIsbn("87123549");
        films1.setRating(8);
        films1.setReleaseDate(new Date());
        films1.setAddedDate(new Date());
        films1.setDescription("Opowiesc o dwoch platnych mordecach pracujacych na zlecenie mafii.");

        Category category2 = new Category();
        category2.setName("Sensacja");
        CategoryDao categoryDao = new CategoryDao();
        try {
            categoryDao.creatOrUpdate(category1);
            DbManager.closeConnectionSource();
        }catch (AppExpections expections){
            expections.printStackTrace();
        }

        Category category3 = new Category();
        category3.setName("Komedia");
        Author author2 = new Author();
        author2.setName("Martin");
        author2.setSurname("Scorsese");
        Films films2 = new Films();
        films2.setCategory(category3);
        films2.setAuthor(author2);
        films2.setTitle("Wilk z Wall Street");
        films2.setIsbn("87123549");
        films2.setRating(7);
        films2.setReleaseDate(new Date());
        films2.setAddedDate(new Date());
        films2.setDescription("Historia Jordana Belforta.");

        FilmsDao filmsDao = new FilmsDao();
        try{
            filmsDao.creatOrUpdate(films1);
            filmsDao.creatOrUpdate(films2);
        }catch (AppExpections expections){
            expections.printStackTrace();
        }

        DbManager.closeConnectionSource();
    }
}
