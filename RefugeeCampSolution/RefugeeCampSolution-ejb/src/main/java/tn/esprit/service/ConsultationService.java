package tn.esprit.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.persistence.Consultation;
import tn.esprit.persistence.MedicalCare;
import tn.esprit.persistence.Refug;


@Stateless
public class ConsultationService implements ConsultationLocal {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	private void ConsultationService() {
		// TODO Auto-generated method stub

	}
	@Override
	public void saveOrupdate(Consultation consultation) {
		entityManager.merge(consultation);		
	}

	@Override
	public void deleteConsultation(Consultation consultation) {
		entityManager.remove(entityManager.merge(consultation));		
	}

	@Override
	public Consultation findConsultationById(Integer idConsultation) {
		// TODO Auto-generated method stub
		return entityManager.find(Consultation.class, idConsultation);	}

	@Override
	public List<Consultation> GetAllConsultation() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select c from Consultation c");
		List<Consultation> Consultations = query.getResultList();

		return Consultations;
	}
	
	@Override
	public List<Refug> GetAllRefugee() {
		Query query = entityManager.createQuery("select S  from Refug S ");
		List<Refug> refugees = query.getResultList();
       
		return refugees;
	}
	@Override
	public List<Integer> GetAllRefugeeID() {
		Query query = entityManager.createQuery("select S.refugID  from Refug S ");
		List<Integer> refugees = query.getResultList();
       
		return refugees;
	}
	@Override
	public List<Integer> GetAllMedecinId()
	{
		Query query = entityManager.createQuery("select S.medecinID  from MedicalCare S ");
		List<Integer> refugees = query.getResultList();
       
		return refugees;
	}
	@Override
	public List<MedicalCare> getByDate(Date d) {
		TypedQuery<MedicalCare> query = entityManager.createNamedQuery("SELECT  m.medecinID FROM MedicalCare m"
				+ " inner join MedecinDisponibilite d on m.medecinID =1"
				+ " where :var BETWEEN d.dateDebut AND d.dateFin  ", MedicalCare.class);
		query.setParameter("var", d);
		
		
		return  query.getResultList();
	}
	
}
