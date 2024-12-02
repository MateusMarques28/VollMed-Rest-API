package med.voll.api.domain.patient;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.adress.Adress;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
@Entity(name = "Patient")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Adress adress;

    private Boolean ativo;

    public Patient(DataRegisterPatient data) {
        this.ativo = true;
        this.nome = data.nome();
        this.email = data.email();
        this.cpf = data.cpf();
        this.telefone = data.telefone();
        this.adress = new Adress(data.adress());
    }

    public void updateInfos(@Valid DataUpdatePatient data) {
        if (data.nome() != null) {
            this.nome = data.nome();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
        if (data.adress() != null) {
            adress.updateInfos(data.adress());
        }
    }

    public void inactivePatient() {
        this.ativo = false;
    }
}
