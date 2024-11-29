package med.voll.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.adress.DataAdress;

public record DataUpdateDoctor(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DataAdress adress) {
}
