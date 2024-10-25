package org.pgm.japdemo.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.pgm.japdemo.domain.Board;
import org.pgm.japdemo.domain.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

        booleanBuilder.or(board.title.contains("11")); // title like ...

        booleanBuilder.or(board.content.contains("11")); // content like ....

        query.where(booleanBuilder);
        query.where(board.bno.gt(0L));


        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();


        return null;
    }
    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);
        //BooleanBuilder booleanBuilder = new BooleanBuilder();
        if((types != null && types.length > 0) && (keyword != null)) {
            BooleanBuilder builder = new BooleanBuilder();
            for(String type : types) {
                switch(type) {
                    case "t":
                        builder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        builder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        builder.or(board.writer.contains(keyword));
                        break;
                }
            }
            query.where(builder);
        }
        query.where(board.bno.gt(0l));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Board> list = query.fetch();
        long total = query.fetchCount();
        return new PageImpl<>(list, pageable,total);
    }
}
