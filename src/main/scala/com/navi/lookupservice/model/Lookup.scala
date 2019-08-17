package com.navi.lookupservice.model

import scala.beans.BeanProperty
class Lookup extends Serializable{

  @BeanProperty var id: Long = _
  @BeanProperty var identifier: String = _
  @BeanProperty var name: String = _
}