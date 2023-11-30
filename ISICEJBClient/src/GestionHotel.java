import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IDaoRemote;
import entities.Hotel;
import entities.Ville;

public class GestionHotel {

    public static IDaoRemote<Hotel> lookUpHotelRemote() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        final Context context = new InitialContext(jndiProperties);

        return (IDaoRemote<Hotel>) context.lookup("ejb:ISICEJBEAR/ISICEJBServer/HotelServiceEJB!dao.IDaoRemote");
    }
    
    public static IDaoRemote<Ville> lookUpVilleRemote() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        final Context context = new InitialContext(jndiProperties);

        return (IDaoRemote<Ville>) context.lookup("ejb:ISICEJBEAR/ISICEJBServer/VilleServiceEJB!dao.IDaoRemote");
    }
    
    public static void main(String[] args) {
        // create a hotel
//    	Hotel h = new Hotel();
//    	h.setNom("Sample Hotel 2");
//    	h.setAdresse("Sample Address 2");
//    	h.setTelephone("84483");
//    	h.setVille(getVille(2));
//        createHotel(h);

        // get hotels by ville
        listHotelsByVille(getVille(1));

        // get hotels
        listHotels();

        // get a hotel
        getHotel(1);

        // update hotel
        Hotel hotel = new Hotel();
        hotel.setId(5);
        hotel.setAdresse("updated Address");
        updateHotel(hotel);

        // delete hotel
//        deleteHotel(7);
    }

    private static Ville getVille(int id) {
		try {
			IDaoRemote<Ville> dao = lookUpVilleRemote();
			return dao.findById(id);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;	
	}
    
    private static void createHotel(Hotel hotel) {
        try {
            IDaoRemote<Hotel> dao = lookUpHotelRemote();
            dao.create(hotel);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static void listHotelsByVille(Ville ville) {
        try {
            IDaoRemote<Hotel> dao = lookUpHotelRemote();
            System.out.println("\n");
            System.out.println("Les hotels dans la ville " + ville.getNom() + ": ");
            for (Hotel h : dao.getHotelsByVille(ville)) {
                System.out.println("Hotel: " + h.getNom());
            }
            System.out.println("\n");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static void listHotels() {
        try {
            IDaoRemote<Hotel> dao = lookUpHotelRemote();
            System.out.println("\n");
            System.out.println("La list des hotels: ");
            for (Hotel h : dao.findAll()) {
                System.out.println("Hotel: " + h.getNom() + ", Ville: " + h.getVille().getNom());
            }
            System.out.println("\n");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static void getHotel(int id) {
        try {
            IDaoRemote<Hotel> dao = lookUpHotelRemote();
            System.out.println("\n");
            System.out.println("Les hotel qui on un id " + id  + " est: ");
            System.out.println("Hotel: " + dao.findById(id).getNom() + ", Ville: " + dao.findById(id).getVille().getNom());
            System.out.println("\n");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static void updateHotel(Hotel hotel) {
        try {
            IDaoRemote<Hotel> dao = lookUpHotelRemote();
            Hotel old = dao.findById(hotel.getId());
            if(hotel.getAdresse() == null) {
            	hotel.setAdresse(old.getAdresse());
            }
			 if(hotel.getNom() == null) {
				 hotel.setNom(old.getNom());	
			 }
			 
			 if(hotel.getTelephone() == null) {
				 hotel.setTelephone(old.getTelephone());
			 }
			 
			 if(hotel.getVille() == null) {
				 hotel.setVille(old.getVille());
			 }
            
            dao.update(hotel);
            System.out.println("Hotel Updated!");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static void deleteHotel(int id) {
        try {
            IDaoRemote<Hotel> dao = lookUpHotelRemote();
            dao.delete(dao.findById(id));
            System.out.println("Hotel Deleted!");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
