package fr.cpasam.leonardo.model.shop;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import fr.cpasam.leonardo.model.geoloc.Geoloc;

@Entity
@Table(name="retailpoints")
public class RetailPoint {

	@Id
	@GeneratedValue
	@Column(name="RETAILPOINT_ID")
	protected long id;
	
	@NotNull
	@Column(name = "NAME")
	@Length(min=3)
	protected String name;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="GEOLOC_ID")
	private Geoloc geoloc;
	
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SHOP_ID")
	protected Shop shop;
	
	@Column (name = "SHOPS")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "retailpoint")
	List<Shop> shops ;
	
	
}
