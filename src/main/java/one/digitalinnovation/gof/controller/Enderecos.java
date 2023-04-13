package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.service.EnderecoCrud;
import one.digitalinnovation.gof.service.ViaCepApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("enderecos")
public class Enderecos {

    /* INSTANCIAS */
    @Autowired
    EnderecoCrud enderecoCrud;
    @Autowired
    ViaCepApi viaCepApi;

    /* MÉTODOS GET */

    @GetMapping
    public ResponseEntity<Iterable<Endereco>> getEnderecos() {
        Iterable<Endereco> enderecos = enderecoCrud.findAll();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Endereco> getEndereco(@PathVariable String cep) {
        Optional<Endereco> endereco = enderecoCrud.findById(cep);
        return ResponseEntity.ok(endereco.get());
    }

    /* MÉTODO POST */

    @PostMapping
    public ResponseEntity<Endereco> postEndereco(@RequestBody Endereco endereco) {
        Endereco enderecoRetornado = viaCepApi.consultarCep(endereco.getCep());
        enderecoCrud.save(enderecoRetornado);
        return ResponseEntity.ok(enderecoRetornado);
    }


    /* MÉTODO PUT: O ENDEREÇO NÃO DEVE SER MODIFICADO */

    /* MÉTODO DELETE */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable String cep) {
        enderecoCrud.deleteById(cep);
        return ResponseEntity.ok().build();
    }
}
