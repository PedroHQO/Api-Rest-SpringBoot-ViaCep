package one.digitalinovation.gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//interface que vai prover todo acesso aos dados
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
