package it.unisalento.se.saw.domain;
// Generated 29-lug-2018 10.15.09 by Hibernate Tools 5.2.0.Final


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Student.
 * @see it.unisalento.se.saw.domain.Student
 * @author Hibernate Tools
 */
@Stateless
public class StudentHome {

    private static final Log log = LogFactory.getLog(StudentHome.class);

    @PersistenceContext private EntityManager entityManager;
    
    public void persist(Student transientInstance) {
        log.debug("persisting Student instance");
        try {
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void remove(Student persistentInstance) {
        log.debug("removing Student instance");
        try {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        }
        catch (RuntimeException re) {
            log.error("remove failed", re);
            throw re;
        }
    }
    
    public Student merge(Student detachedInstance) {
        log.debug("merging Student instance");
        try {
            Student result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public Student findById( StudentId id) {
        log.debug("getting Student instance with id: " + id);
        try {
            Student instance = entityManager.find(Student.class, id);
            log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

