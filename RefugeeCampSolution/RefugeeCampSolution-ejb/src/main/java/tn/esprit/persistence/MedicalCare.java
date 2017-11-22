package tn.esprit.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MedicalCares database table.
 * 
 */
@Entity
@Table(name="MedicalCares")
public class MedicalCare implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="MedecinID")
	private int medecinID;

	@Column(name="UserID")
	private int userID;

	@Column(name="SPC")
	private String spc;

	@Column(name="Img")
	private String img;

	@Column(name="Active")
	private int active;


	/*
	//bi-directional many-to-one association to MedecinDisponibilite
	@OneToMany(mappedBy="medicalCare")
	private List<MedecinDisponibilite> medecinDisponibilites;
	*/
/*
	//bi-directional many-to-one association to Consultation
	@OneToMany(mappedBy="medicalCare")
	private List<Consultation> consultations;
*/
	public MedicalCare() {
	}

	
	public MedicalCare(int userID, String sPC, String img, int active) {
		super();
		userID = userID;
		spc = sPC;
		this.img = img;
		this.active = active;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMedecinID() {
		return this.medecinID;
	}

	public void setMedecinID(int medecinID) {
		this.medecinID = medecinID;
	}

	

/*
	public List<MedecinDisponibilite> getMedecinDisponibilites() {
		return this.medecinDisponibilites;
	}

	public void setMedecinDisponibilites(List<MedecinDisponibilite> medecinDisponibilites) {
		this.medecinDisponibilites = medecinDisponibilites;
	}
*/
	/*
	public MedecinDisponibilite addMedecinDisponibilite(MedecinDisponibilite medecinDisponibilite) {
		getMedecinDisponibilites().add(medecinDisponibilite);
		medecinDisponibilite.setMedicalCare(this);

		return medecinDisponibilite;
	}

	public MedecinDisponibilite removeMedecinDisponibilite(MedecinDisponibilite medecinDisponibilite) {
		getMedecinDisponibilites().remove(medecinDisponibilite);
		medecinDisponibilite.setMedicalCare(null);

		return medecinDisponibilite;
	}
*/
	/*
	public List<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	public Consultation addConsultation(Consultation consultation) {
		getConsultations().add(consultation);
		consultation.setMedicalCare(this);

		return consultation;
	}

	public Consultation removeConsultation(Consultation consultation) {
		getConsultations().remove(consultation);
		consultation.setMedicalCare(null);

		return consultation;
	}
	*/

	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		userID = userID;
	}


	public String getSPC() {
		return spc;
	}


	public void setSPC(String sPC) {
		spc = sPC;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

}