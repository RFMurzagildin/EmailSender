package ru.webbyskysender.messagereceiverclient.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.webbyskysender.messagereceiverclient.dto.Message;
import ru.webbyskysender.messagereceiverclient.service.MessageService;

@Controller
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public String mainPage(){
        return "main-page";
    }

    @PostMapping("/receive")
    public String receive(@ModelAttribute Message message){
        log.info("New message: {}", message);
        messageService.saveMessage(message);
        return "redirect:/";
    }
}
