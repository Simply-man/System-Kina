package cinema.system.utils.converters;

import cinema.system.database.models.Category;
import cinema.system.modelFx.CategoryFx;

public class ConvertCategory {

    public static CategoryFx convertToCategoryFx(Category category){
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());
        categoryFx.setName(category.getName());
        return categoryFx;
    }
}
