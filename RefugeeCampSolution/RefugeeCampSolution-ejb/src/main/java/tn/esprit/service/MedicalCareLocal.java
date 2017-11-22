package tn.esprit.service;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.persistence.MedicalCare;
import tn.esprit.persistence.statmedical;
@Local
public interface MedicalCareLocal {

	public void saveOrupdate(MedicalCare medicalCare);

	public void deleteMedicalCare(MedicalCare medicalCare);

	public MedicalCare findMedicalCareById(Integer idMedicalCare);

	public List<MedicalCare> GetAllMedicalCare();
	public int nbspecialite(String s);
	public List<statmedical> getlistmedical() ;
}
