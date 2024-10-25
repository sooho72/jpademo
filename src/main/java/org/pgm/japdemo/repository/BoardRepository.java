    package org.pgm.japdemo.repository;

    import org.pgm.japdemo.domain.Board;
    import org.pgm.japdemo.repository.search.BoardSearch;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.EntityGraph;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.Optional;

    public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
        @EntityGraph(attributePaths = {"imageSet"})
        @Query("select b from Board b where b.bno=:bno")
        Optional<Board> findByIdWithImages(Long bno);


       //@Query("select b from Board b where b.title like concat('%',:keyword,'%') order by b.bno desc")
       // Page<Board> searchAll(String keyword,Pageable pageable);
        //Page<Board> findByTitle(String title, Pageable pageable);
        //select * from board where title=?
    }
