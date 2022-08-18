package com.its.board.repository;

import com.its.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    //native sql: update board_table set board_hits=board_hits+1 where board_id=?

    //jpql(java persistence query language)
   @Modifying
   @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id = :id")
    void boardHits(@Param("id") Long id); //id => :id 와 매칭!
}
