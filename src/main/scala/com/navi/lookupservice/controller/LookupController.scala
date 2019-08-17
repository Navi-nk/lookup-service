package com.navi.lookupservice.controller

import java.util.stream.Collectors

import com.navi.lookupservice.dao.LookupDao
import com.navi.lookupservice.model.Lookup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, PostMapping, RequestBody, RestController}

import scala.jdk.CollectionConverters._

@RestController
class LookupController @Autowired() (val lookupDao: LookupDao) {

  @PostMapping(Array("save"))
  def saveLookup(@RequestBody lookup: Lookup): Long = {
    lookupDao.save(lookup)
  }

  @GetMapping(Array("fetch/{id}"))
  def fetchLookup(@PathVariable id: Long): Lookup = {
    lookupDao.find(id)
  }

  @GetMapping(Array("fetch"))
  def fetchAllLookup(): java.util.List[Lookup] = {
    lookupDao.findAll().stream().collect(Collectors.toList[Lookup])
  }
}
