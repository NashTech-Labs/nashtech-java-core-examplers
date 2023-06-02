package com.nashtechglobal.repository;


import com.nashtechglobal.model.EntityModel;
import org.springframework.stereotype.Repository;


@Repository
public interface MySqlDBRepository extends BaseRepository<EntityModel, Long> {
}
