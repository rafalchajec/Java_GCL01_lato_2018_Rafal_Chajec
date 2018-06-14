package com.example.javadb.interFaces;

import com.example.javadb.entity.Samochod;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SamochodInt extends CrudRepository<Samochod, Long> {
    List<Samochod> findAll();
    List<Samochod> findAllByNazwa(@NotNull String nazwa);
    List<Samochod> findAllByIdProducent(@NotNull Long idProducent);
    List<Samochod> findAllByIdModel(@NotNull Long idModel);
}