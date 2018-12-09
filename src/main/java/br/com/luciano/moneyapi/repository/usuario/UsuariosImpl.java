package br.com.luciano.moneyapi.repository.usuario;

import br.com.luciano.moneyapi.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UsuariosImpl implements UsuariosQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<String> permissoes(Usuario usuario) {
        return manager.createQuery(
                "select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }
}
