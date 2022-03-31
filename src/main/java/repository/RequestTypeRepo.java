package repository;

import entity.RequestType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;

public class RequestTypeRepo {
    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public List<RequestType> findAll(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<RequestType> requestTypes = em.createQuery(
                "SELECT r FROM RequestType r", RequestType.class)
                .getResultList();
        return requestTypes;
    }

    public RequestType findReqByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<RequestType> requestTypes = em.createQuery(
                "SELECT r FROM RequestType r WHERE r.name = :name", RequestType.class)
                .setParameter("name", name)
                .getResultList();
        RequestType returnReqType = null;
        if(requestTypes.size() != 0){
            returnReqType = requestTypes.get(0);
        }
        return  returnReqType;
    }

    public void createNewReqType(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("INSERT INTO REQUEST_TYPE(id, name) " + " VALUES(?, ?)")
                .setParameter(1, UUID.randomUUID().toString())
                .setParameter(2, name)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public int deleteReqType(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        RequestType requestType = em.find(RequestType.class, id);
        if(requestType != null)
            em.remove(requestType);
        else return -1;
        em.getTransaction().commit();
        return 1;
    }
}
