package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.service.ClienteCrud;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.service.EnderecoCrud;
import one.digitalinnovation.gof.service.ViaCepApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import one.digitalinnovation.gof.model.Cliente;

import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class Clientes {

    /* INSTANCIAS */
    @Autowired
    private ClienteCrud clienteCrud;
    @Autowired
    private EnderecoCrud enderecoCrud;
    @Autowired
    private ViaCepApi viaCepApi;

    /* MÉTODOS GET */

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> getClientes() {
        Iterable<Cliente> clientes = clienteCrud.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteCrud.findById(id);
        return ResponseEntity.ok(cliente.get());
    }

    @GetMapping("/{id}/endereco")
    public ResponseEntity<Endereco> getEndereco(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteCrud.findById(id);
        Endereco endereco = cliente.get().getEndereco();
        return ResponseEntity.ok(endereco);
    }

    /* MÉTODO POST */

    @PostMapping
    public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente) {
        salvarClienteComCep(cliente);
        return ResponseEntity.ok(cliente);
    }

    /* MÉTODO PUT */

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> putCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteAtual = clienteCrud.findById(id);
        if (clienteAtual.isPresent()) {
            salvarClienteComCep(cliente);
        }
        return ResponseEntity.ok(cliente);
    }

    /* MÉTODO DELETE */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteCrud.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /* MÉTODO UTILITÁRIO */

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoCrud.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepApi.consultarCep(cep);
            enderecoCrud.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteCrud.save(cliente);
    }

}
