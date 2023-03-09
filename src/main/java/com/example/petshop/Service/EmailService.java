package com.example.petshop.Service;

import com.example.petshop.Exceptions.EmailErrorException;
import com.example.petshop.Models.AnimalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(AnimalModel animalModel) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("edu.floriani047@gmail.com");
            message.setTo(animalModel.getResponsibleEmail());
            message.setSubject("Serviço finalizado!");
            message.setText(String.format("Olá %s. Nós da petshop gostaríamos de informar que o serviço de %s está concluído! Seu %s está muito feliz!",
                    animalModel.getResponsibleName(),
                    animalModel.getTreatment(),
                    animalModel.getBreed()));
            emailSender.send(message);
        } catch (MailException e) {
            System.out.println("email error");
            throw new EmailErrorException("Erro ao enviar o e-mail");
        }
    }

}
