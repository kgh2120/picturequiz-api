package com.kk.picturequizapi.domain.comment.command.domain;

import com.kk.picturequizapi.domain.quiz.command.domain.Author;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Comment {

    @EmbeddedId
    private CommentId commentId;

    @AttributeOverride(name = "commentId", column = @Column(name = "parent_id"))
    @Embedded
    private CommentId parentId;
    @Embedded
    private QuizId quizId;
    @Embedded
    private Author author;
    @Embedded
    private CommentOrder commentOrder;
    @Embedded
    private CommentContent commentContent;
    @Embedded
    private Recommend recommend;

    public Comment(CommentId commentId, CommentId parentId, QuizId quizId, Author author,
            CommentOrder commentOrder, CommentContent content) {
        this.commentId = commentId;
        this.parentId = parentId;
        this.quizId =quizId;
        this.author = author;
        this.commentOrder = commentOrder;
        this.recommend = Recommend.of();
        this.commentContent = content;
    }

    public static Comment of(CommentId commentId, CommentId parentId, QuizId quizId, Author author,
            CommentOrder commentOrder, CommentContent content){
        return new Comment(commentId, parentId, quizId,author,commentOrder, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) && Objects.equals(
                parentId, comment.parentId) && Objects.equals(quizId, comment.quizId)
                && Objects.equals(author, comment.author) && Objects.equals(
                commentOrder, comment.commentOrder) && Objects.equals(commentContent,
                comment.commentContent) && Objects.equals(recommend, comment.recommend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, parentId, quizId, author, commentOrder, commentContent,
                recommend);
    }
}