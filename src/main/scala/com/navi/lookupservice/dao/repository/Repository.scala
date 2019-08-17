package com.navi.lookupservice.dao.repository

import java.util

trait Repository[T] {
  def find(id: Long) : T

  def findAll() : util.Collection[T]

  def save(value: T) : Long

  def saveAll(args: util.Collection[T])

  def update(value: T) : T

  def delete(id: Long) : T

}
