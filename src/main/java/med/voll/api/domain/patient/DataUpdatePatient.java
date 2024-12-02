package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.adress.DataAdress;

public record DataUpdatePatient(

        @NotNull
        Long id,
        String nome,
        String email,
        @Valid
        DataAdress adress) {
}
