package services;

import java.util.List;

import dao.IDaoLocale;
import dao.IDaoRemote;
import entities.Ville;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name = "VilleServiceEJB")
public class VilleService implements IDaoRemote<Ville>, IDaoLocale<Ville> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Ville create(Ville o) {
		em.persist(o);
		return o;
	}

	@Override
	public boolean delete(Ville o) {
		if (!em.contains(o)) {
	        o = em.merge(o); // Merge the detached entity to make it managed
	    }
		em.remove(o);
		return true;
	}

	@Override
	public Ville update(Ville o) {
		return em.merge(o);
	}

	@Override
	public Ville findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Ville.class, id);
	}

	@Override
	public List<Ville> findAll() {
		Query query = em.createQuery("select v from Ville v");
		return query.getResultList();
	}

	@Override
	public List<Ville> getHotelsByVille(Ville ville) {
		return null;
	}

}
