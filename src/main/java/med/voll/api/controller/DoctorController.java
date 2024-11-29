package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerDoctors(@RequestBody @Valid DataRegisterDoctor data, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(data);
        doctorRepository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsDoctor(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DataListDoctor>> listDoctors(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        var page = doctorRepository.findAllByAtivoTrue(pagination).map(DataListDoctor::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DataUpdateDoctor data) {
        var doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateInfos(data);

        return ResponseEntity.ok(new DataDetailsDoctor(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.inactiveDoctor();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detailDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);

        return ResponseEntity.ok(new DataDetailsDoctor(doctor));
    }
}
