package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    @Autowired
    private SaleRepository repository;

    public void sendSms(Long saledId) {

        Sale sale = repository.findById(saledId).get();

        String date = sale.getDate().getMonth() + "/" + sale.getDate().getYear();

//        String msg = "O vendedor " + sale.getSellerName() + " foi destaque em " + date
//                + " com um total de R$ " + String.format("%.0f", sale.getAmount());

        String msg = "Lé Lu lu lé, esse é o Carlinhos FM, você está recebendo esse SMS porque é muito besta. Assinado Xirixe";
        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, msg).create();

        System.out.println(message.getSid());
    }
}
