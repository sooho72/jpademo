package org.pgm.japdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.modelmapper.ModelMapper;
import org.pgm.japdemo.domain.Board;
import org.pgm.japdemo.dto.BoardDTO;
import org.pgm.japdemo.dto.PageRequestDTO;
import org.pgm.japdemo.dto.PageResponseDTO;
import org.pgm.japdemo.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {
        log.info("getList");
        Pageable pageable=pageRequestDTO.getPageable("bno");
        Page<Board> result = boardRepository.findAll(pageable);

        List<BoardDTO> dtoList=result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toUnmodifiableList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public Board getBoard(Long bno) {
        log.info("getBoard"+bno);
        return boardRepository.findById(bno).get();
    }

    @Override
    public void saveBoard(Board board) {
        log.info("Saving board: " + board);
        boardRepository.save(board);
    }

    @Override
    public void updateBoard(Board board) {
        log.info("update board"+board);
        Board oldBoard =boardRepository.findById(board.getBno()).get();
        oldBoard.setTitle(board.getTitle());
        oldBoard.setContent(board.getContent());
        oldBoard.setWriter(board.getWriter());
        boardRepository.save(oldBoard);
    }

    @Override
    public void deleteBoard(Long bno) {
        log.info("Delete Board"+bno);
        boardRepository.deleteById(bno);
    }
}
