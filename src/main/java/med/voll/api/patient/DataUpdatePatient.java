package med.voll.api.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.adress.DataAdress;

public record DataUpdatePatient(

        @NotNull
        Long id,
        String nome,
        String email,
        @Valid
        DataAdress adress) {
}
