package it.unisalento.se.saw.domain;
// Generated 29-lug-2018 10.15.09 by Hibernate Tools 5.2.0.Final


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Accessory.
 * @see it.unisalento.se.saw.domain.Accessory
 * @author Hibernate Tools
 */
@Stateless
public class AccessoryHome {

    private static final Log log = LogFactory.getLog(AccessoryHome.class);

    @PersistenceContext private EntityManager entityManager;
    
    public void persist(Accessory transientInstance) {
        log.debug("persisting Accessory instance");
        try {
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(Accessory persistentInstance) {
        log.debug("removing Accessory instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        }
        catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }
    
    public Accessory merge(Accessory detachedInstance) {
        log.debug("merging Accessory instance");
        try {
            Accessory result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Accessory findById( AccessoryId id) {
        log.debug("getting Accessory instance with id: " + id);
        try {
            Accessory instance = entityManager.find(Accessory.class, id);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

