package pk.korzen.beercellar;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("beers")
public class BeerResource {

	private BeerDAO dao = new BeerDAO();

	public void setDao(BeerDAO dao) {
		this.dao = dao;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Beer> getBeers() {
		return dao.getBeers();
	}

	@POST
	@Path("create")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createBeer(Beer beer) {
		int count = dao.addBeer(beer);
		if(count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}
	}

	@PUT
	@Path("update/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateBeer(@PathParam("id") int id, Beer beer) {
		int count = dao.updateBeer(id, beer);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}
	}

	@DELETE
	@Path("delete/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteBeer(@PathParam("id") int id) {
		int count = dao.deleteBeer(id);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}
	}
}
