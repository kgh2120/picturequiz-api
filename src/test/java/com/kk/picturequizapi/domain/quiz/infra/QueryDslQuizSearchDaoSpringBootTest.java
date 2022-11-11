package com.kk.picturequizapi.domain.quiz.infra;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryDslQuizSearchDaoSpringBootTest {


    @Autowired
    Environment env;

    @Test
    void limitTest () throws Exception{
        Integer limit = env.getProperty("quiz.limit", Integer.class);

        assertThat(limit).isEqualTo(50);
    }

}