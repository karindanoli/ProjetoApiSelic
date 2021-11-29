package br.com.ibm.service;

import br.com.ibm.dto.SelicRequest;
import br.com.ibm.entities.SelicEntity;
import javassist.NotFoundException;

import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public interface SelicService {

    // tem os metodos que serão implementados CRUD
    public List<SelicRequest> saveSelic();//criar

    public Double getSelicByYear(int ano);//trabalhar os dados de ano

    List<SelicEntity> getAllSelic() throws NotFoundException;//ler tudo

    public SelicEntity updateSelic(int mes, int ano , Double valor) throws IOException;//atualizar

    public void deleteSelic (int codigo_Serie);//delete

    Double getSelicByYearMax(int ano);


    DoubleSummaryStatistics getSelicByYearStatistics(int ano);

    public Double getSelicByYearMedia(int ano);



}
