
%% latin.fst : a Finite State Transducer for ancient latin morphology
%
% All symbols used in the FST:
#include "/workspace/tabulae/parsers/horace-morphology/symbols.fst"

% Dynamically loaded lexica of stems:
$stems$ = "/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-adjectives.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-compoundverbs.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-indeclinables.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-irregcompoundgerundives.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-irregcompoundgerunds.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-irregcompoundinfinitives.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-irregcompoundparticiples.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-irregcompoundsupines.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-irregcompoundverbs.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-irregnouns.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-irregparticiples.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-irregpronouns.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-irregverbs.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-nouns.fst" |\
"/workspace/tabulae/parsers/horace-morphology/lexica/lexicon-verbs.fst"

% Dynamically loaded inflectional rules:
$ends$ = "</workspace/tabulae/parsers/horace-morphology/inflection.a>"

% Morphology data is the crossing of stems and endings:
$morph$ = $stems$ <div> $ends$

% Acceptor (1) filters for content satisfying requirements for
% morphological analysis and  (2) maps from underlying to surface form
% by suppressing analytical symbols, and allowing only surface strings.
$acceptor$ = "</workspace/tabulae/parsers/horace-morphology/acceptor.a>"

% Final transducer:
$morph$ || $acceptor$

