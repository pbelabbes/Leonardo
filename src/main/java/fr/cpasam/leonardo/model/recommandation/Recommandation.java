package fr.cpasam.leonardo.model.recommandation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import fr.cpasam.leonardo.model.user.Member;

@NamedQueries({
	@NamedQuery(
	name = "findRecommandationById",
	query = "from Recommandation r where r.RECOMMANDATION_ID = :recommandationId"
	),
	@NamedQuery(
	name = "findAllRecommandations",
	query = "from Recommandation"
	)
})
@Entity
@Table(name = "recommandations")
public class Recommandation {
	
	@Id
	@GeneratedValue
	@Column(name = "RECOMMANDATION_ID")
	private long id;
	
	@Column(name="RECOMMANDATION_GRADE")
	private int grade;
	
	@Column(name = "RECOMMANDATION_COMMENT")
	private String comment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MEMBER_ID")
	private Member member;

	public Recommandation(int grade, String comment, Member member) {
		this.grade = grade;
		this.comment = comment;
		this.member = member;
	}
	
	
}