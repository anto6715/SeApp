package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.ILessonServices;
import it.unisalento.se.saw.Iservices.IMaterialServices;
import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.domain.MaterialId;
import it.unisalento.se.saw.dto.MaterialDTO;
import it.unisalento.se.saw.exceptions.LessonNotFoundException;
import it.unisalento.se.saw.exceptions.MaterialNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class MaterialService implements IMaterialServices {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    ILessonServices lessonServices;

    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");

    @Transactional(readOnly = true)
    public List<MaterialDTO> getAll() {
        DTO<List<Material>, List<MaterialDTO>> dto = dtoFactory.getDTO("SETMATERIAL");
        return dto.create(materialRepository.findAll());
    }
    @Transactional
    public MaterialDTO getById(int id) throws MaterialNotFoundException {
        try {
            DTO<Material, MaterialDTO> dto = dtoFactory.getDTO("MATERIAL");
            return dto.create(materialRepository.findMaterialById_IdMaterial(id));
        } catch (Exception e) {
            throw new MaterialNotFoundException();
        }
    }

    @Transactional
    public Material getDomainById(int id) throws MaterialNotFoundException {
        return materialRepository.findMaterialById_IdMaterial(id);
    }

    @Transactional
    public List<MaterialDTO> getByIdLesson(int id){
        DTO<List<Material>, List<MaterialDTO>> dto = dtoFactory.getDTO("SETMATERIAL");
        return dto.create(materialRepository.findMaterialsById_LessonIdLesson(id));
    }

    @Transactional
    public List<MaterialDTO> getByIdTeaching(int id) {
        DTO<List<Material>, List<MaterialDTO>> dto = dtoFactory.getDTO("SETMATERIAL");
        return dto.create(materialRepository.findMaterialsById_LessonTeachingIdTeaching(id));
    }

    @Transactional
    public MaterialDTO save(MaterialDTO materialDTO) {
        DTO<Material, MaterialDTO> dto = dtoFactory.getDTO("MATERIAL");
        Lesson lesson = null;
        try {
            lesson = lessonServices.getDomainById(materialDTO.getIdLesson());
        } catch (LessonNotFoundException e) {
            e.printStackTrace();
        }

        MaterialId materialId = new MaterialId();
        materialId.setLessonIdLesson(lesson.getId().getIdLesson());
        materialId.setLessonRoomIdRoom(lesson.getId().getRoomIdRoom());
        materialId.setLessonTeachingCourseIdCourse(lesson.getId().getTeachingCourseIdCourse());
        materialId.setLessonTeachingIdTeaching(lesson.getId().getTeachingIdTeaching());
        materialId.setLessonTeachingProfessorIdProfessor(lesson.getId().getTeachingProfessorIdProfessor());
        materialId.setLessonTeachingProfessorUserIdUser(lesson.getId().getTeachingProfessorUserIdUser());

        Material material = new Material();

        material.setLink(materialDTO.getLink());
        material.setName(materialDTO.getName());
        material.setId(materialId);
        material.setLesson(lesson);
        return dto.create(materialRepository.save(material));

    }
}
