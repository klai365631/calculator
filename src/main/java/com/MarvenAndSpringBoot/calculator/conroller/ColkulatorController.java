package com.MarvenAndSpringBoot.calculator.conroller;

import com.MarvenAndSpringBoot.calculator.service.ColkulateoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class ColkulatorController {
    private final ColkulateoService colkulateoService;

    public ColkulatorController(ColkulateoService colkulateoService) {
        this.colkulateoService = colkulateoService;
    }

    @GetMapping
    public String welcome() {
        return "<h>Добро пожаловать в калькулятор</h>";
    }

    @GetMapping("/plus")
    public String plus(@RequestParam("num1") int a,
                        @RequestParam("num2") int b){
        return returnLines(a,b,colkulateoService.sum(a,b),"+");
    }
    @GetMapping("/minus")
    public String minus(@RequestParam("num1") int a,
                        @RequestParam("num2") int b){
        return returnLines(a,b,colkulateoService.minus(a,b),"-");
    }
    @GetMapping("/multiply")
    public String multiply(@RequestParam("num1") int a,
                        @RequestParam("num2") int b){
        return returnLines(a,b,colkulateoService.multiply(a,b),"*");
    }
    @GetMapping("/divide")
    public String divide(@RequestParam("num1") int a,
                        @RequestParam("num2") int b){
        if (b==0){
            return "На ноль делить нельзя";
        }
        return returnLines(a,b,colkulateoService.divide(a,b),"/");
    }

    public String returnLines(int a, int b, Number result, String operation) {
        return String.format("%d %s %d=%s", a, operation, b, result.toString());
    }

}
