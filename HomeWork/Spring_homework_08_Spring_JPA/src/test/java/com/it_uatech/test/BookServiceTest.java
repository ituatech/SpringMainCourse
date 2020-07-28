package com.it_uatech.test;

import com.it_uatech.domain.Author;
import com.it_uatech.domain.Book;
import com.it_uatech.domain.Comment;
import com.it_uatech.domain.Genre;
import com.it_uatech.services.BookService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(com.it_uatech.config.Configurations.class)
@ComponentScan("com.it_uatech.repository")
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    private TestEntityManager em;

    @Test
    public void retrieveCorrectCountOfATable() {
        assertThat(bookService).matches(bookService -> bookService.count() == 2);
    }

    @Test
    public void shouldFindExpectedBookById() {
        val optionalActualBook = bookService.getById(1);
        val expectedBook = em.find(Book.class, 1);
        val expectedAuthor = em.find(Author.class,1);
        val expectedGenre = em.find(Genre.class,1);
        val expectedComment1 = em.find(Comment.class,1);
        val expectedComment2 = em.find(Comment.class,2);

        assertThat(optionalActualBook).isNotNull().usingRecursiveComparison().isEqualTo(expectedBook);
        assertThat(optionalActualBook).matches(b -> b.getName().equals("book1")&&b.getDescription().equals("description1"),"book name matches")
                .matches(b->b.getAuthor()!=null&&b.getAuthor()==expectedAuthor,"author matches")
                .matches(b->b.getGenre().size()==1&&b.getGenre().contains(expectedGenre),"genre matches")
                .matches(b->b.getComments().size()==2&&b.getComments().contains(expectedComment1)&&b.getComments().contains(expectedComment2),"comment matches");
    }

    @Test
    void shouldReturnCorrectBookListWithAllInfo() {
        val books = bookService.getAllBooks();
        assertThat(books).isNotNull().hasSize(2).allMatch(book -> !book.getName().equals("")&&!book.getDescription().equals(""), "all books exist")
                .allMatch(book -> book.getAuthor()!=null, "all books have author")
                .allMatch(book -> book.getGenre().size()==1,"all books have genre")
                .allMatch(book -> book.getComments().size()==2,"all books have comments");
    }

    @Test
    public void shouldSaveAllBookInfo() {
        Book book = bookService.insert("newBook","newDescription","newAuthorFirstName","newAuthorSecondName","newGenre");
        assertThat(book).isNotNull();
        int id = book.getId();
        assertThat(id).isGreaterThan(0);
        val actualBook = em.find(Book.class, id);
        assertThat(actualBook).isNotNull().matches(b -> b.getName().equals(book.getName())&&b.getDescription().equals(book.getDescription()), "book name matches")
                .matches(b -> b.getAuthor().getFirstName().equals("newAuthorFirstName")&&b.getAuthor().getSecondName().equals("newAuthorSecondName"), "author exists")
                .matches(b->b.getGenre().iterator().next().getGenreName().equals("newGenre"),"genre exist")
                .matches(b->b.getComments().isEmpty(),"there is no comments");
    }

    @Test
    public void shouldDeleteBook() {
        assertThat(em.find(Book.class,1)).isNotNull();
        assertThat(em.find(Genre.class,1)).isNotNull();
        assertThat(em.find(Comment.class,1)).isNotNull();
        assertThat(em.find(Comment.class,2)).isNotNull();
        bookService.deleteById(1);
        assertThat(em.find(Book.class,1)).isNull();
        assertThat(em.find(Genre.class,1)).isNull();
        assertThat(em.find(Comment.class,1)).isNull();
        assertThat(em.find(Comment.class,2)).isNull();
    }
}
