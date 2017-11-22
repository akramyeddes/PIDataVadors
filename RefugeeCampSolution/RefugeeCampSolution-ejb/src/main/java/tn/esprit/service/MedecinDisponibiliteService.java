package tn.esprit.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.persistence.MedecinDisponibilite;
import tn.esprit.persistence.MedicalCare;

@Stateless
public class MedecinDisponibiliteService implements MedecinDisponibiliteLocal {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	 public MedecinDisponibiliteService() {
		// TODO Auto-generated constructor stub
	}
	 @Override
		public void saveOrupdate(MedecinDisponibilite medecinDisponibilite) {
			entityManager.merge(medecinDisponibilite);		
		}

		@Override
		public void deletedispo(MedecinDisponibilite medicalCare) {
			entityManager.remove(entityManager.merge(medicalCare));		
		}

		@Override
		public MedecinDisponibilite findDispoById(Integer idMedicalCare) {
			// TODO Auto-generated method stub
			return entityManager.find(MedecinDisponibilite.class, idMedicalCare);	}

		@Override
		public List<MedecinDisponibilite> GetAllDispo() {
			// TODO Auto-generated method stub
			Query query = entityManager.createQuery("select c from MedecinDisponibilite c");
			List<MedecinDisponibilite> MedicalCare = query.getResultList();

			return MedicalCare;
		}


}
