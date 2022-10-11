package com.kk.picturequizapi.domain.character;

import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CharacterTestUtil {

    public static List<CharacterSearch> create5CharacterSearch()throws Exception {
        List<CharacterSearch> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            CharacterSearch characterSearch = new CharacterSearch();
            Field characterId = characterSearch.getClass().getDeclaredField("characterId");
            characterId.setAccessible(true);
            characterId.set(characterSearch, (long) i);

            Field name = characterSearch.getClass().getDeclaredField("name");
            name.setAccessible(true);
            name.set(characterSearch,"이름"+i);

            Field job = characterSearch.getClass().getDeclaredField("job");
            job.setAccessible(true);
            job.set(characterSearch,"학생");
            list.add(characterSearch);
        }
        return list;
    }
}
