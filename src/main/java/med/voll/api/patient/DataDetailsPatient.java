package med.voll.api.patient;

import med.voll.api.adress.Adress;

public record DataDetailsPatient(Long id, String nome, String email, String cpf, String telefone, Adress adress) {

    public DataDetailsPatient (Patient patient) {
        this(
                patient.getId(),
                patient.getNome(),
                patient.getEmail(),
                patient.getCpf(),
                patient.getTelefone(),
                patient.getAdress()
        );
    }
}
