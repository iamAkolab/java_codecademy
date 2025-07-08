# Language Families
Languages, just like people, often have families. You can even map language lineage a bit like a family tree.

Java can help us build a model to track inherited traits across families. In this case, we’ll focus on something that often varies between language families: word order — where the subject, verb, and object would go in a sentence.

For example, in English, you would use subject-verb-object order:

| subject	| verb	| object |
|---------| ----- | ------ |
| She	| eats | 	noodles| 

But in Japanese, you would use subject-object-verb order:

| subject	| object	| verb |
| ------- | ------  | ------ |
| 彼女は	| 麺を	| 食べる |
| (she)	| (noodles)	| (eats)|

Your Language Inheritance package has three Java files:

* Language.java: for the Language parent class, which serves as the template for all languages.
* Mayan.java: for Mayan, a child class of Language modeled after the Mayan language family.
* SinoTibetan.java: for SinoTibetan, a child class of Language modeled after the Sino-Tibetan language family.
Build out a Java package with these classes to model real-world language families.
