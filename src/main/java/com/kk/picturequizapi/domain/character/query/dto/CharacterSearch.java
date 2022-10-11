package com.kk.picturequizapi.domain.character.query.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "character") @Getter @NoArgsConstructor
public class CharacterSearch implements Serializable {

    @Id @Column(name = "character_id")
    private Long characterId;
    private String name;
    private String job;


}
