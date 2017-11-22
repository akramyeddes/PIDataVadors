package tn.esprit.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.persistence.MedicalCare;
import tn.esprit.persistence.statmedical;
@Stateless
public class MedicalCareService implements MedicalCareLocal {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public MedicalCareService() {
		// TODO Auto-generated method stub

	}
	@Override
	public void saveOrupdate(MedicalCare medicalCare) {
		entityManager.merge(medicalCare);		
	}

	@Override
	public void deleteMedicalCare(MedicalCare medicalCare) {
		entityManager.remove(entityManager.merge(medicalCare));		
	}

	@Override
	public MedicalCare findMedicalCareById(Integer idMedicalCare) {
		// TODO Auto-generated method stub
		return entityManager.find(MedicalCare.class, idMedicalCare);	}

	@Override
	public List<MedicalCare> GetAllMedicalCare() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select c from MedicalCare c");
		List<MedicalCare> MedicalCare = query.getResultList();

		return MedicalCare;
	}
	
	
	
	
	
	
	
	
	@Override
	public int nbspecialite(String s) {
		String jpql = "SELECT  count(c) FROM MedicalCare c where c.SPC=:x";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("x", s);
	    return (int)(long)query.getSingleResult();
	}


	@Override
	public List<statmedical> getlistmedical() {
List<statmedical> listst = new ArrayList<statmedical>();
		
		String jpql = "SELECT distinct l.SPC FROM MedicalCare l";
		Query query = entityManager.createQuery(jpql);
	    @SuppressWarnings("unchecked")
		List<String> ls= query.getResultList();
	    for (String s : ls) {
	    	statmedical st = new statmedical();
	    	st.setName(s);
	    	st.setValue(nbspecialite(s));
	    	listst.add(st);
		}
	    return listst;
	}



}
