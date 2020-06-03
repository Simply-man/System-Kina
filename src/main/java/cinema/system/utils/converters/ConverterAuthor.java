package cinema.system.utils.converters;

import cinema.system.database.models.Author;
import cinema.system.modelFx.AuthorFx;

public class ConverterAuthor {

    public static Author convertoAuthor(AuthorFx authorFx){
        Author author = new Author();
        author.setId(authorFx.getId());
        author.setName(authorFx.getName());
        author.setSurname(authorFx.getSurname());
        return author;
    }

    public static AuthorFx convertToAuthorFx(Author author){
        AuthorFx authorFx = new AuthorFx();
        authorFx.setId(author.getId());
        authorFx.setName(author.getName());
        authorFx.setSurname(author.getSurname());
        return authorFx;
    }
}
