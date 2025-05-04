package es.daw.web.repositories;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import es.daw.web.entities.Ejemplar;
import es.daw.web.exceptions.JPAException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@RequestScoped
public class CrudRepositoryEjemplar implements CrudRepository<Ejemplar> {

    @Inject
    EntityManager em;

    @Override
    public Set<Ejemplar> select() throws JPAException {

        try{
            String jpql = "SELECT e FROM Ejemplar e";

            List<Ejemplar> lista = em.createQuery(jpql, Ejemplar.class).getResultList();
    
            Set<Ejemplar> ejemplares = new LinkedHashSet<>(lista);
    
            return ejemplares;
    
        }catch(Exception e){
            throw new JPAException(JpaManagerCdi.getMessageError(e));
        }
    }

    @Override
    public Optional<Ejemplar> selectById(int id) throws JPAException {
        try{
            Ejemplar ejemplar = em.find(Ejemplar.class,id);
            return Optional.ofNullable(ejemplar);
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
    public void save(Ejemplar t) throws JPAException {
        try{
            em.persist(t);
            //em.flush();
        }catch(Exception e){
            throw new JPAException(JpaManagerCdi.getMessageError(e));
        }

    }


}
