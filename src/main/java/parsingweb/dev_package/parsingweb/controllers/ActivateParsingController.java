package parsingweb.dev_package.parsingweb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parsingweb.dev_package.parsingweb.elements_for_parsing.ParsingChitayGorod;

@RestController
@RequestMapping(value = "/parsing")
public class ActivateParsingController {

    @Autowired
    ParsingChitayGorod parsingChitayGorod;

    @GetMapping(value = "/start")
    public void startParsing(){
        parsingChitayGorod.parsingSite();
    }
}
