
package edu.holycross.shot.tabulae

import org.scalatest.FlatSpec

class FstStemParsingSpec extends FlatSpec {


  "The FstStem object" should "parse the stem part of an FST reply into an FstStem object" in  {
    val stemFst = "<u>dev1.n1</u><u>lexent.n1</u>femin<noun><fem><a_ae>"

    val stemObj = FstStem(stemFst)
    stemObj match {
      case nounObj: NounStem => {
        assert(nounObj.stemId == "dev1.n1")
        assert(nounObj.lexentId == "lexent.n1")
        assert(nounObj.stem == "femin")
        assert(nounObj.inflClass == "a_ae")
        assert(nounObj.gender == "fem")
      }
      case _ => fail("Should have created NounStem")
    }
  }

  it should "parse verb stems"in {
    val stemFst = "<u>dev1.v1</u><u>lexent.v1</u><#>am<verb><conj1>"
    val stemObj = FstStem(stemFst)
    stemObj match {
      case verbObj: VerbStem => {
        assert(verbObj.stemId == "dev1.v1")
        assert(verbObj.lexentId == "lexent.v1")
        assert(verbObj.inflClass == "conj1")
        assert(verbObj.stem == "<#>am")
      }
      case _ => fail("Should have created VerbStem")
    }
  }

  it should "parse indeclinable stems" in {
      val stemFst = "<u>pliny.indecl1</u><u>lexent.tbd</u>cum<indecl><conjunct>"
      val stemObj = FstStem(stemFst)
      stemObj match {
        case indeclObj: IndeclStem => {
          assert(indeclObj.stemId == "pliny.indecl1")
          assert(indeclObj.lexentId == "lexent.tbd")
          assert(indeclObj.pos == "conjunct")
          assert(indeclObj.stem == "cum")
        }
        case _ => fail("Should have created IndeclStem")
      }
  }
  it should "parse adjective stems" in pending
  it should "parse participial stems" in pending
  it should "parse infinitive stems" in pending
  it should "parse adverbial stems" in pending

}
