package com.istore.mysqldbservice.repository.impl.jdbc;

import com.istore.mysqldbservice.model.Category;
import com.istore.mysqldbservice.repository.CategoryRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class CategoryRepositoryJDBCImpl implements CategoryRepository {
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Category> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public void deleteAll(Iterable<? extends Category> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Category> S save(S s) {
        return null;
    }

    @Override
    public <S extends Category> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Category> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Category> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Category getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Category> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Category> boolean exists(Example<S> example) {
        return false;
    }
}
