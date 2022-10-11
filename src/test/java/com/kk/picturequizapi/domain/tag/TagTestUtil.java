package com.kk.picturequizapi.domain.tag;

import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;

import java.lang.reflect.Field;
import java.util.Optional;

public class TagTestUtil {

    public static Optional<TagSearch> createTag() throws Exception {
        TagSearch tagSearch = new TagSearch();
        Field id = tagSearch.getClass().getDeclaredField("id");
        id.setAccessible(true);
        id.set(tagSearch,"1");

        Field name = tagSearch.getClass().getDeclaredField("name");
        name.setAccessible(true);
        name.set(tagSearch, "운동");

        return Optional.of(tagSearch);
    }
}
