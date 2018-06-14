package com.example.javadb.interFaces;

import com.example.javadb.entity.Model;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ModelInt extends CrudRepository<Model, Long> {
    List<Model> findAll();
    Model findByName(@NotNull String nazwa);
}