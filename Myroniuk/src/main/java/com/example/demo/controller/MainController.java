package com.example.demo.controller;

import com.example.demo.model.Number;
import com.example.demo.model.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {
    private List<Result> list;

    public void setList(List<Result> list) {
        this.list = list;
    }

    @GetMapping("/")
    public String getList(Model model) {
        initialize();
        Number number = new Number();
        model.addAttribute("number", number);
        model.addAttribute("results", list);
        return "main";
    }

    @GetMapping("/main")
    public String getListWithoutInit(Model model) {
        Number number = new Number();
        model.addAttribute("number", number);
        model.addAttribute("results", list);
        return "main";
    }

    @GetMapping("/sort/asc")
    public String getListSortAsc(Model model) {
        this.list.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return (o1.getResult() < o2.getResult()) ? -1 : ((o1.getResult() == o2.getResult()) ? 0 : 1);
            }
        });
        model.addAttribute("results", list);
        return "main";
    }

    @GetMapping("/sort/desc")
    public String getListSortDesc(Model model) {
        this.list.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return (o2.getResult() < o1.getResult()) ? -1 : ((o1.getResult() == o2.getResult()) ? 0 : 1);
            }
        });
        model.addAttribute("results", list);
        return "main";
    }

    @GetMapping("/sortA/asc")
    public String getListSortAAsc(Model model) {
        this.list.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return (o1.getArgument() < o2.getArgument()) ? -1 : ((o1.getArgument() == o2.getArgument()) ? 0 : 1);
            }
        });
        model.addAttribute("results", list);
        return "main";
    }

    @GetMapping("/sortA/desc")
    public String getListSortADesc(Model model) {
        this.list.sort(new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return (o2.getArgument() < o1.getArgument()) ? -1 : ((o1.getArgument() == o2.getArgument()) ? 0 : 1);
            }
        });
        model.addAttribute("results", list);
        return "main";
    }

    @PostMapping("/process")
    public String saveProduct(@ModelAttribute("number") Number number) {
        setList(list.stream().filter(i -> Math.abs(number.getNum() - i.getResult()) < 0.1).collect(Collectors.toList()));
        return "redirect:/main";
    }

    public double function(float x) {
        return 5/(2*x-3);
    }
    public void initialize() {
        Random random = new Random();
        List<Result> list = new ArrayList<>();
        IntStream.range(0, 58).forEach(i -> {
            float v =  -100 + random.nextFloat()*200;
            Result result = new Result(v);
            result.setResult(function(v));
            list.add(result);
        });
        setList(list);
    }
}
