package br.com.ibm.service;

import br.com.ibm.dto.SelicRequest;
import br.com.ibm.entities.SelicEntity;
import br.com.ibm.integration.client.SelicFeignClient;
import br.com.ibm.repositories.SelicRepository;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// este pega do api e guarda no repository


//@Override
//getTaxabyYear
//colocar o metodo pro ano

@Service
public class SelicServiceImpl implements SelicService{

    @Autowired
    private SelicRepository repository;

    @Autowired
    private SelicFeignClient selicFeignClient;


    @Override
    public List<SelicRequest> saveSelic() {//consome os dados que foram enviados pela api
        //pega os dados do client pra salvar no repository via jpa

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<SelicRequest> selicRequest = selicFeignClient.onbordingDadosSelic();
        selicRequest.forEach(taxaSelicRequest1 -> {LocalDate transformacaoDeData = LocalDate.parse(String.valueOf(taxaSelicRequest1.getData()), dtf);
            Double transformacaoDeValor = Double.parseDouble(String.valueOf(taxaSelicRequest1.getValor()));

            SelicEntity dadosSelicEntity = new SelicEntity();
            dadosSelicEntity.setData(transformacaoDeData);
            dadosSelicEntity.setValor(transformacaoDeValor);
            repository.save(dadosSelicEntity);

        });
        return selicRequest;
    }

    @Override
    public Double getSelicByYear(int ano) {

        List<SelicEntity> yearEntities = repository.findAll();

        Double resposta =
                yearEntities.stream().filter(taxaSelic -> taxaSelic.getData().getYear() == ano)
                        .map(taxaSelic -> taxaSelic.getValor())
                        .reduce(0.0, Double::sum);
        return resposta;
    }
    @Override
    public List<SelicEntity> getAllSelic() throws NotFoundException {

        List<SelicEntity> allSelic = repository.findAll();

        if (allSelic.isEmpty()) {
            throw new NotFoundException("Não há registros desta data.");
        }
        return allSelic;
    }

    @Override
    public SelicEntity updateSelic (int mes, int ano, Double valor) throws IOException{
        List<SelicEntity> yearEntities = repository.findAll();
        Optional<SelicEntity> respostaOptional = yearEntities.stream()
                .filter(selicEntity -> selicEntity.getData().getYear() == ano)
                .filter(selicEntity -> selicEntity.getData().getMonth() == Month.of(mes))
                .findFirst();
        if(!respostaOptional.isPresent()){throw new IOException("NÃO ENCONTRADO");
        }
        SelicEntity resposta = respostaOptional.get();

        resposta.setValor(valor);
        //para validar se existe ou nao existe usamos o respostaoptional

        return repository.save(resposta);
    }

    @Override
    public void deleteSelic (int codigo_Serie){
        List<SelicEntity> yearEntities = repository.findAll();

        List<SelicEntity> resposta = yearEntities.stream()
                .filter(selicEntity -> selicEntity.getCodigo_Serie() == codigo_Serie)
                .collect(Collectors.toList());

        resposta.forEach(selicEntity ->
                repository.deleteById(selicEntity.getCodigo_Serie()));

        if (resposta.isEmpty()) {
            throw new IndexOutOfBoundsException("Código série não encontrado");
        }
    }

    @Override
    public Double getSelicByYearMax(int ano) {

        List<SelicEntity> yearEntities = repository.findAll();

        Double resposta =
                yearEntities.stream().filter(taxaSelic -> taxaSelic.getData().getYear() == ano)
                        .map(taxaSelic -> taxaSelic.getValor())
                        .reduce(0.0, Double::max);

        return resposta;
    }
    @Override
    public DoubleSummaryStatistics getSelicByYearStatistics(int ano) {

        List<SelicEntity> yearEntities = repository.findAll();

        return yearEntities.stream().filter(taxaSelic -> taxaSelic.getData().getYear() == ano)
                .mapToDouble(taxaSelic -> taxaSelic.getValor()).summaryStatistics();
    }

    @Override
    public Double getSelicByYearMedia(int ano) {

        List<SelicEntity> yearEntities = repository.findAll();

        Double resposta =
                yearEntities.stream().filter(taxaSelic -> taxaSelic.getData().getYear() == ano)
                        .mapToDouble(taxaSelic -> taxaSelic.getValor()).average().getAsDouble();


        return resposta;
    }




}