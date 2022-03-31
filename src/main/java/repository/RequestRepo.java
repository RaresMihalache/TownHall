package repository;

import entity.House;
import entity.Request;
import entity.RequestType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;

public class RequestRepo {
    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");


    public List<Request> getRequestsByUserEmail(String email){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Request> requests = em.createQuery(
                "SELECT r FROM Request r JOIN House h" +
                        " ON r.house.id = h.id JOIN User u " +
                        "ON h.user.id = u.id WHERE u.email = :userEmail")
                .setParameter("userEmail", email)
                .getResultList();
        for(Request r : requests)
            System.out.println(r.toString());
        return requests;
    }

    public void addRequest(RequestType reqTypeName, String details, House house){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("INSERT INTO REQUEST (id, date, details, house_id, type_id, approveStatus) " + " VALUES (?, ?, ?, ?, ?, ?)")
                .setParameter(1, UUID.randomUUID().toString())
                .setParameter(2, Request.sdf.format(Request.dt))
                .setParameter(3, details)
                .setParameter(4, house.getId())
                .setParameter(5, reqTypeName.getId())
                .setParameter(6, "no")
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public int checkNoReqTypeOnHouse(String houseId, String reqTypeId){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();;
        List<Request> requests = em.createQuery("SELECT r FROM Request r JOIN House h " +
                "ON r.house.id =  h.id JOIN RequestType rt ON " +
                "r.requestType.id = rt.id WHERE " +
                "h.id = :houseId AND rt.id = :reqTypeId")
                .setParameter("houseId", houseId)
                .setParameter("reqTypeId", reqTypeId)
                .getResultList();
        return requests.size();
    }

    public int updateRequest(String reqId, RequestType requestType, String details, House house){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Request request = em.find(Request.class, reqId);
        if(request == null)
            return -1;
        request.setRequestType(requestType);
        request.setDate(Request.sdf.format(Request.dt));
        request.setDetails(details);
        request.setHouse(house);

        em.merge(request);
        em.getTransaction().commit();
        em.close();
        return 1;
    }

    public int approveReq(String reqId){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Request request = em.find(Request.class, reqId);
        if(request == null)
            return -1;
        request.setApproveStatus("yes");
        em.merge(request);
        em.getTransaction().commit();
        em.close();
        return 1;
    }

    public int deleteRequest(String reqId){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Request request = em.find(Request.class, reqId);
        if(request != null)
            em.remove(request);
        else return -1;
        em.getTransaction().commit();
        return 1;

    }

    public List<Request> getAllRequests(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Request> requests = em.createQuery(
                "SELECT r FROM Request r")
                .getResultList();
        return requests;
    }

    public List<Request> filterByDate(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Request> requests = em.createQuery(
                "SELECT r FROM Request r ORDER BY r.date ASC")
                .getResultList();
        System.out.println(requests.size());
        return requests;
    }

    public List<Request> filterByReqType(String reqType){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Request> requests = em.createQuery(
                "SELECT r FROM Request r " +
                        "WHERE r.requestType.name = :reqType " +
                        "ORDER BY r.date ASC")
                .setParameter("reqType", reqType)
                .getResultList();
        System.out.println(requests.size());
        return requests;
    }

}
