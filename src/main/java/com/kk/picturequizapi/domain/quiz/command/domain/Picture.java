package com.kk.picturequizapi.domain.quiz.command.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable @NoArgsConstructor @Getter
public class Picture {

    private String url;

    public Picture(String url) {
        this.url = url;
    }


}
