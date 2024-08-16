package one.digitalinovation.gof.service.impl;

import one.digitalinovation.gof.model.Cliente;
import one.digitalinovation.gof.model.ClienteRepository;
import one.digitalinovation.gof.model.Endereco;
import one.digitalinovation.gof.model.EnderecoRepository;
import one.digitalinovation.gof.service.ClienteService;
import one.digitalinovation.gof.service.ViaCepSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepSevice viaCepSevice;

    @Override
    public Iterable<Cliente> buscarTodos() {
        //Buscar todos os clientes(devolve um iterable)
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteUp = clienteRepository.findById(id);
        if(clienteUp.isPresent()){
            salvarClienteCep(cliente);
        }
    }

    private void salvarClienteCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Optional<Endereco> enderecoOptional = enderecoRepository.findByCep(cep);
        Endereco endereco = enderecoOptional.orElseGet(() -> {
            Endereco novoEndereco = viaCepSevice.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
