import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IDaoRemote;
import entities.Ville;

public class GestionVille {

	public static IDaoRemote<Ville> lookUpEmployeRemote() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (IDaoRemote<Ville>) context.lookup("ejb:ISICEJBEAR/ISICEJBServer/VilleServiceEJB!dao.IDaoRemote");

	}
	public static void main(String[] args) {
		// create a ville 
		CreateVille(new Ville("Asfi"));
		
		// getting villes
		listVilles();
		
		// get a ville
		getVille(1);
		
		// update ville
		Ville v = new Ville();
		v.setId(5);
		v.setNom("Marrakech 1");
		updateVille(v);
		
		// delete ville
		deleteVille(5);
		
	}
	
	private static void CreateVille(Ville ville) {
		try {
			IDaoRemote<Ville> dao = lookUpEmployeRemote();
			dao.create(ville);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
	}
	
	private static void listVilles() {
		try {
			IDaoRemote<Ville> dao = lookUpEmployeRemote();
			for(Ville v : dao.findAll()) {
				System.out.println(v.getNom());
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
	}
	
	private static void getVille(int id) {
		try {
			IDaoRemote<Ville> dao = lookUpEmployeRemote();
			System.out.println(dao.findById(id).getNom());
			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
	}
	
	private static void updateVille(Ville ville) {
		try {
			IDaoRemote<Ville> dao = lookUpEmployeRemote();
			dao.update(ville);
			System.out.println("Ville Updated!");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
	}
	
	private static void deleteVille(int id) {
		try {
			IDaoRemote<Ville> dao = lookUpEmployeRemote();
			dao.delete(dao.findById(id));
			System.out.println("Ville Delete!");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
	}
}
