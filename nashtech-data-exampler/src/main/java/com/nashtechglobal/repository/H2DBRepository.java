package com.nashtechglobal.repository;


import com.nashtechglobal.model.EntityModel;
import org.springframework.stereotype.Repository;


@Repository
public interface H2DBRepository extends BaseRepository<EntityModel, Long> {
}
