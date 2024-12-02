package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerPatients(@RequestBody @Valid DataRegisterPatient data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        patientRepository.save(patient);

        var uri = uriBuilder.path("patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsPatient(patient));
    }

    @GetMapping
    public ResponseEntity<Page<DataListPatient>> listPatients(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        var page = patientRepository.findAllByAtivoTrue(pagination).map(DataListPatient::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePatients(@RequestBody @Valid DataUpdatePatient data) {
        var patient = patientRepository.getReferenceById(data.id());
        patient.updateInfos(data);

        return ResponseEntity.ok(new DataDetailsPatient(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.inactivePatient();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detailPatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);

        return ResponseEntity.ok(new DataDetailsPatient(patient));
    }

}
