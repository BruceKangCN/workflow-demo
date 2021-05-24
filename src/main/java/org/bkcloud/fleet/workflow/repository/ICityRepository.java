package org.bkcloud.fleet.workflow.repository;

import org.bkcloud.fleet.workflow.entity.City;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository auto implemented by spring boot
 */
@Repository
public interface ICityRepository extends JpaRepository<City, Integer> {
    @Override
    List<City> findAll();

    @Override
    List<City> findAll(Sort sort);

    @Override
    Page<City> findAll(Pageable pageable);

    @Override
    List<City> findAllById(Iterable<Integer> iterable);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(City city);

    @Override
    void deleteAll(Iterable<? extends City> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends City> S save(S s);

    @Override
    <S extends City> List<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<City> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void flush();

    @Override
    <S extends City> S saveAndFlush(S s);

    @Override
    void deleteInBatch(Iterable<City> iterable);

    @Override
    void deleteAllInBatch();

    @Override
    City getOne(Integer integer);

    @Override
    <S extends City> Optional<S> findOne(Example<S> example);

    @Override
    <S extends City> List<S> findAll(Example<S> example);

    @Override
    <S extends City> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends City> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends City> long count(Example<S> example);

    @Override
    <S extends City> boolean exists(Example<S> example);
}
