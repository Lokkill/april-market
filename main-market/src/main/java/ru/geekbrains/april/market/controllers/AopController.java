package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.april.market.aop.AppLogAspect;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/aop")
public class AopController {
    private final AppLogAspect appLogAspect;

    @GetMapping("/statistic")
    public String getDurationTime(){
        String text = "ProductService: " + appLogAspect.getTimeProductsService()
                + "\nOrderService: " + appLogAspect.getTimeOrderService()
                + "\nUserService: " + appLogAspect.getTimeUserService()
                + "\nRoleService: " + appLogAspect.getTimeRoleService();
        return text;
    }

}
