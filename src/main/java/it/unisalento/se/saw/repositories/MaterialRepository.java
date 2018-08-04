package it.unisalento.se.saw.repositories;


import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.domain.MaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, MaterialId> {

    public Material findMaterialById_IdMaterial(int id);
}
