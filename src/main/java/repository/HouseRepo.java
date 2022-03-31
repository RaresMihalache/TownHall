package repository;

import entity.House;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;

public class HouseRepo {

    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewHouse(String street, int no, String bl, int ap, User user){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("INSERT INTO HOUSE (id, street, no, bl, ap, user_id) " + " VALUES(?, ?, ?, ?, ?, ?)")
                .setParameter(1, UUID.randomUUID().toString())
                .setParameter(2, street)
                .setParameter(3, no)
                .setParameter(4, bl)
                .setParameter(5, ap)
                .setParameter(6, user.getId())
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public List<House> getHousesByUserEmail(String email){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<House> houses = em.createQuery(
                "SELECT h FROM House h WHERE h.user.email = :userEmail", House.class)
                .setParameter("userEmail", email)
                .getResultList();
        for(House h : houses)
            System.out.println(h.toString());
        return houses;
    }

    /**
     *
     * @param id
     * @return -1 -> error   - house with given id NOT FOUND
     *          1 -> success - house with given id FOUND
     */
    public int removeHouse(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        House house = em.find(House.class, id);
        if(house != null)
            em.remove(house);
        else return -1;
        em.getTransaction().commit();
        return 1;
    }

    public House getHouseByData(String street, int no, String bl, int ap){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<House> houses = em.createQuery(
                        "SELECT h FROM House h WHERE h.street = :street " +
                                "AND h.no = :no AND h.bl = :bl AND h.ap = :ap ", House.class)
                .setParameter("street", street)
                .setParameter("no", no)
                .setParameter("bl", bl)
                .setParameter("ap", ap)
                .getResultList();
        House h = null;
        if(houses.size() != 0)
            h = houses.get(0);
        return h;
    }
}
