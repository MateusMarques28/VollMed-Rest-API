package med.voll.api.adress;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adress {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Adress(DataAdress adress) {
        this.logradouro = adress.logradouro();
        this.bairro = adress.bairro();
        this.cep = adress.cep();
        this.numero = adress.numero();
        this.complemento = adress.complemento();
        this.cidade = adress.cidade();
        this.uf = adress.uf();
    }

    public void updateInfos(DataAdress data) {
        if (data.logradouro() != null) {
            this.logradouro = data.logradouro();
        }
        if (data.bairro() != null) {
            this.bairro = data.bairro();
        }
        if (data.cep() != null) {
            this.cep = data.cep();
        }
        if (data.numero() != null) {
            this.numero = data.numero();
        }
        if (data.complemento() != null) {
            this.complemento = data.complemento();
        }
        if (data.cidade() != null) {
            this.cidade = data.cidade();
        }
        if (data.uf() != null) {
            this.uf = data.uf();
        }
    }
}
