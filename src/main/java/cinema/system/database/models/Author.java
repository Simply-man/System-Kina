package cinema.system.database.models;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

// deklaracja tabeli Authors
@DatabaseTable(tableName = "AUTHORS")
public class Author implements BaseModel {
    public Author() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false, unique = true)
    private String name;

    @DatabaseField(columnName = "SURNAME", canBeNull = false, unique = true)
    private String surname;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Films> films;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ForeignCollection<Films> getFilms() {
        return films;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFilms(ForeignCollection<Films> films) {
        this.films = films;
    }
}