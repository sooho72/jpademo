package org.pgm.japdemo.service;

import org.pgm.japdemo.domain.Board;
import org.pgm.japdemo.dto.BoardDTO;
import org.pgm.japdemo.dto.PageRequestDTO;
import org.pgm.japdemo.dto.PageResponseDTO;

import java.util.List;

public interface BoardService {
    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);
    Board getBoard(Long bno);
    void saveBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(Long bno);
}
