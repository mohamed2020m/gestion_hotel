package services;

import java.util.List;

import dao.IDaoLocale;
import dao.IDaoRemote;
import entities.Hotel;
import entities.Ville;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


@Stateless(name = "HotelServiceEJB")
public class HotelService implements IDaoRemote<Hotel>, IDaoLocale<Hotel> {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Hotel create(Hotel o) {
		em.persist(o);
		return o;
	}

	@Override
	public boolean delete(Hotel o) {
		if (!em.contains(o)) {
	        o = em.merge(o); 
	    }
		em.remove(o);
		return true;
	}

	@Override
	public Hotel update(Hotel o) {
		return em.merge(o);
	}

	@Override
	public Hotel findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Hotel.class, id);
	}

	@Override
	public List<Hotel> findAll() {
		Query query = em.createQuery("select h from Hotel h");
		return query.getResultList();
	}
	
	@Override
    public List<Hotel> getHotelsByVille(Ville ville) {
        TypedQuery<Hotel> query = em.createQuery("SELECT h FROM Hotel h WHERE h.ville = :ville", Hotel.class);
        query.setParameter("ville", ville);
        return query.getResultList();
    }


}
