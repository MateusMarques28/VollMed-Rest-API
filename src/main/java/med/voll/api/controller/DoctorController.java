package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void registerDoctors(@RequestBody @Valid DataRegisterDoctor data) {
        doctorRepository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DataListDoctor> listDoctors(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        return doctorRepository.findAllByAtivoTrue(pagination).map(DataListDoctor::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid DataUpdateDoctor data) {
        var doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateInfos(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.inactiveDoctor();
    }
}
