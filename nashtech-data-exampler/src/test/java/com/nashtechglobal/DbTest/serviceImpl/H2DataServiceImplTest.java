package com.nashtechglobal.DbTest.serviceImpl;



import com.nashtechglobal.exception.ResourceNotFoundException;
import com.nashtechglobal.model.EntityModel;
import com.nashtechglobal.repository.H2DBRepository;
import com.nashtechglobal.serviceimpl.H2DataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {H2DataServiceImpl.class})
@ExtendWith(SpringExtension.class)
class H2DataServiceImplTest {
    @MockBean
    private H2DBRepository h2DBRepository;

    @Autowired
    private H2DataServiceImpl h2DataServiceImpl;

    /**
     * Method under test: {@link H2DataServiceImpl#getEntityByTestId(long)} (long)}
     */
    @Test
    void testGetEntityByTestId() {
        EntityModel entityModel = new EntityModel();
        entityModel.setTestId(1L);
        entityModel.setTestName("Name");
        Optional<EntityModel> ofResult = Optional.of(entityModel);
        when(h2DBRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(entityModel, h2DataServiceImpl.getEntityByTestId(1L));
        verify(h2DBRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link H2DataServiceImpl#addEntity(EntityModel)} (EntityModel)}
     */
    @Test
    void testAddEntity() {
        EntityModel entityModel = new EntityModel();
        entityModel.setTestId(1L);
        entityModel.setTestName("Name");
        when(h2DBRepository.save((EntityModel) any())).thenReturn(entityModel);

        EntityModel entityModel1 = new EntityModel();
        entityModel1.setTestId(1L);
        entityModel1.setTestName("Name");
        assertTrue(h2DataServiceImpl.addEntity(entityModel1));
        verify(h2DBRepository).save((EntityModel) any());
    }

    /**
     * Method under test: {@link H2DataServiceImpl#updateEntity(EntityModel)} (EntityModel)}
     */
    @Test
    void testUpdateEntity() {
        EntityModel entityModel = new EntityModel();
        entityModel.setTestId(1L);
        entityModel.setTestName("Name");
        when(h2DBRepository.save((EntityModel) any())).thenReturn(entityModel);
        EntityModel entityModel1 = new EntityModel();
        entityModel1.setTestId(1L);
        entityModel1.setTestName("Name");

        h2DataServiceImpl.updateEntity(entityModel1);
        verify(h2DBRepository).save((EntityModel) any());
        assertEquals(1L, entityModel1.getTestId());
        assertEquals("Name", entityModel1.getTestName());
        assertTrue(h2DataServiceImpl.getAllEntity().isEmpty());
    }

    /**
     * Method under test: {@link H2DataServiceImpl#deleteEntity(long)} (long)}
     */
    @Test
    void testDeleteEntity() {
        EntityModel entityModel = new EntityModel();
        entityModel.setTestId(1L);
        entityModel.setTestName("Name");

        Optional<EntityModel> ofResult = Optional.of(entityModel);
        doNothing().when(h2DBRepository).delete((EntityModel) any());
        when(h2DBRepository.findById((Long) any())).thenReturn(ofResult);
        h2DataServiceImpl.deleteEntity(1L);
        verify(h2DBRepository).findById((Long) any());
        verify(h2DBRepository).delete((EntityModel) any());
        assertTrue(h2DataServiceImpl.getAllEntity().isEmpty());
    }
    @Test
    void getEntityByTestId_notFound() {
        // Create a test ID that does not exist in the database
        long testId = 99L;

        // Simulate the findById method returning an empty Optional
        when(h2DBRepository.findById(testId)).thenReturn(Optional.empty());

        // Call the getEntityByTestId method with the test ID and verify that it throws a ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> h2DataServiceImpl.getEntityByTestId(testId));

        // Verify that the findById method was called with the test ID
        verify(h2DBRepository).findById(testId);
    }






}

