package med.voll.api.doctor;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.voll.api.adress.Adress;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Adress adress;

    private Boolean ativo;

    public Doctor(DataRegisterDoctor data) {
        this.ativo = true;
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.crm = data.crm();
        this.especialidade = data.especialidade();
        this.adress = new Adress(data.adress());
    }

    public void updateInfos(@Valid DataUpdateDoctor data) {
        if (data.nome() != null) {
            this.nome = data.nome();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
        if (data.telefone() != null) {
            this.telefone = data.telefone();
        }
        if (data.adress() != null) {
            this.adress.updateInfos(data.adress());
        }
    }

    public void inactiveDoctor() {
        this.ativo = false;
    }
}
