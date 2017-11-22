package tn.esprit.service;

import java.util.Date;
import java.util.List;


import tn.esprit.persistence.Consultation;
import tn.esprit.persistence.MedicalCare;
import tn.esprit.persistence.Refug;

public interface ConsultationLocal {

	public void saveOrupdate(Consultation consultation);

	public void deleteConsultation(Consultation consultation);

	public Consultation findConsultationById(Integer idconsultation);

	public List<Consultation> GetAllConsultation();
	public List<Refug> GetAllRefugee();
	public List<Integer> GetAllRefugeeID();
	public List<Integer> GetAllMedecinId();
	public List<MedicalCare> getByDate(Date d);
	
}
