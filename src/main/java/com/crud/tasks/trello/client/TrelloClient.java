package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

import static java.util.Optional.ofNullable;

@Component
@Slf4j
public class TrelloClient {

    private final TrelloConfig trelloConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public TrelloClient(TrelloConfig trelloConfig, RestTemplate restTemplate) {
        this.trelloConfig = trelloConfig;
        this.restTemplate = restTemplate;
    }

    private URI createUri(Map<String, String> uriParams,String access){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + access);
        uriComponentsBuilder.queryParam("key", trelloConfig.getTrelloAppKey());
        uriComponentsBuilder.queryParam("token", trelloConfig.getTrelloToken());
        for(Map.Entry<String, String> param:uriParams.entrySet()){
            uriComponentsBuilder.queryParam(param.getKey(), param.getValue());
        }
        return uriComponentsBuilder.build().encode().toUri();
    }

    public List<TrelloBoardDto> getTrelloBoards() {
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("fields", "name,id");
        uriParams.put("lists", "all");
        try{
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(createUri(uriParams, "/members/" + trelloConfig.getTrelloAppUsername() + "/boards"), TrelloBoardDto[].class);
            return Arrays.asList(ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
        }catch (RestClientException e){
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto){
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("name", trelloCardDto.getName());
        uriParams.put("desc", trelloCardDto.getDescription());
        uriParams.put("pos", trelloCardDto.getPos());
        uriParams.put("idList", trelloCardDto.getListId());
        return restTemplate.postForObject(createUri(uriParams, "/cards"), null, CreatedTrelloCardDto.class);
    }
}
