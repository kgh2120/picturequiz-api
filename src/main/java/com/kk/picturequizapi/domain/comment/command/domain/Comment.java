package com.kk.picturequizapi.domain.comment.command.domain;

import com.kk.picturequizapi.domain.comment.exception.AccessDeletedCommentException;
import com.kk.picturequizapi.domain.comment.exception.CommentNotYoursException;
import com.kk.picturequizapi.domain.quiz.command.domain.Author;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.global.jpa.BaseEntity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Comment extends BaseEntity {

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

    private LocalDateTime createdDateTime;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL ,orphanRemoval = true)
    private Set<CommentRecommend> commentRecommends = new HashSet<>();

    public Comment(CommentId commentId, CommentId parentId, QuizId quizId, Author author,
            CommentOrder commentOrder, CommentContent content) {
        this.commentId = commentId;
        this.parentId = parentId;
        this.quizId =quizId;
        this.author = author;
        this.commentOrder = commentOrder;
        this.recommend = Recommend.of();
        this.commentContent = content;
        createdDateTime = LocalDateTime.now();
    }

    public static Comment of(CommentId commentId, CommentId parentId, QuizId quizId, Author author,
            CommentOrder commentOrder, CommentContent content){
        return new Comment(commentId, parentId, quizId,author,commentOrder, content);
    }



    public void associatedWith(CommentRecommend commentRecommend) {
        commentRecommends.add(commentRecommend);
    }

    public CommentId getCommentId(){
        return commentId;
    }

    public void increaseRecommend() {
        this.recommend = recommend.increaseRecommend();
    }

    public void decreaseNotRecommend() {
        this.recommend = recommend.decreaseNotRecommend();
    }

    public void decreaseRecommend() {
        this.recommend = recommend.decreaseRecommend();
    }

    public void increaseNotRecommend() {
        this.recommend = recommend.increaseNotRecommend();
    }

    public boolean isParentComment(){
        return parentId == null;
    }

    public void delete(){
        this.author = new Author(null, "[삭제]");
        this.recommend = Recommend.of();
        this.commentContent = CommentContent.of("[삭제된 댓글입니다]");
        this.commentRecommends.clear();
    }

    public void validateAccessAuthority(long userId){
        validateAlreadyDeleted();
         if(!this.author.getUserId().equals(UserId.of(userId)))
             throw new CommentNotYoursException();
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

    public void changeAuthorNickname(String nickname) {
        author = author.changeNickname(nickname);
    }

    public void validateAlreadyDeleted() {
        if(this.author == null || this.author.getUserId() == null)
            throw new AccessDeletedCommentException();
    }
}
