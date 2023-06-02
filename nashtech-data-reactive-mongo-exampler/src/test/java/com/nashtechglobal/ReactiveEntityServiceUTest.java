package com.nashtechglobal;


import com.nashtechglobal.entity.ReactiveEntity;
import com.nashtechglobal.model.ReactiveEntityRequest;
import com.nashtechglobal.model.ReactiveEntityResponse;
import com.nashtechglobal.repository.ReactiveEntityRepository;
import com.nashtechglobal.service.ReactiveEntityServiceImpl;
import java.util.Date;
import java.util.Objects;

import com.nashtechglobal.service.impl.CoreLoggerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ReactiveEntityServiceImpl.class, CoreLoggerImpl.class})
@ExtendWith(SpringExtension.class)
class ReactiveEntityServiceUTest {

    @Autowired
    private ReactiveEntityServiceImpl reactiveEntityServiceImpl;

    @MockBean
    private ReactiveEntityRepository reactiveEntityRepository;

    @Test
    void getReactiveEntity_returnsValidReactiveEntity_whenCalled() {

        ReactiveEntity reactiveEntityMono =this.getReactiveEntity();

        when(reactiveEntityRepository.findById("1")).thenReturn(Mono.just(reactiveEntityMono));

        ReactiveEntityResponse reactiveEntityResponse = new ReactiveEntityResponse();

        reactiveEntityResponse.setTestingStringData("testing");
        reactiveEntityResponse.setTestingLongData(1234L);
        reactiveEntityResponse.setTestingDateData(new Date(1));

        assertEquals("testing", Objects.requireNonNull
            (reactiveEntityServiceImpl.getReactiveEntity("1").block()).getTestingStringData());

        verify(reactiveEntityRepository).findById(anyString());
    }

    @Test
    void getAllReactiveEntities_returnsAllValidReactiveEntities_whenCalled()
    {
        ReactiveEntity reactiveEntity = getReactiveEntity();

        ReactiveEntity reactiveEntity1 = new ReactiveEntity();

        reactiveEntity1.setId("2");
        reactiveEntity1.setTestingStringData("testing1");
        reactiveEntity1.setTestingLongData(123456L);
        reactiveEntity1.setTestingDateData(new Date(2));

        Flux<ReactiveEntity> fluxReactiveEntity = Flux.just(reactiveEntity, reactiveEntity1);

        when(reactiveEntityRepository.findAll()).thenReturn(fluxReactiveEntity);

        Flux<ReactiveEntityResponse> reactiveEntityResponseFlux = reactiveEntityServiceImpl.getAllReactiveEntities();

        assertEquals(2 , reactiveEntityResponseFlux.count().block());
        assertEquals(reactiveEntity.getTestingLongData(),
           Objects.requireNonNull(reactiveEntityResponseFlux.
               doOnNext(System.out::println).blockFirst()).getTestingLongData());

       verify(reactiveEntityRepository,times(1)).findAll();
    }

    @Test
    void saveReactiveEntity_saveEntityInDB_whenCalled ()
    {
        ReactiveEntity reactiveEntity = ReactiveEntity.builder()
            .id("1")
            .testingStringData("test4")
            .testingLongData(45677L)
            .testingDateData(new Date(1))
            .build();

        ReactiveEntityRequest reactiveEntityRequest = ReactiveEntityRequest.builder()
            .id("1")
            .testingStringData("test4")
            .testingLongData(45677L)
            .testingDateData(new Date(1))
            .build();

        ReactiveEntityResponse response = ReactiveEntityResponse.builder()
            .testingStringData(reactiveEntity.getTestingStringData())
            .testingLongData(reactiveEntity.getTestingLongData())
            .testingDateData(reactiveEntity.getTestingDateData())
            .build();

        when(reactiveEntityRepository.save(any(ReactiveEntity.class))).thenReturn(Mono.just(reactiveEntity));

        assertEquals(Objects.requireNonNull(Mono.just(response).block()).getTestingDateData(),
            Objects.requireNonNull(reactiveEntityServiceImpl
                .saveReactiveEntity(reactiveEntityRequest).block()).getTestingDateData());

        verify(reactiveEntityRepository, times(1)).save((ReactiveEntity) any());
    }

    @Test
    void updateReactiveEntity_updatesRecordInDB_whenCalled()
    {
        ReactiveEntity record = ReactiveEntity.builder()
            .id("1")
            .testingStringData("test1")
            .testingLongData(789L)
            .testingDateData(new Date(1))
            .build();

        ReactiveEntityRequest request = ReactiveEntityRequest.builder()
            .id("1")
            .testingStringData("test1")
            .testingLongData(789L)
            .testingDateData(new Date(1))
            .build();

        when(reactiveEntityRepository.save((ReactiveEntity) any())).thenReturn(Mono.just(record));

        Mono<ReactiveEntityResponse> response = reactiveEntityServiceImpl.saveReactiveEntity(request);
        verify(reactiveEntityRepository).save((ReactiveEntity) any());
        assertEquals("test1", Objects.requireNonNull(response.block()).getTestingStringData());
        assertEquals(789L, Objects.requireNonNull(response.block()).getTestingLongData());
    }

    @Test
    void deleteReactiveEntity_whenCalled() {
        ReactiveEntity reactiveEntity = getReactiveEntity();

        Mono<Void> voidReturn  = Mono.empty();

        when(reactiveEntityRepository.delete((ReactiveEntity) any())).thenReturn( Mono.empty());
        when(reactiveEntityRepository.findById(anyString())).thenReturn((Mono.just(reactiveEntity)));
        reactiveEntityServiceImpl.deleteReactiveEntity("1");
        Assert.isNull(reactiveEntityRepository.findAll(), "This is empty after delete");
    }

    ReactiveEntity getReactiveEntity()
    {
        ReactiveEntity reactiveEntityMono = new ReactiveEntity();

        reactiveEntityMono.setId("1");
        reactiveEntityMono.setTestingStringData("testing");
        reactiveEntityMono.setTestingLongData(1234L);
        reactiveEntityMono.setTestingDateData(new Date(1));

        return  reactiveEntityMono;
    }

}
