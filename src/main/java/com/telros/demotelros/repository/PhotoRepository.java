package com.telros.demotelros.repository;

import com.telros.demotelros.entity.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
    Optional<Photo> findByUserId(Long id);
}
