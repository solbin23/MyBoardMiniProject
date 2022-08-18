package com.its.board.controller;


import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save-form")
    public String saveForm(){
        return"boardPages/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){
        Long id = boardService.save(boardDTO);
        return "redirect:/board/" + id;
    }


    @GetMapping("/")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList); // boardDTOList를 boardList 에 담아서
        return "boardPages/findAll"; //boardPages에 findAll 로 갈거야!
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){ //경로상의 id 값을 가져오기 때문에 pathvariable 사용
        BoardDTO boardDTO = boardService.findById(id); //findbyid 를 통해 dto 객체를 가져온다
        model.addAttribute("board", boardDTO); //dto 객체를 가지고
        return "boardPages/detail"; //detail 로 간다!
    }
}
