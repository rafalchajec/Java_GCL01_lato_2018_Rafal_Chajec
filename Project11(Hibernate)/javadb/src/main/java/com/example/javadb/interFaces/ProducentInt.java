package com.example.javadb.interFaces;

import com.example.javadb.entity.Producent;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProducentInt extends CrudRepository<Producent, Long> {
    List<Producent> findAll();

    Producent findByName(@NotNull String nazwa);
}