package com.example.project.quote.controller;

import com.example.project.quote.model.dto.QuoteDto;
import com.example.project.quote.model.scheduler.QuoteScheduler;
import com.example.project.quote.model.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class QuoteController {
    private final QuoteService quoteService;
    private final QuoteScheduler quoteScheduler = new QuoteScheduler();
    private long quoteId = quoteScheduler.selectQuoteId();

    //글귀 1개 가져오기
    @GetMapping("/quote")
    public ResponseEntity<QuoteDto> selectOneQuote(){

//        long quoteId = (long)(Math.random()*35);
//        long quoteId = 21; //db에 넣고 결정할 것

        QuoteDto quoteDto = quoteService.selectOneQuote(quoteId).orElseThrow();
        return new ResponseEntity<>(quoteDto, HttpStatus.OK);
    }

}