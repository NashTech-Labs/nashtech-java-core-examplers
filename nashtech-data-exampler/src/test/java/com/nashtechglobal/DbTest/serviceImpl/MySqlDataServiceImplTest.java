package com.nashtechglobal.DbTest.serviceImpl;

import com.nashtechglobal.exception.ResourceNotFoundException;
import com.nashtechglobal.model.EntityModel;
import com.nashtechglobal.repository.MySqlDBRepository;
import com.nashtechglobal.serviceimpl.MySqlDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {MySqlDataServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MySqlDataServiceImplTest {

    @Autowired
    private MySqlDataServiceImpl mySqlDataServiceImpl;

    @MockBean
    private MySqlDBRepository mySqlDBRepository;
    EntityModel RECORD_1 = new EntityModel(1L, "test1");
    EntityModel RECORD_2 = new EntityModel(2L, "test2");
    EntityModel RECORD_3 = new EntityModel(3L, "test3");
    @Test
    void getEntityByTestId_success() {
        when(mySqlDBRepository.findById(RECORD_1.getTestId())).thenReturn(Optional.ofNullable((RECORD_1)));
        assertSame(RECORD_1, mySqlDataServiceImpl.getEntityModelById(1L));
        verify(mySqlDBRepository).findById( any());
    }
    @Test
    void getEntityModelById_notFound() {
        Long testId = 99L;
        when(mySqlDBRepository.findById(testId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> mySqlDataServiceImpl.getEntityModelById(testId));
        verify(mySqlDBRepository).findById(testId);
    }

    @Test
    void getAllEntity_success() {
        List<EntityModel> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        when(mySqlDBRepository.findAll()).thenReturn(records);
        assertEquals(3, mySqlDataServiceImpl.getAllEntityModels().size());
        verify(mySqlDBRepository).findAll();
    }


    @Test
    void createEntity_success() {
        EntityModel record = EntityModel.builder()
                .testId(4L)
                .testName("test11")
                .build();

        when(mySqlDBRepository.save((EntityModel) any())).thenReturn(record);
        assertSame(record, mySqlDataServiceImpl.saveEntityModel(record));
        verify(mySqlDBRepository).save((EntityModel) any());
    }
    @Test
    void updateEntityModel_success() {
        EntityModel existingRecord = EntityModel.builder()
                .testId(1L)
                .testName("Existing Test")
                .build();
        when(mySqlDBRepository.findById(1L)).thenReturn(Optional.of(existingRecord));

        EntityModel newRecord = EntityModel.builder()
                .testId(1L)
                .testName("Updated Test")
                .build();
        when(mySqlDBRepository.save(existingRecord)).thenReturn(newRecord);

        EntityModel updatedRecord = mySqlDataServiceImpl.updateEntityModel(newRecord);
        verify(mySqlDBRepository).findById(1L);
        verify(mySqlDBRepository).save(existingRecord);
        assertSame(updatedRecord, newRecord);
    }

    @Test
    void updateEntity_success() {
        EntityModel record = EntityModel.builder()
                .testId(4L)
                .testName("test12")
                .build();

        when(mySqlDBRepository.save((EntityModel) any())).thenReturn(record);
        mySqlDataServiceImpl.saveEntityModel(record);
        verify(mySqlDBRepository).save((EntityModel) any());
        assertEquals(4, record.getTestId());
        assertEquals("test12", record.getTestName());
        assertTrue(mySqlDataServiceImpl.getAllEntityModels().isEmpty());
    }

    @Test
    void deleteEntityByTestId_success() {
        EntityModel record = EntityModel.builder()
                .testId(4L)
                .testName("test12")
                .build();
        Optional<EntityModel> ofResult = Optional.of(record);
        doNothing().when(mySqlDBRepository).delete((EntityModel) any());
        when(mySqlDBRepository.findById( any())).thenReturn(ofResult);
        mySqlDataServiceImpl.deleteEntityModel(4L);
        verify(mySqlDBRepository).findById( any());
        verify(mySqlDBRepository).delete((EntityModel) any());
        assertTrue(mySqlDataServiceImpl.getAllEntityModels().isEmpty());
    }
    @Test
    void deleteEntityModel_notFound() {
        Long testId = 99L;
        when(mySqlDBRepository.findById(testId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> mySqlDataServiceImpl.deleteEntityModel(testId));
        verify(mySqlDBRepository).findById(testId);
        verify(mySqlDBRepository, never()).delete(any(EntityModel.class));
    }

}
