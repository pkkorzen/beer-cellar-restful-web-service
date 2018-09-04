package pk.korzen.beercellar;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BeerDAO {
	Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Beer.class);
	SessionFactory sessionFactory = configuration.buildSessionFactory();

	public int addBeer(Beer bean) {
		if (isEntryValid(bean)) {
			return 0;
		} else {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Beer beer = new Beer();
			settingBeerCreateParameters(beer, bean, session);
			session.save(beer);
			tx.commit();
			session.close();
			return 1;
		}
	}

	private void settingBeerCreateParameters(Beer beer, Beer bean, Session session) {
		beer.setName(bean.getName());
		beer.setBrewery(bean.getBrewery());
		beer.setStyle(bean.getStyle());
		beer.setCountry(bean.getCountry());
		beer.setBatch(bean.getBatch());
		beer.setBuyingDate(bean.getBuyingDate());
		beer.setQuantityBought(bean.getQuantityBought());
		beer.setQuantityAvailable(bean.getQuantityAvailable());
		beer.setDrinkingDate(bean.getDrinkingDate());
		beer.setAvailability(bean.getAvailability());
		beer.setComment(bean.getComment());
	}

	private boolean isEntryValid(Beer beer) {
		return beer.getAvailability() == null || beer.getName() == null || beer.getBrewery() == null
				|| beer.getCountry() == null || beer.getStyle() == null || beer.getBuyingDate() == null
				|| beer.getQuantityBought() == null || beer.getQuantityAvailable() == null;
	}
	
	public List<Beer> getBeers() {
		Session session = sessionFactory.openSession();
		TypedQuery query = session.createQuery("from Beer");
		List<Beer> beers = query.getResultList();
		session.close();
		return beers;
	}
	
	public int deleteBeer(int id) {
		if (id <= 0) {
			return 0;
		} else {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			TypedQuery query = session.createQuery("delete from Beer where id=?1");
			query.setParameter(1, id);
			int rowCount = query.executeUpdate();
			System.out.println("Rows affected " + rowCount);
			tx.commit();
			session.close();
			return rowCount;
		}
	}
	
	public int updateBeer(int id, Beer beer) {
		if (id <= 0) {
			return 0;
		} else {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			TypedQuery query = session.createQuery(
					"update Beer set name = :name, brewery = :brewery, style = :style, country = :country, batch = :batch, buyingDate = :buying_date, quantityBought = :quantity_bought, quantityAvailable = :quantity_available, drinkingDate = :drinking_date, availability = :availability, comment = :comment where id= :id");
			settingBeerUpdateParameters(id, beer, query);
			int rowCount = query.executeUpdate();
			System.out.println("Rows affected " + rowCount);
			tx.commit();
			session.close();
			return rowCount;
		}
	}

	private void settingBeerUpdateParameters(int id, Beer beer, TypedQuery query) {
		query.setParameter("id", id);
		query.setParameter("name", beer.getName());
		query.setParameter("brewery", beer.getBrewery());
		query.setParameter("style", beer.getStyle());
		query.setParameter("country", beer.getCountry());
		query.setParameter("batch", beer.getBatch());
		query.setParameter("buying_date", beer.getBuyingDate());
		query.setParameter("quantity_bought", beer.getQuantityBought());
		query.setParameter("quantity_available", beer.getQuantityAvailable());
		query.setParameter("drinking_date", beer.getDrinkingDate());
		query.setParameter("availability", beer.getAvailability());
		query.setParameter("comment", beer.getComment());
	}
}
