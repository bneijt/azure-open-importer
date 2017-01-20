package nl.bigdatarepublic.importer

import java.util.concurrent.TimeUnit

import org.joda.time.DateTime
import scala.collection.immutable.Iterable

class DateSource(startingFrom: DateTime) extends Iterable[DateTime] {

  class DateSourceIterator extends Iterator[DateTime] {
    var currentDate = startingFrom

    override def hasNext: Boolean = true

    override def next(): DateTime = {
      currentDate = currentDate.plusDays(1)
      if (currentDate.isAfterNow()) {
        Thread.sleep(TimeUnit.DAYS.toMillis(1) + TimeUnit.MINUTES.toMillis(10))
      }
      currentDate
    }

  }

  override def iterator: Iterator[DateTime] = new DateSourceIterator
}
