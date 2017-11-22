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

import tn.esprit.persistence.MedecinDisponibilite;
import tn.esprit.persistence.MedicalCare;
import tn.esprit.service.MedecinDisponibiliteLocal;
@ManagedBean(name="DispoBean")
@SessionScoped
public class DispoBean {
	@EJB
	private MedecinDisponibiliteLocal mdl;
	private MedecinDisponibilite md;
	private MedecinDisponibilite mdselected;
	private List<MedecinDisponibilite> mds = new ArrayList<>();
	private Boolean formList = true;
	private Boolean formlistspon=false;
	private Boolean formEdit = false;
	private Boolean formDetails = false;
	
	public DispoBean()
	{
		
	}

	public MedecinDisponibilite getMdselected() {
		return mdselected;
	}

	public void setMdselected(MedecinDisponibilite mdselected) {
		this.mdselected = mdselected;
	}

	public MedecinDisponibilite getMd() {
		return md;
	}

	public void setMd(MedecinDisponibilite md) {
		this.md = md;
	}

	public List<MedecinDisponibilite> getMds() {
		return mds;
	}

	public void setMds(List<MedecinDisponibilite> mds) {
		this.mds = mds;
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

	public void setFormEdit(Boolean formEdit) {
		this.formEdit = formEdit;
	}

	public Boolean getFormDetails() {
		return formDetails;
	}

	public void setFormDetails(Boolean formDetails) {
		this.formDetails = formDetails;
	}
	@PostConstruct
	public void init() {

		
			mds = mdl.GetAllDispo();
	
	}
	public String doAddOrSavemedicalEdit() {
		

		mdl.saveOrupdate(mdselected);

		formEdit = false;
		formList = true;
		mds = mdl.GetAllDispo();
		return "C";
					
			}
	public String selectEditDispo(MedecinDisponibilite medicalCare) {
		this.mdselected = medicalCare;
		formEdit = true;
		formList = false;
		formDetails = false;
		return null;

	}
	public String selectAddDispo() {
		
		
		return "/pages/AjouterDispo?faces-redirect=true";

	}
	public String doAddOrSaveMedicalCare() {
        
		mdl.saveOrupdate(md);
		formlistspon=false;
		formList=true;
		
		return "/pages/AfficherDispo?faces-redirect=true";

	}
	
}
