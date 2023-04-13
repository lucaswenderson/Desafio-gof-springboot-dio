package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Endereco;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoCrud extends CrudRepository<Endereco, String> {}