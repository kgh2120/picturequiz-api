package com.kk.picturequizapi.domain.tag.command.domain;

import java.util.UUID;

public interface TagRepository {

    // 생성

    TagData save(TagData tag);

    boolean existsByName(String name);

    default String nextId() {
        return UUID.randomUUID().toString();
    }
}
