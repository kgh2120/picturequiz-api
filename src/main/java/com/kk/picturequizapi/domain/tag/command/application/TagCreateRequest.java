package com.kk.picturequizapi.domain.tag.command.application;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class TagCreateRequest {

    @NotBlank @Pattern(regexp = "^[ㄱ-ㅎㅏ-ㅣ가-힣0-9A-Za-z]{1,5}")
    private String name;
    @NotBlank
    private String color;
}
