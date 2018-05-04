package fr.cpasam.leonardo.model.geoloc;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.cpasam.leonardo.model.shop.RetailPoint;
import fr.cpasam.leonardo.model.user.Member;

@NamedQueries({
	@NamedQuery(
	name = Geoloc.FIND_GEOLOC_BY_ID,
	query = "from Geoloc g where g.GEOLOC_ID = :geolocId"
	),
	@NamedQuery(
	name = Geoloc.FIND_ALL_GEOLOC,
	query = "from Geoloc"
	)
})
@Entity
@Table(name="geolocalisations")
public class Geoloc {

	public final static String FIND_GEOLOC_BY_ID = "findGeolocById";
	public final static String FIND_ALL_GEOLOC = "findAllGeolocs";
	
	@Id
	@GeneratedValue
	@Column(name="GEOLOC_ID")
	protected long id;
	
	@Column(name="LAT")
	protected long lat;
	
	@Column(name="LONG")
	protected long longit;
	
	@Column (name = "MEMBERS")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "geoloc")
	List<Member> members ;
	
	@Column (name = "RETAILPOINTS")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "geoloc")
	List<RetailPoint> retailpoints ;

	public Geoloc() {}
	
	public Geoloc(long lat, long longit, List<Member> members, List<RetailPoint> retailpoints) {
		this.lat = lat;
		this.longit = longit;
		this.members = members;
		this.retailpoints = retailpoints;
	}
	
	
}
