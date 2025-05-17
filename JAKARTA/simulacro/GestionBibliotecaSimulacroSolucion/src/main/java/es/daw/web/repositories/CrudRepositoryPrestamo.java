package es.daw.web.repositories;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import es.daw.web.entities.Prestamo;
import es.daw.web.exceptions.JPAException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@RequestScoped
public class CrudRepositoryPrestamo implements CrudRepository<Prestamo>{

    @Inject
    EntityManager em;

    @Override
    public Set<Prestamo> select() throws JPAException {
        try{
            String jpql = "SELECT p FROM Prestamo p";

            List<Prestamo> lista = em.createQuery(jpql, Prestamo.class).getResultList();
    
            Set<Prestamo> prestamos = new LinkedHashSet<>(lista);
    
            return prestamos;
    
        }catch(Exception e){
            throw new JPAException(JpaManagerCdi.getMessageError(e));

        }
    }

    @Override
    public Optional<Prestamo> selectById(int id) throws JPAException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public void deleteById(int id) throws JPAException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    @Transactional
    public void save(Prestamo p) throws JPAException {
        try{
            em.persist(p);
            //em.flush();
        }catch(Exception e){
            throw new JPAException(JpaManagerCdi.getMessageError(e));
        }

    }

}
