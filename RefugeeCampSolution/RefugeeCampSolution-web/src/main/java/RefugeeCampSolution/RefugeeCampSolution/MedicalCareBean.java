package RefugeeCampSolution.RefugeeCampSolution;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.print.attribute.standard.DateTimeAtCreation;

import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.PieChartModel;

import tn.esprit.persistence.Consultation;
import tn.esprit.persistence.ConsultationPK;
import tn.esprit.persistence.MedecinDisponibilite;
import tn.esprit.persistence.MedicalCare;
import tn.esprit.persistence.statmedical;
import tn.esprit.service.MedecinDisponibiliteLocal;
import tn.esprit.service.MedicalCareLocal;




@ManagedBean(name="meBean")
@SessionScoped
public class MedicalCareBean {
	@EJB
	private MedicalCareLocal medicalCareLocal;
	@EJB
    private MedecinDisponibiliteLocal medecindisolocal;
	
	public MedicalCare medicalCare = new MedicalCare();
	public MedicalCare medicalCareSelected = new MedicalCare();
	private List<MedicalCare> medicalCares = new ArrayList<>();
	private MedecinDisponibilite medecindispo = new MedecinDisponibilite();
	private UploadedFile uploadedFile;
	private Boolean formList = true;
	private Boolean formlistspon=false;
	private Boolean formEdit = false;
	private Boolean formDetails = false;
	private PieChartModel pie;
	
	public PieChartModel getPie() {
		return pie;
	}
	public void setPie(PieChartModel pie) {
		this.pie = pie;
	}
	public MedicalCare getMedicalCare() {
		return medicalCare;
	}
	public MedicalCareBean() {
		
	}
	
	public Boolean getFormList() {
		return formList;
	}
	public void setFormList(Boolean formList) {
		this.formList = formList;
	}
	public Boolean getFormlistspon() {
		return formlistspon;
	}
	public void setFormlistspon(Boolean formlistspon) {
		this.formlistspon = formlistspon;
	}
	public Boolean getFormEdit() {
		return formEdit;
	}
	public MedecinDisponibilite getMedecindispo() {
		return medecindispo;
	}
	public void setMedecindispo(MedecinDisponibilite medecindispo) {
		this.medecindispo = medecindispo;
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
	public void setMedicalCare(MedicalCare medicalCare) {
		this.medicalCare = medicalCare;
	}
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	public MedicalCare getMedicalCareSelected() {
		return medicalCareSelected;
	}
	public void setMedicalCareSelected(MedicalCare medicalCareSelected) {
		this.medicalCareSelected = medicalCareSelected;
	}
	public List<MedicalCare> getMedicalCares() {
		return medicalCares;
	}
	public void setMedicalCares(List<MedicalCare> medicalCares) {
		this.medicalCares = medicalCares;
	}
	@PostConstruct
	public void init() {

		pie =new PieChartModel();
			medicalCares = medicalCareLocal.GetAllMedicalCare();
			 List<statmedical> listst = new 

					 ArrayList<statmedical>();
					         listst=medicalCareLocal.getlistmedical();
					         for (statmedical statLeave : listst) {
					 			pie.set(statLeave.getName(), 

					 statLeave.getValue());
					 		}
					        
					        pie.setTitle("Competition/SkiArea");
					        pie.setLegendPosition("e");
					        pie.setFill(true);
					        pie.setShowDataLabels(true);
					        pie.setDiameter(200);
			
			
		
	}
	public String doAddOrSaveMedicalCare() {
           medicalCare.setImg(uploadedFile.getFileName());
		

		byte[] bytes = uploadedFile.getContents();
		OutputStream out;
		try {
			out = new FileOutputStream(
					"C:/Users/LENOVO/workspacejee/Ski/Ski-web/src/main/webapp/resources/assets/layouts/layout3/img/" + uploadedFile.getFileName());
			out.write(bytes);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		MedicalCare ma=new MedicalCare(1,medicalCare.getSPC(),medicalCare.getImg(),0);
	
		Date db= new Date("11/01/2018");
		Date df=new Date("11/01/2019");
		
		MedecinDisponibilite md=new MedecinDisponibilite();
		md.setDateDebut(medecindispo.getDateDebut());
		md.setDateFin(medecindispo.getDateFin());
	
		md.setMedicalCare(ma);
		medicalCareLocal.saveOrupdate(ma);
	
		System.out.println(ma.getUserID());
	
		medecindisolocal.saveOrupdate(md);
		
		
		medicalCares = medicalCareLocal.GetAllMedicalCare();
		return "/pages/AfficherDoctor?faces-redirect=true";

	}
	
	public String doDeleteMedical(MedicalCare medicalCare) {
		medicalCareLocal.deleteMedicalCare(medicalCare);
		medicalCares = medicalCareLocal.GetAllMedicalCare();
		return "/pages/AfficherDoctor?faces-redirect=true";

	}
	public String selectEditCompetition(MedicalCare medicalCare) {
		this.medicalCareSelected = medicalCare;
		medicalCareSelected.setActive(1);
		medicalCareLocal.saveOrupdate(medicalCareSelected);
		medicalCares = medicalCareLocal.GetAllMedicalCare();
		return "/pages/AfficherDoctor?faces-redirect=true";
		/*
		formEdit = true;
		formList = false;
		formDetails = false;
		*/
		

	}
	public String selectEditDesactive(MedicalCare medicalCare)
	{this.medicalCareSelected = medicalCare;
	medicalCareSelected.setActive(0);
	medicalCareLocal.saveOrupdate(medicalCareSelected);
	medicalCares = medicalCareLocal.GetAllMedicalCare();
		return "/pages/AfficherDoctor?faces-redirect=true";
	}
	public String doAddOrSavemedicalEdit() {
		

	medicalCareLocal.saveOrupdate(medicalCareSelected);

	formEdit = false;
	formList = true;
	medicalCares = medicalCareLocal.GetAllMedicalCare();
	return "C";
				
		}
private void createpie() {
        
        List<statmedical> listst = new 

ArrayList<statmedical>();
        listst=medicalCareLocal.getlistmedical();
        for (statmedical statLeave : listst) {
			pie.set(statLeave.getName(), 

statLeave.getValue());
		}
       
       pie.setTitle("aaaaaa/zzzzzz");
       pie.setLegendPosition("e");
       pie.setFill(true);
       pie.setShowDataLabels(true);
       pie.setDiameter(200);
       }
	public String gotoChart(){
		String navigateTo=null;
		createpie();
		navigateTo="chart.xhtml";
		return navigateTo;
	}
	


}
