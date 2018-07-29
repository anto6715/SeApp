package it.unisalento.se.saw.domain;
// Generated 29-lug-2018 10.15.09 by Hibernate Tools 5.2.0.Final


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Segnalation.
 * @see it.unisalento.se.saw.domain.Segnalation
 * @author Hibernate Tools
 */
@Stateless
public class SegnalationHome {

    private static final Log log = LogFactory.getLog(SegnalationHome.class);

    @PersistenceContext private EntityManager entityManager;
    
    public void persist(Segnalation transientInstance) {
        log.debug("persisting Segnalation instance");
        try {
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(Segnalation persistentInstance) {
        log.debug("removing Segnalation instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        }
        catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }
    
    public Segnalation merge(Segnalation detachedInstance) {
        log.debug("merging Segnalation instance");
        try {
            Segnalation result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Segnalation findById( SegnalationId id) {
        log.debug("getting Segnalation instance with id: " + id);
        try {
            Segnalation instance = entityManager.find(Segnalation.class, id);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

