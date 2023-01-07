package parsingweb.dev_package.parsingweb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parsingweb.dev_package.parsingweb.service.parser.ParserChitayGorod;

@RestController
@RequestMapping(value = "/parser")
public class ActivateParserController {

    @Autowired
    ParserChitayGorod parserChitayGorod;

    @GetMapping(value = "/start")
    public void startParsing(){
        parserChitayGorod.parsingSite();
    }
}
