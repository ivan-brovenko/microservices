package com.istore.mysqldbservice.repository.impl.jpa;

import com.istore.mysqldbservice.model.Order;
import com.istore.mysqldbservice.repository.OrderRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class OrderRepositoryJPAImpl implements OrderRepository {
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Order> findAllById(Iterable<Integer> iterable) {
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
    public void delete(Order order) {

    }

    @Override
    public void deleteAll(Iterable<? extends Order> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Order> S save(S s) {
        return null;
    }

    @Override
    public <S extends Order> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Order> findById(Integer integer) {
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
    public <S extends Order> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Order> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Order getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Order> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Order> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Order> boolean exists(Example<S> example) {
        return false;
    }
}
