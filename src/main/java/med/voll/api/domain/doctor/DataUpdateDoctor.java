package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.adress.DataAdress;

public record DataUpdateDoctor(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DataAdress adress) {
}
