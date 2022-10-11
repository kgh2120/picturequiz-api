package com.kk.picturequizapi.domain.tag.infra;

import com.kk.picturequizapi.domain.tag.command.domain.TagData;
import com.kk.picturequizapi.domain.tag.command.domain.TagId;
import com.kk.picturequizapi.domain.tag.command.domain.TagRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTagRepository extends TagRepository, JpaRepository<TagData, TagId> {


    boolean existsByName(String name);
}
