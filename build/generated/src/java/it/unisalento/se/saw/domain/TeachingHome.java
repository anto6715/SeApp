package it.unisalento.se.saw.domain;
// Generated 29-lug-2018 10.15.09 by Hibernate Tools 5.2.0.Final


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Teaching.
 * @see it.unisalento.se.saw.domain.Teaching
 * @author Hibernate Tools
 */
@Stateless
public class TeachingHome {

    private static final Log log = LogFactory.getLog(TeachingHome.class);

    @PersistenceContext private EntityManager entityManager;
    
    public void persist(Teaching transientInstance) {
        log.debug("persisting Teaching instance");
        try {
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(Teaching persistentInstance) {
        log.debug("removing Teaching instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        }
        catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }
    
    public Teaching merge(Teaching detachedInstance) {
        log.debug("merging Teaching instance");
        try {
            Teaching result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Teaching findById( TeachingId id) {
        log.debug("getting Teaching instance with id: " + id);
        try {
            Teaching instance = entityManager.find(Teaching.class, id);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

