package org.pgm.japdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.japdemo.domain.Board;
import org.pgm.japdemo.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public List<Board> getList() {
        log.info("getList");
        List<Board> boardList = boardRepository.findAll();
        log.info("getList");
        return boardList;
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
