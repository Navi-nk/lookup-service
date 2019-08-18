package com.navi.lookupservice.controller

import java.util.stream.Collectors

import com.navi.lookupservice.dao.LookupDao
import com.navi.lookupservice.model.Lookup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

@RestController
class LookupController @Autowired() (val lookupDao: LookupDao) {

  @PostMapping(Array("save"))
  def saveLookup(@RequestBody lookup: Lookup): Long = {
    lookupDao.save(lookup)
  }

  @PutMapping(Array("save/{id}"))
  def saveLookup(@PathVariable id: Long, @RequestBody lookup: Lookup): Long = {
    lookup.id = id
    lookupDao.update(lookup)
  }

  @DeleteMapping(Array("delete/{id}"))
  def deleteLookup(@PathVariable id: Long): Long = {
    lookupDao.delete(id)
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
