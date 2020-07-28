package com.it_uatech.test;

import com.it_uatech.domain.Comment;
import com.it_uatech.services.CommentService;
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
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private TestEntityManager em;

    @Test
    public void retrieveCorrectCountOfATable() {
        assertThat(commentService).matches(commentService -> commentService.count() == 4);
    }

    @Test
    public void shouldFindExpectedCommentById() {
        val optionalActualComment = commentService.getById(1);
        val expectedComment = em.find(Comment.class, 1);
        assertThat(optionalActualComment).isNotNull().usingRecursiveComparison().isEqualTo(expectedComment);
        assertThat(optionalActualComment).matches(c -> c.getComment().equals("comment11") && c.getBook() != null && c.getBook().getId() == 1);
    }

    @Test
    void shouldReturnCorrectCommentListWithAllInfo() {
        val comments = commentService.getAllComments();
        assertThat(comments).isNotNull().hasSize(4).allMatch(comment -> !comment.getComment().equals(""), "all comments exist")
                .allMatch(comment -> comment.getBook() != null, "all comments belong to books");
    }

    @Test
    public void shouldSaveAllCommentInfo() {
        Comment comment = commentService.insert(1, "newComment");
        assertThat(comment).isNotNull();
        int id = comment.getId();
        assertThat(id).isGreaterThan(0);
        val actualComment = em.find(Comment.class, id);
        assertThat(actualComment).isNotNull().matches(c -> c.getComment().equals(comment.getComment()), "comment matches")
                .matches(c -> c.getBook() != null, "book exists");
    }

    @Test
    public void shouldDeleteComment() {
        assertThat(em.find(Comment.class,1)).isNotNull();
        commentService.deleteById(1);
        assertThat(em.find(Comment.class,1)).isNull();
    }
}
