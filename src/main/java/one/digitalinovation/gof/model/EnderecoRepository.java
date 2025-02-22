package one.digitalinovation.gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
    Optional<Endereco> findByCep(String cep);
}
