package br.com.luciano.moneyapi.resource;

import br.com.luciano.moneyapi.model.Usuario;
import br.com.luciano.moneyapi.repository.Usuarios;
import br.com.luciano.moneyapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private Usuarios usuarios;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> inserir(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = this.usuarioService.salvar(usuario);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(usuarioSalvo.getId()).toUri()).body(usuarioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editar(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = this.usuarioService.editar(id, usuario);

        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        this.usuarios.deleteById(id);
    }

    @GetMapping
    public Page<Usuario> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
        return this.usuarios.findByNomeContaining(nome, pageable);
    }

}
