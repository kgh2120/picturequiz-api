package com.kk.picturequizapi.domain.report;

import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public class FakeUserRepository implements UserRepository {

    private final Map<Long, Users> storage = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Optional<Users> findByLoginId(String loginId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return false;
    }

    @Override
    public Optional<Users> findByAuthEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Users> findByAuthEmailAndLoginId(String email, String loginId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByLoginId(String loginId) {
        return false;
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public List<Users> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Users> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Users entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Users> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Users> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Users> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Users> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Users> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Users> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Users getOne(Long aLong) {
        return null;
    }

    @Override
    public Users getById(Long aLong) {
        return null;
    }

    @Override
    public Users getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Users> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Users> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Users> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Users, R> R findBy(Example<S> example,
            Function<FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
