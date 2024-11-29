package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void registerPatients(@RequestBody @Valid DataRegisterPatient data) {
        patientRepository.save(new Patient(data));
    }

    @GetMapping
    public Page<DataListPatient> listPatients(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        return patientRepository.findAllByAtivoTrue(pagination).map(DataListPatient::new);
    }

    @PutMapping
    @Transactional
    public void updatePatients(@RequestBody @Valid DataUpdatePatient data) {
        var patient = patientRepository.getReferenceById(data.id());
        patient.updateInfos(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.inactivePatient();
    }

}
