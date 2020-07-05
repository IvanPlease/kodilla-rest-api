package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.username}")
    private String trelloAppUsername;

    private final RestTemplate restTemplate;

    @Autowired
    public TrelloClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private URI createUri(Map<String, String> uriParams,String access){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + access);
        uriComponentsBuilder.queryParam("key", trelloAppKey);
        uriComponentsBuilder.queryParam("token", trelloToken);
        for(Map.Entry<String, String> param:uriParams.entrySet()){
            uriComponentsBuilder.queryParam(param.getKey(), param.getValue());
        }
        return uriComponentsBuilder.build().encode().toUri();
    }

    public List<TrelloBoardDto> getTrelloBoards() {
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("fields", "name,id");
        uriParams.put("lists", "all");
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(createUri(uriParams, "/members/" + trelloAppUsername + "/boards"), TrelloBoardDto[].class);
        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("name", trelloCardDto.getName());
        uriParams.put("desc", trelloCardDto.getDescription());
        uriParams.put("pos", trelloCardDto.getPos());
        uriParams.put("idList", trelloCardDto.getListId());
        return restTemplate.postForObject(createUri(uriParams, "/cards"), null, CreatedTrelloCard.class);
    }
}
