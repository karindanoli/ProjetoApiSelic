package br.com.ibm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelicRequest {
    //request destes dados no json pra puxar pros métodos do CRUD Na interface SELICSERVICE

    @JsonProperty("data")
    private String data;

    @JsonProperty("valor")
    private Double valor;


}
