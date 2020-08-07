package com.crud.tasks.domain;

import com.crud.tasks.mapper.TaskMapper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(MockitoJUnitRunner.class)
public class DomainTestSuite {
    @Mock
    private TaskMapper taskMapper;
    @Test
    public void testTrelloAndTrelloCard(){
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "trelloList", false);
        TrelloList trelloList1 = new TrelloList("2", "trelloList", false);
        trelloLists.add(trelloList);
        trelloLists.add(trelloList1);
        TrelloBoard trelloBoard = new TrelloBoard("1", "trelloBoard", trelloLists);
        TrelloCard trelloCard = new TrelloCard("trelloCard", "trelloCard", "1", "500");
        Trello trello = new Trello(trelloBoard.getName(),trelloCard.getName());
        Trello trello1 = new Trello();
        TrelloCard trelloCard1 = new TrelloCard();
        //When & Then
        assertNull(trello1.getCard());
        assertNull(trello1.getBoard());
        assertNull(trelloCard1.getDescription());
        assertNull(trelloCard1.getName());
        assertNull(trelloCard1.getPos());
        assertNull(trelloCard1.getListId());
        assertEquals(trello.getBoard(), trelloBoard.getName());
        assertEquals(trello.getCard(), trelloCard.getName());
        assertEquals(trelloBoard.getLists(), trelloLists);
        assertEquals(trelloBoard.getId(), "1");
        assertEquals(trelloCard.getListId(), "500");
        assertEquals(trelloCard.getPos(), "1");
        assertEquals(trelloCard.getDescription(), "trelloCard");
    }
}
