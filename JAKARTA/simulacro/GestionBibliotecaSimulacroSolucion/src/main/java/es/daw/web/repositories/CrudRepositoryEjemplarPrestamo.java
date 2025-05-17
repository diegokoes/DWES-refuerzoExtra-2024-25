package es.daw.web.repositories;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import es.daw.web.entities.EjemplarPrestamo;
import es.daw.web.exceptions.JPAException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@RequestScoped
public class CrudRepositoryEjemplarPrestamo implements CrudRepository<EjemplarPrestamo>{

    @Inject
    EntityManager em;

    // ----------- INYECCIÓN  POR CONSTRUCTOR -----
    // final EntityManager em;

    // @Inject
    // public CrudRepositoryEjemplarPrestamo(EntityManager em){
    //     this.em=em;

    // }
    //---------------------


    @Override
    // Podría haberlo llamado en la interface CrudRepository findAll
    public Set<EjemplarPrestamo> select() throws JPAException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'select'");
    }

    @Override
    public Optional<EjemplarPrestamo> selectById(int id) throws JPAException {
        // ESTE MÉTODO DEVUELVE UN ÚNICO EJEMPLAR DE UN PRÉSTAMO Y NUESTRA APLICACIÓN
        // SOPORTA QUE UN USUARIO SE LLEVE PRESTADO MÁS DE UN EJEMPLAR...

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public void deleteById(int id) throws JPAException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    /**
     * Save hay que usarlo tanto para actualizar como insertar...
     */
    @Transactional
    public void save(EjemplarPrestamo ep) throws JPAException {
        // Si existe el ep, hago merge, si no existe hago persist..
        try{
            if (ep.getFechaLimiteDevolucion() == null){
                System.out.println("********** enchufo plus!!!!");
                ep.setFechaLimiteDevolucion(LocalDate.now().plusDays(15));
            }
                

            System.out.println("************ EJEMPLAR PRÉSTAMO JUSTO ANTES DEL SAVE !!! **********");
            System.out.println(ep);
            System.out.println("**************************");


            //em.merge(ep); // merge hace un persist si no existe, si no update

            if (ep.getId() == null){
                em.persist(ep);
                System.out.println("----------> persist EjemplarPrestamo!!!!");
            }                
            else{
                em.merge(ep);
                System.out.println("----------> merge EjemplarPrestamo!!!!");
            }
                
            em.flush();
        }catch(Exception e){
            throw new JPAException(JpaManagerCdi.getMessageError(e));
        }

    }

    /**
     * Método  nuevo para obtener todos los ejemplares de un préstamo
     * @param idPrestamo
     * @return
     * @throws JPAException
     */
    public Set<EjemplarPrestamo> findEjemplaresByPrestamoId(Long idPrestamo) throws JPAException{
        try{
            String jpql = "SELECT ep FROM EjemplarPrestamo ep WHERE ep.prestamo.id = :idPrestamo";
            List<EjemplarPrestamo> lista = em.createQuery(jpql, EjemplarPrestamo.class)
                .setParameter("idPrestamo",idPrestamo)
                .getResultList();
    
                
            return new LinkedHashSet<>(lista);
    
        }catch(Exception e){
            throw new JPAException(JpaManagerCdi.getMessageError(e));
        }
    }

}
