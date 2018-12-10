package br.com.luciano.moneyapi.resource;

import br.com.luciano.moneyapi.model.Grupo;
import br.com.luciano.moneyapi.repository.Grupos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoResource {

    @Autowired
    private Grupos grupos;

    @GetMapping
    public List<Grupo> findAll() {
        return this.grupos.findAll();
    }

}
