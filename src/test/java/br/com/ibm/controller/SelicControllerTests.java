package br.com.ibm.controller;

import br.com.ibm.dto.SelicRequest;
import br.com.ibm.entities.SelicEntity;
import br.com.ibm.service.SelicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class SelicControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private SelicService selicService;


        @Test
        public void salvarComSucesso() throws Exception {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            String data = "01/06/1986";
            Double valor = Double.parseDouble("1.26");

            SelicEntity dadoSelicEntity = new SelicEntity();
            dadoSelicEntity.setValor(valor);
            dadoSelicEntity.setData(LocalDate.parse(data, formatter));

            List<SelicRequest> selicRequest = new ArrayList<>();

            when(selicService.saveSelic())
                    .thenReturn((selicRequest));

            MvcResult resposta = mockMvc.perform(MockMvcRequestBuilders.post("/v1/selic/save")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(selicRequest)))
                    .andExpect(MockMvcResultMatchers.status().is(201)).andReturn();

            Assert.assertNotNull(resposta);
        }

        @Test
        public void updateComSucesso() throws Exception {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            int mes = 06;
            int ano = 1986;
            Double valor = 1.25;
            SelicEntity dadosSelicEntity = new SelicEntity();

            dadosSelicEntity.setValor(valor);
            dadosSelicEntity.setData(LocalDate.parse("01/06/1986",formatter));

            when(selicService.updateSelic(anyInt(),anyInt(),anyDouble())).thenReturn((dadosSelicEntity));


            MvcResult respostaTeste = mockMvc.perform(
                            MockMvcRequestBuilders.patch
                                            ("/v1/selic/atualizarselic")
                                    .param("mes","10")
                                    .param("ano","1997")
                                    .param("valor","1.25")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is(SC_OK)).andReturn();

            Assert.assertNotNull(respostaTeste.getResponse());

        }
        @Test
        public void buscarTudo() throws Exception {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            Double valor = 1.25;
            List<SelicEntity> allSelic = new ArrayList<>();
            SelicEntity dadosSelicEntity = new SelicEntity();

            dadosSelicEntity.setValor(valor);
            dadosSelicEntity.setData(LocalDate.parse("01/06/1986",formatter));
            allSelic.add(dadosSelicEntity);

              when(selicService.getAllSelic())
                    .thenReturn((allSelic));

            MvcResult resposta = mockMvc.perform(MockMvcRequestBuilders.get("/v1/selic/selic")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().is(SC_OK)).andReturn();

            Assert.assertNotNull(resposta);
        }
    }

