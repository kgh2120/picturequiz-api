package com.kk.picturequizapi.domain.tag.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tag")
@Entity @Getter @NoArgsConstructor @AllArgsConstructor
public class TagSearch {

    @Id @Column(name = "tag_id")
    private String id;
    private String name;


}
