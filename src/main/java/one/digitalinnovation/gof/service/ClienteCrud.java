package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteCrud extends CrudRepository<Cliente, Long> {}