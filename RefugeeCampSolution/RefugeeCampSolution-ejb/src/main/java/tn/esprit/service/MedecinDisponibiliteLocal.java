package tn.esprit.service;

import java.util.List;

import tn.esprit.persistence.MedecinDisponibilite;

public interface MedecinDisponibiliteLocal {
	
	public void saveOrupdate(MedecinDisponibilite medecinDisponibilite);

	public void deletedispo(MedecinDisponibilite medecinDisponibilite);

	public MedecinDisponibilite findDispoById(Integer idmedecinDisponibilite);

	public List<MedecinDisponibilite> GetAllDispo();
}
