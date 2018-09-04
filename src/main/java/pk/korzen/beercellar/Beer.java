package pk.korzen.beercellar;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Beer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=50, nullable=false)
	private String name;
	@Column(length=50, nullable=false)
	private String brewery;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Style style;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Country country;
	private LocalDate batch;
	@Column(name="buying_date", nullable=false)
	private LocalDate buyingDate;
	@Column(name="quantity_bought", nullable=false)
	private int quantityBought;
	@Column(name="quantity_available", nullable=false)
	private int quantityAvailable;
	@Column(name="drinking_date")
	private LocalDate drinkingDate;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Availability availability;
	private String comment;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrewery() {
		return brewery;
	}
	public void setBrewery(String brewery) {
		this.brewery = brewery;
	}
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public LocalDate getBatch() {
		return batch;
	}
	public void setBatch(LocalDate batch) {
		this.batch = batch;
	}
	public LocalDate getBuyingDate() {
		return buyingDate;
	}
	public void setBuyingDate(LocalDate buyingDate) {
		this.buyingDate = buyingDate;
	}
	public int getQuantityBought() {
		return quantityBought;
	}
	public void setQuantityBought(int quantityBought) {
		this.quantityBought = quantityBought;
	}
	public int getQuantityAvailable() {
		return quantityAvailable;
	}
	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	public LocalDate getDrinkingDate() {
		return drinkingDate;
	}
	public void setDrinkingDate(LocalDate drinkingDate) {
		this.drinkingDate = drinkingDate;
	}
	public Availability getAvailability() {
		return availability;
	}
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
