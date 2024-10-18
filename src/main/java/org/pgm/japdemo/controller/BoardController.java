package org.pgm.japdemo.controller;

import lombok.extern.log4j.Log4j2;
import org.pgm.japdemo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public void list(Model model) {
        log.info("controller list");
        model.addAttribute("boardList", boardService.getList());
    }
}
