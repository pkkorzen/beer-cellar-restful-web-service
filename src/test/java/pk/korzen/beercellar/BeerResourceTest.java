package pk.korzen.beercellar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BeerResourceTest {
	
	@Mock
	private BeerDAO dao;
	private Beer beer;
	private BeerResource beerResource;
	
	@Before
	public void setup() {
		beer = new Beer();
		beerResource = new BeerResource();
		beerResource.setDao(dao);
	}
	
	@Test
	public void shouldReturnTrueIfResourceCreateMethodAndDaoAddMethodInvoked(){
		when(dao.addBeer(beer)).thenReturn(1);
		Response response = beerResource.createBeer(beer);
		
		assertEquals(Status.OK, response.getStatusInfo());
		verify(dao).addBeer(beer);
	}
	
	@Test
	public void shouldReturnTrueIfResourceCreateMethodRespondsBadRequest(){
		when(dao.addBeer(beer)).thenReturn(0);
		Response response = beerResource.createBeer(beer);
		
		assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
		verify(dao).addBeer(beer);
	}
	
	@Test
	public void shouldReturnTrueIfThereAreNoBeers(){
		when(dao.getBeers()).thenReturn(Collections.<Beer>emptyList());
		
		List<Beer> result = beerResource.getBeers();
		
		assertTrue(result.isEmpty());
		verify(dao).getBeers();
	}
	
	@Test
	public void shouldReturnTrueIfUpdateMethodRespondsOK(){
		when(dao.updateBeer(1, beer)).thenReturn(1);
		
		Response result = beerResource.updateBeer(1, beer);
		
		assertEquals(Status.OK, result.getStatusInfo());
		verify(dao).updateBeer(1, beer);
	}
	
	@Test
	public void shouldReturnTrueIfUpdateMethodRespondsBadReuquest(){
		when(dao.updateBeer(0, beer)).thenReturn(0);
		
		Response result = beerResource.updateBeer(0, beer);
		
		assertEquals(Status.BAD_REQUEST, result.getStatusInfo());
		verify(dao).updateBeer(0, beer);
	}
	
	@Test
	public void shouldReturnTrueIfDeleteMethodRespondsOK(){
		when(dao.deleteBeer(2)).thenReturn(2);
		
		Response result = beerResource.deleteBeer(2);
		
		assertEquals(Status.OK, result.getStatusInfo());
		verify(dao).deleteBeer(2);
	}
	
	@Test
	public void shouldReturnTrueIfDeleteMethodRespondsBadRequest(){
		when(dao.deleteBeer(0)).thenReturn(0);
		
		Response result = beerResource.deleteBeer(0);
		
		assertEquals(Status.BAD_REQUEST, result.getStatusInfo());
		verify(dao).deleteBeer(0);
	}

}
