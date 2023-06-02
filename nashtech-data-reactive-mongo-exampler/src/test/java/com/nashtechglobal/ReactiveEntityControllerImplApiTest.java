package com.nashtechglobal;

import com.nashtechglobal.controller.ReactiveEntityControllerImpl;
import com.nashtechglobal.entity.ReactiveEntity;
import com.nashtechglobal.model.ReactiveEntityRequest;
import com.nashtechglobal.model.ReactiveEntityResponse;
import com.nashtechglobal.repository.ReactiveEntityRepository;
import com.nashtechglobal.service.ReactiveEntityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@WebFluxTest(ReactiveEntityControllerImpl.class)
@ContextConfiguration(classes = ReactiveMongoApplication.class)
class ReactiveEntityControllerImplApiTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ReactiveEntityService reactiveEntityService;

    @MockBean
    ReactiveEntityRepository repository;

    @Test
    void findFluxReactiveEntities_success() throws Exception {

        Flux<ReactiveEntity> employeeFlux = Flux.just(getReactiveEntity(), getReactiveEntity());

        Mockito
            .when(repository.findAll())
            .thenReturn(employeeFlux);

        webTestClient.get()
            .uri("/reactive/entities")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(ReactiveEntityResponse.class);
    }

    @Test
    void getReactiveEntityById_Success() throws Exception {

        ReactiveEntityResponse reactiveEntityResponse = new ReactiveEntityResponse();

        reactiveEntityResponse.setTestingStringData("test");
        reactiveEntityResponse.setTestingLongData(123L);
        reactiveEntityResponse.setTestingDateData(new Date(1));

        Mockito
            .when(reactiveEntityService.getReactiveEntity("101"))
            .thenReturn(Mono.just(reactiveEntityResponse));

        webTestClient.get()
            .uri("/reactive/entities/101")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(ReactiveEntityResponse.class);
    }

    @Test
    void saveReactiveRecord_success() throws Exception {
        Mockito
            .when(reactiveEntityService.saveReactiveEntity(getSampleRequest()))
            .thenReturn(Mono.just(getSampleResponse()));

        webTestClient.post()
            .uri("/reactive/entities")
            .bodyValue(getSampleRequest())
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(ReactiveEntityResponse.class);
    }

    @Test
    void updateReactiveEntityRecord() throws Exception {
        Mockito
            .when(reactiveEntityService.updateReactiveEntity(getSampleRequest(), "101"))
            .thenReturn(Mono.just(getSampleResponse()));
        webTestClient.put()
            .uri("/reactive/entities/101")
            .bodyValue(getSampleRequest())
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(ReactiveEntityResponse.class);
    }

    @Test
    void deleteReactiveEntityById_success() throws Exception {

        Mockito.when(reactiveEntityService.getReactiveEntity("107"))
            .thenReturn(Mono.just(getSampleResponse()));

        webTestClient.delete()
            .uri("/reactive/entities/107")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(ReactiveEntityResponse.class);
    }

    private ReactiveEntity getReactiveEntity() {
        ReactiveEntity reactiveEntity = new ReactiveEntity();

        reactiveEntity.setId("101");
        reactiveEntity.setTestingStringData("test");
        reactiveEntity.setTestingLongData(123L);
        reactiveEntity.setTestingDateData(new Date(1));
        return reactiveEntity;
    }

    private ReactiveEntityResponse getSampleResponse() {
        ReactiveEntityResponse reactiveEntity =  new ReactiveEntityResponse();
        reactiveEntity.setTestingStringData("test");
        reactiveEntity.setTestingLongData(123L);
        reactiveEntity.setTestingDateData(new Date(1));
        return reactiveEntity;
    }

    private ReactiveEntityRequest getSampleRequest() {
        ReactiveEntityRequest reactiveEntity =  new ReactiveEntityRequest();
        reactiveEntity.setId("101");
        reactiveEntity.setTestingStringData("test");
        reactiveEntity.setTestingLongData(123L);
        reactiveEntity.setTestingDateData(new Date(1));
        return reactiveEntity;
    }
}
