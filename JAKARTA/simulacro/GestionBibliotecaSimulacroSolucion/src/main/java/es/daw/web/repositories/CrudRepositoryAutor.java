package es.daw.web.repositories;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import es.daw.web.entities.Autor;
import es.daw.web.exceptions.JPAException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@RequestScoped
public class CrudRepositoryAutor implements CrudRepository<Autor> {

    @Inject
    EntityManager em;

    @Override
    public Set<Autor> select() throws JPAException {

        try{
            String jpql = "SELECT a FROM Autor a";

            List<Autor> lista = em.createQuery(jpql, Autor.class).getResultList();
    
            
            Set<Autor> autores = new LinkedHashSet<>(lista);
    
            return autores;
    
        }catch(Exception e){
            throw new JPAException(JpaManagerCdi.getMessageError(e));
        }
    }

    @Override
    public Optional<Autor> selectById(int id) throws JPAException {
        try{
            Autor autor = em.find(Autor.class, id);
            return Optional.ofNullable(autor);
        }catch(Exception e){
            throw new JPAException(JpaManagerCdi.getMessageError(e));
        }

    }

    @Override
    public void deleteById(int id) throws JPAException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    @Transactional
    public void save(Autor t) throws JPAException {
        try {
                em.merge(t); //update
            } catch (Exception e) {
                throw new JPAException(JpaManagerCdi.getMessageError(e));
            }
    }


    // método extra....
}
