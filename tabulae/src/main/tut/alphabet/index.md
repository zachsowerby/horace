---
title: An alphabet for your parser
layout: page
---

In a `tabulae` dataset, the directory `orthography` contains a file `alphabet.fst` where the alphabet for your corpus is explicitly enuerated.

This is the one component of a `tabulae` dataset that is not recorded as a delimited-text table.  Instead, `alphabet.fst` is directly written in the notation of the Stuttgart FST toolkit.

Several model alphabets are included in the template that `tabulae` installs with the `sbt corpus` task.  You can choose one of these and copy it to `alphabet.fst`.
