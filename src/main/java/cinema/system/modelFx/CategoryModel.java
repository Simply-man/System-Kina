package cinema.system.modelFx;

import cinema.system.database.dao.CategoryDao;
import cinema.system.database.dao.CommonDao;
import cinema.system.database.dbuitls.DbManager;
import cinema.system.database.models.Category;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

// KLasa rozdzielajaca główną apliakcje od bazy danych
public class CategoryModel {

    // Lista dla pól w  ComboBox
    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();
    // Wybrany element w ComboBox
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>();

    // Funcja inicjalizująca liste danych z dany bazych
    public void init(){
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        List<Category> categories = categoryDao.queryForAll(Category.class);
        this.categoryList.clear();
        categories.forEach(c ->{
            CategoryFx categoryFx = new CategoryFx();
            categoryFx.setId(c.getId());
            categoryFx.setName(c.getName());
            this.categoryList.add(categoryFx);
        });
        DbManager.closeConnectionSource();
    }

    public void saveCategoryInDatabase(String name){
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category category = new Category();
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        DbManager.closeConnectionSource();
        init();
    }

    public void deleteCategoryById(){
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        categoryDao.deleteById(Category.class, category.getValue().getId());
        DbManager.closeConnectionSource();
        init();
    }

    public ObservableList<CategoryFx> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ObservableList<CategoryFx> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryFx getCategory() {
        return category.get();
    }

    public ObjectProperty<CategoryFx> categoryProperty() {
        return category;
    }

    public void setCategory(CategoryFx category) {
        this.category.set(category);
    }
}
