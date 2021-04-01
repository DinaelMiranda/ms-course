package com.ditech.hrpayroll.services;

import com.ditech.hrpayroll.entities.Payment;
import com.ditech.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String WorkerHost;

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(long workerId, int days) {

        //Código de Requisição para o projeto hr-worker
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", ""+ workerId);// (""+workerId) é a conversão pra string do parâmentro workerId
        Worker worker = restTemplate.getForObject(WorkerHost + "/workers/{id}", Worker.class, uriVariables);

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }


}
