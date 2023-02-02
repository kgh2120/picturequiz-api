package com.kk.picturequizapi.domain.comment.query.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentSearchResult {

    private List<CommentSearch> comments;
    private Long currentPage;
    private Long lastPage;

}
