package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.ILessonServices;
import it.unisalento.se.saw.Iservices.IMaterialServices;
import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.domain.Material;
import it.unisalento.se.saw.domain.MaterialId;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.MaterialDTO;
import it.unisalento.se.saw.exceptions.MaterialNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaterialService implements IMaterialServices {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    ILessonServices lessonServices;

    @Transactional(readOnly = true)
    public List<Material> getAll() {
        return materialRepository.findAll();
    }
    @Transactional
    public Material getById(int id) throws MaterialNotFoundException {
        try {
            return materialRepository.findMaterialById_IdMaterial(id);
        } catch (Exception e) {
            throw new MaterialNotFoundException();
        }
    }

    @Transactional
    public Material getDomainById(int id) throws MaterialNotFoundException {
        try {
            return materialRepository.findMaterialById_IdMaterial(id);
        } catch (Exception e) {
            throw new MaterialNotFoundException();
        }
    }

    @Transactional
    public List<Material> getByIdLesson(int id){
        return materialRepository.findMaterialsById_LessonIdLesson(id);
    }

    @Transactional
    public List<Material> getByIdTeaching(int id) {
        return materialRepository.findMaterialsById_LessonTeachingIdTeaching(id);
    }

    @Transactional
    public Material save(MaterialDTO materialDTO) throws TeachingNotFoundException {
        try {
            Lesson lesson = lessonServices.getById(materialDTO.getIdLesson());

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
            return materialRepository.save(material);
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }
    }
    @Transactional
    public void remove(int id) throws MaterialNotFoundException {
        try {
            Material material = materialRepository.findMaterialById_IdMaterial(id);
            materialRepository.delete(material);
        } catch (Exception e) {
            throw new MaterialNotFoundException();
        }

    }
}
