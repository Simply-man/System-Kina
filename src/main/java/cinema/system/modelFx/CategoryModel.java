package cinema.system.modelFx;

import cinema.system.database.dao.CategoryDao;
import cinema.system.database.dao.CommonDao;
import cinema.system.database.dbuitls.DbManager;
import cinema.system.database.models.Category;
import cinema.system.utils.converters.ConvertCategory;
import cinema.system.utils.expections.AppExpections;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.List;

// KLasa rozdzielajaca główną apliakcje od bazy danych
public class CategoryModel {

    // Lista dla pól w  ComboBox
    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();
    // Wybrany element w ComboBox
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>();
    // Wyswietlanie bazy danych
    private TreeItem<String> root = new TreeItem<>();


    // Funkcja inicjalizująca liste danych z danymi w bazie danych
    public void init() throws AppExpections {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.queryForAll(Category.class);
        initCategoryList(categories);
        initRoot(categories);
    }

    private void initRoot(List<Category> categories) {
        this.root.getChildren().clear();
        categories.forEach(c->{
            TreeItem<String> categoryItem = new TreeItem<>(c.getName());
            c.getFilms().forEach(b->{
                categoryItem.getChildren().add(new TreeItem<>(b.getTitle()));
            });
            root.getChildren().add(categoryItem);
        });
    }

    private void initCategoryList(List<Category> categories) {
        this.categoryList.clear();
        categories.forEach(c ->{
            CategoryFx categoryFx = ConvertCategory.convertToCategoryFx(c);
            this.categoryList.add(categoryFx);
        });
    }

    public void saveCategoryInDatabase(String name) throws AppExpections {
        CategoryDao categoryDao = new CategoryDao(); //polaczenie do bazy danych
        Category category = new Category();
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        // zamkniecie polaczenia do bazy w commonDao
        init();
    }

    public void deleteCategoryById() throws AppExpections {
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.deleteById(Category.class, category.getValue().getId());
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

    public void updateCategoryInDataBase() throws AppExpections {
        CategoryDao categoryDao = new CategoryDao();
        Category tempCategory = categoryDao.findById(Category.class, getCategory().getId());
        tempCategory.setName(getCategory().getName());
        categoryDao.creatOrUpdate(tempCategory);
        init();
    }

    public TreeItem<String> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<String> root) {
        this.root = root;
    }

}
