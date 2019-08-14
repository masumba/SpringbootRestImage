package com.demo.image.rest.restimage.repository;

import com.demo.image.rest.restimage.model.DBFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends CrudRepository<DBFile,String> {
}
