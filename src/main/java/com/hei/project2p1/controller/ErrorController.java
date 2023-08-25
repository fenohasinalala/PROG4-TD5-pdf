package com.hei.project2p1.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.ServerException;

@Controller
@AllArgsConstructor
public class ErrorController {

    @ExceptionHandler(ServerException.class)
    public String handleBadRequestException(ServerException e, HttpServletResponse response, Model model) {
        int statusCode = response.getStatus();
        model.addAttribute("exception", e);
        model.addAttribute("status", statusCode);
        return "error";
    }

}
