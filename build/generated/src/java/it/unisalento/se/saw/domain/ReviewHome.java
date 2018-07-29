package it.unisalento.se.saw.domain;
// Generated 29-lug-2018 10.15.09 by Hibernate Tools 5.2.0.Final


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Review.
 * @see it.unisalento.se.saw.domain.Review
 * @author Hibernate Tools
 */
@Stateless
public class ReviewHome {

    private static final Log log = LogFactory.getLog(ReviewHome.class);

    @PersistenceContext private EntityManager entityManager;
    
    public void persist(Review transientInstance) {
        log.debug("persisting Review instance");
        try {
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(Review persistentInstance) {
        log.debug("removing Review instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        }
        catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }
    
    public Review merge(Review detachedInstance) {
        log.debug("merging Review instance");
        try {
            Review result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Review findById( ReviewId id) {
        log.debug("getting Review instance with id: " + id);
        try {
            Review instance = entityManager.find(Review.class, id);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}
