package it.unisalento.se.saw.domain;
// Generated 29-lug-2018 10.15.09 by Hibernate Tools 5.2.0.Final


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Lesson.
 * @see it.unisalento.se.saw.domain.Lesson
 * @author Hibernate Tools
 */
@Stateless
public class LessonHome {

    private static final Log log = LogFactory.getLog(LessonHome.class);

    @PersistenceContext private EntityManager entityManager;
    
    public void persist(Lesson transientInstance) {
        log.debug("persisting Lesson instance");
        try {
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(Lesson persistentInstance) {
        log.debug("removing Lesson instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        }
        catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }
    
    public Lesson merge(Lesson detachedInstance) {
        log.debug("merging Lesson instance");
        try {
            Lesson result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Lesson findById( int id) {
        log.debug("getting Lesson instance with id: " + id);
        try {
            Lesson instance = entityManager.find(Lesson.class, id);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

