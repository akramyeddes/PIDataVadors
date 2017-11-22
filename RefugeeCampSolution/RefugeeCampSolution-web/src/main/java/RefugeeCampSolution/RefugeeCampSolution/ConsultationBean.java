package RefugeeCampSolution.RefugeeCampSolution;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.persistence.Consultation;
import tn.esprit.persistence.ConsultationPK;
import tn.esprit.persistence.MedecinDisponibilite;
import tn.esprit.persistence.MedicalCare;
import tn.esprit.persistence.Refug;
import tn.esprit.service.ConsultationLocal;
import tn.esprit.service.ConsultationService;

@ManagedBean(name = "ConsultationBean")
@SessionScoped
public class ConsultationBean {
	@EJB
	private ConsultationLocal consultationservice;

	private Consultation consultation = new Consultation();
	private Consultation consultationSelected = new Consultation();
	private List<Consultation> consultations = new ArrayList<>();
	private List<Refug> refugees = new ArrayList<>();
	private List<Integer> refugeesId = new ArrayList<>();
	private List<Integer> medId = new ArrayList<>();
	private int testid = 0;
	private String tt;
	private Date date = new Date();
	private int medecinid;
	private int a;
	private Boolean formList = true;
	private Boolean formlistspon = false;
	private Boolean formEdit = false;
	private Boolean formDetails = false;

	public Boolean getFormList() {
		return formList;
	}

	public void setFormList(Boolean formList) {
		this.formList = formList;
	}

	public Boolean getFormlistspon() {
		return formlistspon;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public void setFormlistspon(Boolean formlistspon) {
		this.formlistspon = formlistspon;
	}

	public Boolean getFormEdit() {
		return formEdit;
	}

	public void setFormEdit(Boolean formEdit) {
		this.formEdit = formEdit;
	}

	public Boolean getFormDetails() {
		return formDetails;
	}

	public void setFormDetails(Boolean formDetails) {
		this.formDetails = formDetails;
	}

	public Consultation getConsultation() {
		return consultation;
	}

	public List<Integer> getMedId() {
		return medId;
	}

	public void setMedId(List<Integer> medId) {
		this.medId = medId;
	}

	public int getMedecinid() {
		return medecinid;
	}

	public void setMedecinid(int medecinid) {
		this.medecinid = medecinid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Integer> getRefugeesId() {
		return refugeesId;
	}

	public void setRefugeesId(List<Integer> refugeesId) {
		this.refugeesId = refugeesId;
	}

	public String getTt() {
		return tt;
	}

	public void setTt(String tt) {
		this.tt = tt;
	}

	public int getTestid() {
		return testid;
	}

	public void setTestid(int testid) {
		this.testid = testid;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public Consultation getConsultationSelected() {
		return consultationSelected;
	}

	public void setConsultationSelected(Consultation consultationSelected) {
		this.consultationSelected = consultationSelected;
	}

	public List<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	public List<Refug> getRefugees() {
		return refugees;
	}

	public void setRefugees(List<Refug> refugees) {
		this.refugees = refugees;
	}

	@PostConstruct
	public void init() {

		setRefugees(consultationservice.GetAllRefugee());
		setRefugeesId(consultationservice.GetAllRefugeeID());
		setMedId(consultationservice.GetAllMedecinId());

		consultations = consultationservice.GetAllConsultation();
/*
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			Date a = simpleDateFormat.parse("2018-11-11");
			List<MedicalCare> m = consultationservice.getByDate(a);
			for (MedicalCare aa : m) {
				System.out.println(aa.getMedecinID());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

	}

	public String ToDoctor() {

		 a = Integer.parseInt(tt);
		// System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+tt);
		// testid=Integer.valueOf(tt);
		// testid=Integer.decode(tt).intValue();
		// System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiintntntntntntntntntntnt
		// "+a);
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String datesimple = simpleDateFormat.format(date);
		System.out.println("dddddddddddddddddddddddd    " + date);

		System.out.println(datesimple);
		return "/pages/AjouterDoctorConsultation?faces-redirect=true";

	}

	public String AddConsultation() {
		ConsultationPK cpk = new ConsultationPK();
		cpk.setDateConsultation(date);
		cpk.setMedecinID(medecinid);
		cpk.setRefugID(a);
		Consultation con = new Consultation(cpk, "test", "tesst");
		consultationservice.saveOrupdate(con);

		consultations = consultationservice.GetAllConsultation();
		return "/pages/AfficherConsultation?faces-redirect=true";

	}

	public String DeleteConsultation(Consultation con) {

		consultationservice.deleteConsultation(con);

		consultations = consultationservice.GetAllConsultation();
		return "/pages/AfficherConsultation?faces-redirect=true";

	}

	public String selectEditDispo(Consultation con) {
		this.consultationSelected = con;
		formEdit = true;
		formList = false;
		formDetails = false;
		return null;

	}

	public String doAddOrSaveMedicalCare() {
		consultationservice.saveOrupdate(consultationSelected);

		formEdit = false;
		formlistspon = false;
		formList = true;

		return "/pages/AfficherConsultation?faces-redirect=true";

	}

	public String UpdateConsultation(Consultation con) {

		consultationservice.saveOrupdate(con);

		consultations = consultationservice.GetAllConsultation();
		return "/pages/AfficherConsultation?faces-redirect=true";

	}

}
