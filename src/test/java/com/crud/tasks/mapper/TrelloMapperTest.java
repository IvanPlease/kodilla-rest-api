package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {
    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard(
                "1",
                "trellBoard",
                new ArrayList<>());
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto(
                "1",
                "trellBoard",
                new ArrayList<>());
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(trelloBoard);
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(trelloBoardDto);
        when(trelloMapper.mapToBoards(boardDtos)).thenReturn(boardList);
        //When
        List<TrelloBoard> mappedBoardList = trelloMapper.mapToBoards(boardDtos);
        //Then
        assertEquals(mappedBoardList.size(), boardDtos.size());
        for (TrelloBoard board : mappedBoardList) {
            assertEquals(board.getName(), boardDtos.get(mappedBoardList.indexOf(board)).getName());
            assertEquals(board.getId(), boardDtos.get(mappedBoardList.indexOf(board)).getId());
            assertEquals(board.getLists().size(), boardDtos.get(mappedBoardList.indexOf(board)).getLists().size());
        }
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard(
                "1",
                "trellBoard",
                new ArrayList<>());
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto(
                "1",
                "trellBoard",
                new ArrayList<>());
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(trelloBoard);
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(trelloBoardDto);
        when(trelloMapper.mapToBoardsDto(boardList)).thenReturn(boardDtos);
        //When
        List<TrelloBoardDto> mappedBoardList = trelloMapper.mapToBoardsDto(boardList);
        //Then
        assertEquals(mappedBoardList.size(), boardDtos.size());
        for (TrelloBoardDto board : mappedBoardList) {
            assertEquals(board.getName(), boardDtos.get(mappedBoardList.indexOf(board)).getName());
            assertEquals(board.getId(), boardDtos.get(mappedBoardList.indexOf(board)).getId());
            assertEquals(board.getLists().size(), boardDtos.get(mappedBoardList.indexOf(board)).getLists().size());
        }
    }
}