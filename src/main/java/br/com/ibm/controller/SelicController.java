package br.com.ibm.controller;


import br.com.ibm.dto.SelicRequest;
import br.com.ibm.entities.SelicEntity;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.ibm.service.SelicService;

import java.io.IOException;

import java.util.DoubleSummaryStatistics;
import java.util.List;


@RestController
@RequestMapping("/v1/selic")
public class SelicController {


   @Autowired
    private SelicService selicService;

    @ApiOperation(value = "Este request lê os dados de datas e valores das taxas Selic. Também gera um banco de dados no repositório H2.")
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public List<SelicRequest> salvarSelic() {

        return selicService.saveSelic();
    }

    @ApiOperation(value = "Este request retorna os valores da taxa Selic por ano.")
    @GetMapping("/{ano}")

    public Double getSelicByYear(@PathVariable("ano") int ano) {
        return selicService.getSelicByYear(ano);
    }
    @ApiOperation(value = "Este request retorna todos os dados contidos na API(mês, ano e valor)")
    @GetMapping("/selic")
    public ResponseEntity<List<SelicEntity>> buscaSelic() throws NotFoundException {
        List<SelicEntity> list = selicService.getAllSelic();
        return new ResponseEntity<List<SelicEntity>>(list , HttpStatus.OK);
    }
    @ApiOperation(value = "Este request atualiza os dados de mês, ano e valor")
    @PatchMapping("/atualizarselic")
    public SelicEntity atualizar(@RequestParam("mes")int mes,
                                 @RequestParam("ano")int ano,
                                 @RequestParam("valor") Double valor
    ) throws IOException {
        return selicService.updateSelic(mes,ano,valor);
    }

    @ApiOperation(value = "Este request deleta os valores mês, ano e valor daquele código serie específico.")
    @DeleteMapping("/deletar")
    public String deleteSelic(@RequestParam("codigo_Serie") int codigo_Serie, final RedirectAttributes redirectAttributes)
    {
        try
        {
            selicService.deleteSelic(codigo_Serie);
        }
        catch(Exception ex)
        {
            throw ex;
        }
        //redirectAttributes.addFlashAttribute("mensagemSucesso", "Valor do mês selecionado removido com sucesso");
        return ("Valores do código série " + codigo_Serie + " removido com sucesso");
    }

    @ApiOperation(value = "Este request retorna o maior valor da taxa Selic do ano em questão.")
    @GetMapping("/max/{ano}")

    public Double getSelicByYearMax(@PathVariable("ano") int ano) {
        return selicService.getSelicByYearMax(ano);
    }

    @ApiOperation(value = "Este request retorna soma,media,maximo e minimo da taxa Selic por ano.")
    @GetMapping("/summary/{ano}")
    public DoubleSummaryStatistics getSelicByYearStatistics(@PathVariable("ano") int ano) {
        return selicService.getSelicByYearStatistics(ano);
    }

    @ApiOperation(value = "Este request retorna o valor médio da taxa Selic do ano em questão.")
    @GetMapping("/media/{ano}")

    public Double getSelicByYearMedia(@PathVariable("ano") int ano) {
        return selicService.getSelicByYearMedia(ano);
    }


}
