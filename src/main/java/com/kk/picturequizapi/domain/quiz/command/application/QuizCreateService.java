package com.kk.picturequizapi.domain.quiz.command.application;


import com.kk.picturequizapi.domain.quiz.command.domain.*;
import com.kk.picturequizapi.domain.tag.command.domain.TagId;
import com.kk.picturequizapi.domain.tag.exception.TagNotFoundException;
import com.kk.picturequizapi.domain.tag.query.dao.TagSearchDao;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class QuizCreateService {

    private final AuthorService authorService;
    private final AnswerService answerService;
    private final QuizRepository quizRepository;
    private final PictureUploadService pictureUploadService;
    private final TagSearchDao tagSearchDao;


    public void createQuiz(MultipartFile file, QuizCreateRequest request) {

        Answer answer = createAnswer(request);
        Author author = createAuthor();
        Picture picture = createPicture(file);
        List<QuizTag> quizTags = createQuizTag(request.getTagNames());

        Quiz quiz = new Quiz(QuizId.of(quizRepository.nextId()),
                author, picture, answer, quizTags);
        quizRepository.save(quiz);
    }

    private Picture createPicture(MultipartFile file) {
        return pictureUploadService.uploadPicture(file);
    }

    private Author createAuthor() {
        return authorService.createAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    private Answer createAnswer(QuizCreateRequest request) {
        return answerService.createAnswer(request.getCharacterId());
    }

    private List<QuizTag> createQuizTag(List<String> tagNames) {
        List<QuizTag> tags = new ArrayList<>();
        tagNames.forEach(name ->{
            TagSearch tagSearch = tagSearchDao.findByName(name)
                    .orElseThrow(TagNotFoundException::new);
            tags.add(new QuizTag(TagId.of(tagSearch.getId())
                    , tagSearch.getName() , tagSearch.getColor()));
        });
        return tags;
    }


}
