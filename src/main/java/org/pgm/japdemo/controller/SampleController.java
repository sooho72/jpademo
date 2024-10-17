package org.pgm.japdemo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    public void hello(Model model) {
        log.info("hello");
        model.addAttribute("msg", "hello world");
    }
    @GetMapping("/hello1")
    public void hello1(@RequestParam("name") String name,
                       @RequestParam("age") int age,
                       Model model) {
        log.info("hello");
        log.info("name: " + name);
        log.info("age: " + age);
        model.addAttribute("name",name);
        model.addAttribute("msg", "hello world");
    }
    @GetMapping("/hello2")
    public void hello2(Model model) {
        log.info("hello");
        model.addAttribute("msg", "hello world");
    }
    @GetMapping("/hello3")
    public void hello3(Model model) {
        log.info("hello");
        model.addAttribute("msg", "hello world");
    }
    @GetMapping("/ex/ex1")
    public void ex1(Model model) {
        log.info("ex1");
        List<String> list= Arrays.asList("AAAA","BBBB","CCCC");
        model.addAttribute("list", list);
    }
    @GetMapping("/ex/ex2")
    public void ex2(Model model) {
        log.info("ex2");
        List<String> strList2=new ArrayList<>();
        List<String> strList= IntStream.range(1,10)
                .mapToObj(i->"Data"+i)
                .collect(Collectors.toList());

        for(int i=1; i<10; i++){
            strList2.add("Data"+i);
        }
        model.addAttribute("strList", strList);
        model.addAttribute("strList2", strList2);
    }
}
