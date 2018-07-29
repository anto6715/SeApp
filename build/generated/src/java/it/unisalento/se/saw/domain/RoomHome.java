package it.unisalento.se.saw.domain;
// Generated 29-lug-2018 10.15.09 by Hibernate Tools 5.2.0.Final


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Room.
 * @see it.unisalento.se.saw.domain.Room
 * @author Hibernate Tools
 */
@Stateless
public class RoomHome {

    private static final Log log = LogFactory.getLog(RoomHome.class);

    @PersistenceContext private EntityManager entityManager;
    
    public void persist(Room transientInstance) {
        log.debug("persisting Room instance");
        try {
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(Room persistentInstance) {
        log.debug("removing Room instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        }
        catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }
    
    public Room merge(Room detachedInstance) {
        log.debug("merging Room instance");
        try {
            Room result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Room findById( RoomId id) {
        log.debug("getting Room instance with id: " + id);
        try {
            Room instance = entityManager.find(Room.class, id);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

