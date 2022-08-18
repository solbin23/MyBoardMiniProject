package com.its.board.service;

import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public Long save(BoardDTO boardDTO) {
        Long savedId = boardRepository.save(BoardEntity.toSaveEntity(boardDTO)).getId();

        return savedId;
    }

    public List<BoardDTO> findAll() {
       List<BoardEntity> boardEntityList =  boardRepository.findAll();
       List<BoardDTO> boardDTOList = new ArrayList<>(); //DTO로 옮겨 담기

        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity)); //각각의 엔티티를 dto객체로 반환을 하고, 반환된 것을 dtolist 로 추가!
        }
        return boardDTOList;
    }

    @Transactional
    public BoardDTO findById(Long id) {
        //조회수 처리
        //native sql : update board_table set boardHits = boardHits+1 where id=?
        boardRepository.boardHits(id);
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);//repository로 부터 findById를 가져와야함!
        if (optionalBoardEntity.isPresent()) {
                return BoardDTO.toBoardDTO(optionalBoardEntity.get());
        }else {
            return  null;
        }
    }
}
