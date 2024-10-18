package org.pgm.japdemo.controller;

import lombok.extern.log4j.Log4j2;
import org.pgm.japdemo.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
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
        Map<String, Integer> maps=new HashMap<>();
        maps.put("홍길동",80);
        maps.put("박경미",75);
        maps.put("윤요섭",85);
        model.addAttribute("maps",maps);

        SampleDTO sampleDTO=new SampleDTO();
        sampleDTO.setName("hong");
        sampleDTO.setAge(20);
        sampleDTO.setGender("남자");

        model.addAttribute("sampleDTO",sampleDTO);
        model.addAttribute("strList", strList);
        model.addAttribute("strList2", strList2);
    }
    @GetMapping("ex/ex3")
    public void ex3(){
        log.info("ex3");
    }
}
