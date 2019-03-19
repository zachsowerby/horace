package edu.holycross.shot.tabulae.builder

import better.files._
import better.files.File._
import better.files.Dsl._
import java.io.{File => JFile}


/** Object for converting tabular source to FST statements.
*/
object GerundRulesInstaller {

  /** Write FST rules for all Gerund data in a directory
  * of tabular files.
  *
  * @param srcDir Directory with inflectional rules.
  * @param targetFile File to write FST statements to.
  */
  def apply(srcDir: File, targetFile: File): Unit = {
    val GerundFst = fstForGerundRules(srcDir)
      if(GerundFst.nonEmpty) {
        targetFile.overwrite(GerundFst)
      } else {}
  }


  /** Compose FST statements for all tables of
  * Gerund data found in a directory.
  *
  * @param srcDir Directory with lexical tables.
  */
  def fstForGerundRules(srcDir: File) : String = {

    val GerundsFiles = srcDir.glob("*.cex").toVector
    val rules = GerundsFiles.flatMap(f =>
      f.lines.toVector.filter(_.nonEmpty).drop(1))
    val fst = gerundRulesToFst(rules.toVector)
    if (fst.nonEmpty) {
      "$gerundinfl$ = " + fst + "\n\n$gerundinfl$\n"
    } else {
      ""
    }
  }

  /** Compose FST for a single delimited-text line of a lexical
  * data table.
  *
  * @param line Line of data.
  */
  def gerundRuleToFst(line: String) : String = {
    val cols = line.split("#")
    //"gerund.conj1_1#conj1#andi#gen"
    if (cols.size < 4) {
      println(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n${line}")
      throw new Exception(s"Wrong number of columns ${cols.size}.\nCould not parse data line:\n s${line}")
    } else {
      //"gerund.conj1_1#conj1#andi#gen
      val fst = StringBuilder.newBuilder
      val ruleUrn = cols(0).replaceAll("_","\\\\_").
        replaceAll("\\.","\\\\.")
      val inflClass = cols(1).replaceAll("_","\\_")
      val inflString = cols(2) // DataInstaller.toFstAlphabet(cols(2))
      val grammCase = cols(3)



      fst.append(s" <${inflClass}><gerund>${inflString}<${grammCase}><u>${ruleUrn}</u>").toString
    }
  }

  /** Compose a single FST string for a Vector of rules
  * stated as one line of delimited-text each.
  *
  * @param data Vector of rules strings.
  */
  def gerundRulesToFst(data: Vector[String]) : String = {
    data.map(gerundRuleToFst(_)).mkString(" |\\\n")
  }

}
