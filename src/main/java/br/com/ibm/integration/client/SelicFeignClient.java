package br.com.ibm.integration.client;

import br.com.ibm.dto.SelicRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "Selic", url = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.4390/dados?formato=json")
public interface SelicFeignClient {
    // link com serviços externos

    @GetMapping("")
    List<SelicRequest> onbordingDadosSelic();
}
