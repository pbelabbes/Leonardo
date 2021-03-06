package fr.cpasam.leonardo.model.geoloc;

import java.util.List;

import fr.cpasam.leonardo.model.shop.RetailPoint;
import fr.cpasam.leonardo.model.user.Member;

public class Geoloc {
	
	protected long id;
	protected long lat;
	protected long longit;
	List<Member> members ;
	List<RetailPoint> retailpoints ;

	public Geoloc() {}
	
	/**
	 * Constructeur 5 paramètres
	 * @param id
	 * @param lat
	 * @param longit
	 * @param members
	 * @param retailpoints
	 */
	public Geoloc(long id, long lat, long longit, List<Member> members, List<RetailPoint> retailpoints) {
		this.id = id;
		this.lat = lat;
		this.longit = longit;
		this.members = members;
		this.retailpoints = retailpoints;
	}

	/**
	 * Retourne l'id d'une geolocalisation
	 * @return id
	 */
	public long getId () {return id;}
	/**
	 * Retourne la latitude d'une geolocalisation
	 * @return lat
	 */
	public long getLat () {return lat;}
	/**
	 * Retourne la longitude d'une geolocalisation
	 * @return longit
	 */
	public long getLongit () {return longit;}
	/**
	 * Retourne la liste des membres d'une geolocalisation
	 * @return members
	 */
	public List<Member> getMembers () {return members;}
	/**
	 * Retourne la liste des points de vente d'une geolocalisation
	 * @return retailpoints
	 */
	public List<RetailPoint> getRetailpoints () {return retailpoints;}
	
	
	/**
	 * Met à jour l'id d'une geolocalisation
	 * @param id
	 */
	public void setId (long id) {this.id=id;}
	/**
	 * Met à jour la latitude d'une geolocalisation
	 * @param lat
	 */
	public void setLat (long lat) {this.lat=lat;}
	/**
	 * Met à jour la longitude d'une geolocalisation
	 * @param longit
	 */
	public void setLongit (long longit) {this.longit= longit;}
	/**
	 * Met à jour la liste des membres d'une geolocalisation
	 * @param members
	 */
	public void setMembers (List<Member> members) {this.members= members;}
	/**
	 * Met à jour la liste des points de vente d'une geolocalisation
	 * @param retailpoints
	 */
	public void setRetailPoints(List<RetailPoint> retailpoints) {this.retailpoints=retailpoints;}
}
