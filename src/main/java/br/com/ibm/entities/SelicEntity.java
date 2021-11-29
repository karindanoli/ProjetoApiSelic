package br.com.ibm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "selic_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SelicEntity implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    @JsonProperty("codigo_Serie")
    private Long codigo_Serie;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Double valor;



}
