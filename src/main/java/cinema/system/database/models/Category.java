package cinema.system.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

// deklaracja tabeli categories
@DatabaseTable(tableName = "CATEGORIES")
public class Category implements BaseModel {
    public Category() {
    }

    // tworzenie unikalnego ID
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false, unique = true)
    private String name;

    @ForeignCollectionField(columnName = "Film_ID")
    private ForeignCollection<Films> films;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Films> getFilms() {
        return films;
    }

    public void setFilms(ForeignCollection<Films> films) {
        this.films = films;
    }
}
