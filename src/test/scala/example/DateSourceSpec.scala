package example

import nl.bigdatarepublic.importer.DateSource
import org.joda.time.DateTime
import org.scalatest._

class DateSourceSpec extends FlatSpec with Matchers {
  "The DateSource" should "generate the first date" in {
    val firstDate = DateTime.now().minusDays(1)
    new DateSource(firstDate).iterator.next() shouldEqual firstDate
  }
}
