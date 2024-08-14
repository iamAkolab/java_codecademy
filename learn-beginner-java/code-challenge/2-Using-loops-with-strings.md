# Code Challenge: Using Loops With Strings.
## String Traversal
Strings are objects that hold text-based information. Did you know we can also iterate through a String using a loop? That’s right- similar to arrays and ArrayLists, we can traverse a String in order to access each individual element.

This ability to traverse a String can be extremely useful. For example, an attempt was made to copy and send over the full poem of “The Raven” by Edgar Allan Poe; however, it was reported that two of the stanzas showed up like this:

```
".<romr<v<N" sa <man hcus htiW            
,rood r<bmahc sih <voba tsub d<rutplucs <ht nopu tsa<b ro driB
—rood r<bmahc sih <voba drib gni<<s htiw d<ss<lb saw t<y r<v<    
gni<b namuh gnivil on taht gni<<rga pl<h tonnac <w roF    
;<rob ycnav<l<r <lttil—gnina<m <lttil r<wsna sti hguohT
,ylnialp os <sruocsid ra<h ot lwof ylniagnu siht d<ll<vram I hcuM    

".<romr<v<N" n<vaR <ht htouQ            
"!<rohs nainotulP s'thgiN <ht no si <man yldrol yht tahw <m ll<T
—<rohs ylthgiN <ht morf gnir<dnaw n<vaR tn<icna dna mirg yltsahG
,n<varc on <rus tra" ,dias I ",uoht ,n<vahs dna nrohs <b ts<rc yht hguohT"
,<row ti <cnan<tnuoc <ht fo muroc<d nr<ts dna <varg <ht yB
,gnilims otni ycnaf das ym gniliug<b drib ynob< siht n<hT
```
We’ve been tasked with resolving the problem using String traversal. Let’s get started!

Create a new string with the characters reversed
The first known issue is that the poem text became reversed. We’ll use an algorithm to reverse all the characters. We’ll need to take the following steps:

* Declare a new, empty String to store the reversed text.
* Create a for loop that will iterate through every value of our String.
* Inside the loop, grab a single element at the String index of the current iteration.
* Also within the loop, update the new String by prepending the current element’s value to the current String value.
* Return the new String once the loop is complete.


Complete the function reverseString() which has one parameter: String text. The function must return a String which stores the characters of text in reverse. For example, if the method is called with the argument "abc", it should return "cba".

To accomplish this, you will need to use a for loop to traverse text.
```
class Reverse {
  public static String reverseString(String text) {
    // Add your code below
   String reversed = new String("");
 
  for (int i=0; i < text.length(); i++){
    char nextCharacter = text.charAt(i);
    reversed = nextCharacter + reversed;
  }
  return reversed;
   
  }

  public static void main(String[] args) {
    String text = new String("\".<romr<v<N\" sa <man hcus htiW\n ,rood r<bmahc sih <voba tsub d<rutplucs <ht nopu tsa<b ro driB —rood r<bmahc sih <voba drib gni<<s htiw d<ss<lb saw t<y r<v< gni<b namuh gnivil on taht gni<<rga pl<h tonnac <w roF ;<rob ycnav<l<r <lttil—gnina<m <lttil r<wsna sti hguohT ,ylnialp os <sruocsid ra<h ot lwof ylniagnu siht d<ll<vram I hcuM\n\n\".<romr<v<N\" n<vaR <ht htouQ\n \"!<rohs nainotulP s’thgiN <ht no si <man yldrol yht tahw <m ll<T —<rohs ylthgiN <ht morf gnir<dnaw n<vaR tn<icna dna mirg yltsahG ,n<varc on <rus tra\" ,dias I \",uoht ,n<vahs dna nrohs <b ts<rc yht hguohT\",<row ti <cnan<tnuoc <ht fo muroc<d nr<ts dna <varg <ht yB ,gnilims otni ycnaf das ym gniliug<b drib ynob< siht n<hT");

   System.out.println(reverseString(text));
   
 }
}
```

We first create an empty String called reversed- we’ll use this String to store the reversed value of text.

Then, we create a for loop. We want to iterate through each value of text; therefore, we set our loop header to continue to iterate until i < text.length().

Inside our loop, we create a char variable called nextCharacter and assign it to store the current element value of text using this code: nextCharacter = text.charAt(i);

The last thing we do in our loop is to update the value of reversed with the code reversed = nextCharacter + reversed; This will place the current element value (stored in nextCharacter) at the beginning of our reversed variable. Doing this creates our new, reversed String. Finally, outside the loop, we return the value of reversed.

## Find if one or more substrings has a particular property
Ah, this is a lot easier to read now. The other issue we’ve come across is that every e was somehow replaced with the symbol <. In order to take every < in our String and replace it with e, we’ll have to do the following:

* Create a loop that iterates through every element of our String.
* Check if the element is equivalent to the character we want to replace.
* If the above condition is true, replace the element with a new value. To do this, we will update our String to equal the following: a substring of all the values before that element’s index value + the replacement value + a substring of all the values that appear after that element’s index.
* Outside the loop, return the value of our String.

Complete the function replaceCharacter(). The function takes in three parameters: String text, char charToReplace, and char replacement.
The function must return text with every charToReplace value inside it replaced with replacement.

For example, if we called the function replaceCharacter("abracadabra", 'a', 'i'), the function should return "ibricidibri".
```
class Replace {
  public static String replaceCharacter(String text, char charToReplace, char replacement) {
    // Add your code below
    for (int i = 0; i < text.length(); i++){
    if (text.charAt(i) == charToReplace) {
      text = text.substring(0, i) + replacement + text.substring(i+1);      
    }
 }
  return text; 
    
  }
 
  public static void main(String[] args) {
    String text = "Th<n this <bony bird b<guiling my sad fancy into smiling, By th< grav< and st<rn d<corum of th< count<nanc< it wor<,\"Though thy cr<st b< shorn and shav<n, thou,\" I said, \"art sur< no crav<n, Ghastly grim and anci<nt Rav<n wand<ring from th< Nightly shor<— T<ll m< what thy lordly nam< is on th< Night's Plutonian shor<!\" \nQuoth th< Rav<n \"N<v<rmor<.\"\n\nMuch I marv<ll<d this ungainly fowl to h<ar discours< so plainly, Though its answ<r littl< m<aning—littl< r<l<vancy bor<; For w< cannot h<lp agr<<ing that no living human b<ing <v<r y<t was bl<ss<d with s<<ing bird abov< his chamb<r door— Bird or b<ast upon th< sculptur<d bust abov< his chamb<r door, \nWith such nam< as \"N<v<rmor<.\"";
    System.out.println(replaceCharacter(text, '<', 'e'));
   
  }
}

Then this ebony bird beguiling my sad fancy into smiling, By the grave and stern decorum of the countenance it wore,"Though thy crest be shorn and shaven, thou," I said, "art sure no craven, Ghastly grim and ancient Raven wandering from the Nightly shore— Tell me what thy lordly name is on the Night's Plutonian shore!" 
Quoth the Raven "Nevermore."

Much I marvelled this ungainly fowl to hear discourse so plainly, Though its answer little meaning—little relevancy bore; For we cannot help agreeing that no living human being ever yet was blessed with seeing bird above his chamber door— Bird or beast upon the sculptured bust above his chamber door, 
With such name as "Nevermore."

```
We have three parameters:
* String text - This will hold a String value that we will iterate through.
* char charToReplace - This is the character we will be searching for in order to replace it.
* char replacement - This is the character we will use to replace charToReplace in our String.

First, we create a loop that loops from 0 until i < text.length(). Inside the loop, we use an if statement to check if the element at the index of the current iteration is equivalent to the value of charToReplace: text.charAt(i) == charToReplace

If the condition is true we need to update the value of text to replace the charToReplace value with replacement. For this, we use the code text = text.substring(0, i) + replacement + text.substring(i+1);.

We recreate text by concatenating three values. The first value, text.substring(0, i), will create a substring containing every element that came before the current index. The second value is replacement. The third value, text.substring(i+1) is a substring of every element in text that appears after the current index. By concatenating them with +, the charToReplace value at that index is replaced with replacement. Outside the loop, we return text.

Remember that Strings are immutable; this means that their value cannot be changed. Every time we replace a character, we are creating a brand new String.

## Determine the number of substrings that meet specific criteria
### Part 1
Something interesting about “The Raven” is its use of dialogue. How many lines of dialogue are present in the entire poem? To find out, we’ll need to do the following:

* Create a counter variable and set its initial value to 0.
* Loop through each element of the String.
* Check if the char type element is equivalent to a quotation mark (").
* If the condition above is true, increase the counter by 1.
* Outside the loop, divide the counter variable by 2.
* Return the counter variable. 

Complete the function findNumberOfQuotes() that takes in the parameter String text and returns an int value that represents the number of quotes in text.

For example, if we called findNumberOfQuotes("/"Hello there,/" said Codey, /"How are you?/""), the function would return 2.

Remember, each quote is enclosed in two quotation marks and /" is an escape sequence that allows a quote (") to appear within a String.
```
class Count {

  public static int findNumberOfQuotes(String text) {
    // Add your code below
    int numQuotes = 0;
  for (int i = 0; i < text.length(); i++){
    if (text.charAt(i) == '"') {
      numQuotes += 1;
    }
  }
  numQuotes /= 2;
  return numQuotes;
    
    
  }
  
  public static void main(String[] args) {
    String text = "Once upon a midnight dreary, while I pondered, weak and weary,Over many a quaint and curious volume of forgotten lore—While I nodded, nearly napping, suddenly there came a tapping,As of some one gently rapping, rapping at my chamber door.\"’Tis some visitor,\" I muttered, \"tapping at my chamber door—\nOnly this and nothing more.\"\n\nAh, distinctly I remember it was in the bleak December;And each separate dying ember wrought its ghost upon the floor. Eagerly I wished the morrow;—vainly I had sought to borrow \nFrom my books surcease of sorrow—sorrow for the lost Lenore—For the rare and radiant maiden whom the angels name Lenore—\nNameless here for evermore.\n\nAnd the silken, sad, uncertain rustling of each purple curtain\nThrilled me—filled me with fantastic terrors never felt before;\nSo that now, to still the beating of my heart, I stood repeating\n\"’Tis some visitor entreating entrance at my chamber door—Some late visitor entreating entrance at my chamber door;—\nThis it is and nothing more.\"\n\nPresently my soul grew stronger; hesitating then no longer,\"Sir,\" said I, \"or Madam, truly your forgiveness I implore; But the fact is I was napping, and so gently you came rapping, And so faintly you came tapping, tapping at my chamber door,That I scarce was sure I heard you\"—here I opened wide the door—\nDarkness there and nothing more.\n\nDeep into that darkness peering, long I stood there wondering, fearing, Doubting, dreaming dreams no mortal ever dared to dream before; But the silence was unbroken, and the stillness gave no token, And the only word there spoken was the whispered word, \"Lenore?\"This I whispered, and an echo murmured back the word, \"Lenore!\"—\nMerely this and nothing more.\n\nBack into the chamber turning, all my soul within me burning, Soon again I heard a tapping somewhat louder than before.\"Surely,\" said I, \"surely that is something at my window lattice;\nLet me see, then, what thereat is, and this mystery explore—Let my heart be still a moment and this mystery explore;—\n’Tis the wind and nothing more!\"\n\nOpen here I flung the shutter, when, with many a flirt and flutter, In there stepped a stately Raven of the saintly days of yore;Not the least obeisance made he; not a minute stopped or stayed he;But, with mien of lord or lady, perched above my chamber door— Perched upon a bust of Pallas just above my chamber door—Perched, and sat, and nothing more.\n\nThen this ebony bird beguiling my sad fancy into smiling,\nBy the grave and stern decorum of the countenance it wore,\"Though thy crest be shorn and shaven, thou,\" I said, \"art sure no craven,Ghastly grim and ancient Raven wandering from the Nightly shore—Tell me what thy lordly name is on the Night’s Plutonian shore!\"\nQuoth the Raven \"Nevermore.\"\n\nMuch I marvelled this ungainly fowl to hear discourse so plainly,Though its answer little meaning—little relevancy bore; For we cannot help agreeing that no living human being Ever yet was blessed with seeing bird above his chamber door—Bird or beast upon the sculptured bust above his chamber door,\nWith such name as \"Nevermore.\"\n\nBut the Raven, sitting lonely on the placid bust, spoke only That one word, as if his soul in that one word he did outpour. Nothing farther then he uttered—not a feather then he fluttered— Till I scarcely more than muttered \"Other friends have flown before—On the morrow he will leave me, as my Hopes have flown before.\"\nThen the bird said \"Nevermore.\"\n\nStartled at the stillness broken by reply so aptly spoken,\"Doubtless,\" said I, \"what it utters is its only stock and storeCaught from some unhappy master whom unmerciful Disaster Followed fast and followed faster till his songs one burden bore— Till the dirges of his Hope that melancholy burden bore Of ‘Never—nevermore’.\"\n\n But the Raven still beguiling all my fancy into smiling, Straight I wheeled a cushioned seat in front of bird, and bust and door; Then, upon the velvet sinking, I betook myself to linking Fancy unto fancy, thinking what this ominous bird of yore— What this grim, ungainly, ghastly, gaunt, and ominous bird of yore\n\nMeant in croaking \"Nevermore.\" This I sat engaged in guessing, but no syllable expressing To the fowl whose fiery eyes now burned into my bosom’s core; This and more I sat divining, with my head at ease reclining On the cushion’s velvet lining that the lamp-light gloated o’er, But whose velvet-violet lining with the lamp-light gloating o’er,\nShe shall press, ah, nevermore!\n\nThen, methought, the air grew denser, perfumed from an unseen censer Swung by Seraphim whose foot-falls tinkled on the tufted floor. \"Wretch,\" I cried, \"thy God hath lent thee—by these angels he hath sent thee Respite—respite and nepenthe from thy memories of Lenore; Quaff, oh quaff this kind nepenthe and forget this lost Lenore!\"\nQuoth the Raven \"Nevermore.\"\n\n\"Prophet!\" said I, \"thing of evil!—prophet still, if bird or devil!—Whether Tempter sent, or whether tempest tossed thee here ashore, Desolate yet all undaunted, on this desert land enchanted— On this home by Horror haunted—tell me truly, I implore—Is there—is there balm in Gilead?—tell me—tell me, I implore!\"\nQuoth the Raven \"Nevermore.\"\n\n\"Prophet!\" said I, \"thing of evil!—prophet still, if bird or devil! By that Heaven that bends above us—by that God we both adore— Tell this soul with sorrow laden if, within the distant Aidenn, It shall clasp a sainted maiden whom the angels name Lenore—Clasp a rare and radiant maiden whom the angels name Lenore.\"\nQuoth the Raven \"Nevermore.\"\n\n\"Be that word our sign of parting, bird or fiend!\" I shrieked, upstarting—\"Get thee back into the tempest and the Night’s Plutonian shore!Leave no black plume as a token of that lie thy soul hath spoken!Leave my loneliness unbroken!—quit the bust above my door!Take thy beak from out my heart, and take thy form from off my door!\"\nQuoth the Raven \"Nevermore.\"\n\nAnd the Raven, never flitting, still is sitting, still is sitting On the pallid bust of Pallas just above my chamber door; And his eyes have all the seeming of a demon’s that is dreaming,And the lamp-light o’er him streaming throws his shadow on the floor;And my soul from out that shadow that lies floating on the floor\nShall be lifted—nevermore!";
   
    System.out.println(findNumberOfQuotes(text)); 
  }
}
```
We first create numQuotes which will act as our counter variable. Then, we create a for loop that will iterate from 0 until i < text.length().

Then, we check if the current element value is a quotation mark using the condition text.charAt(i) == '"' We can also choose to use an escape sequence (\") as our condition. If we were searching for a String value instead of a char value, we would need to use \" because just " would have closed our String; using the escape sequence alerts the compiler that we will be searching for " as a value.

If the condition is true, we increase numQuotes by 1. Outside the loop, we use the /= compound assignment operator to divide the value of numQuotes by 2. We do this because a single quote is enclosed in two quotation marks ("). With this as our final value, we return numQuotes.

### Part 2
One of the most repeated words in the poem is “Nevermore”. Just how many times is it used throughout “The Raven”? Here’s what we can do to find out:

* Create a counter variable and set its initial value to 0.
* Loop through the String. In order to prevent an indexOutOfBounds error, we need to start the loop at the beginning of the String, but end the iteration before the loop control variable is equal to the length of the String minus the length of the String value we are searching for.
* In each iteration, check if the current element and the next several element values are equivalent to the text we are searching for. If it is, increase the counter variable by 1.
* Return the counter variable.

Complete the function findNumValues() which contains a String parameter called text and a String parameter called textToFind.

The function must return an int value that represents the number of times textToFind appears in text.

Hint: Be sure that your program accounts for different capitalizations.
```
class Count {
  public static int findNumValues(String text, String findText) {
    // Add your code below
     int count = 0;
  for (int i = 0; i <= text.length()-findText.length(); i++) {
    if (text.substring(i, i + findText.length()).equalsIgnoreCase(findText)) {
      count += 1;
    }
  }
  return count;
    

  }

  public static void main(String[] args) {
    String text = "Once upon a midnight dreary, while I pondered, weak and weary,Over many a quaint and curious volume of forgotten lore—While I nodded, nearly napping, suddenly there came a tapping,As of some one gently rapping, rapping at my chamber door.\"’Tis some visitor,\" I muttered, \"tapping at my chamber door—\nOnly this and nothing more.\"\n\nAh, distinctly I remember it was in the bleak December;And each separate dying ember wrought its ghost upon the floor. Eagerly I wished the morrow;—vainly I had sought to borrow \nFrom my books surcease of sorrow—sorrow for the lost Lenore—For the rare and radiant maiden whom the angels name Lenore—\nNameless here for evermore.\n\nAnd the silken, sad, uncertain rustling of each purple curtain\nThrilled me—filled me with fantastic terrors never felt before;\nSo that now, to still the beating of my heart, I stood repeating\n\"’Tis some visitor entreating entrance at my chamber door—Some late visitor entreating entrance at my chamber door;—\nThis it is and nothing more.\"\n\nPresently my soul grew stronger; hesitating then no longer,\"Sir,\" said I, \"or Madam, truly your forgiveness I implore; But the fact is I was napping, and so gently you came rapping, And so faintly you came tapping, tapping at my chamber door,That I scarce was sure I heard you\"—here I opened wide the door—\nDarkness there and nothing more.\n\nDeep into that darkness peering, long I stood there wondering, fearing, Doubting, dreaming dreams no mortal ever dared to dream before; But the silence was unbroken, and the stillness gave no token, And the only word there spoken was the whispered word, \"Lenore?\"This I whispered, and an echo murmured back the word, \"Lenore!\"—\nMerely this and nothing more.\n\nBack into the chamber turning, all my soul within me burning, Soon again I heard a tapping somewhat louder than before.\"Surely,\" said I, \"surely that is something at my window lattice;\nLet me see, then, what thereat is, and this mystery explore—Let my heart be still a moment and this mystery explore;—\n’Tis the wind and nothing more!\"\n\nOpen here I flung the shutter, when, with many a flirt and flutter, In there stepped a stately Raven of the saintly days of yore;Not the least obeisance made he; not a minute stopped or stayed he;But, with mien of lord or lady, perched above my chamber door— Perched upon a bust of Pallas just above my chamber door—Perched, and sat, and nothing more.\n\nThen this ebony bird beguiling my sad fancy into smiling,\nBy the grave and stern decorum of the countenance it wore,\"Though thy crest be shorn and shaven, thou,\" I said, \"art sure no craven,Ghastly grim and ancient Raven wandering from the Nightly shore—Tell me what thy lordly name is on the Night’s Plutonian shore!\"\nQuoth the Raven \"Nevermore.\"\n\nMuch I marvelled this ungainly fowl to hear discourse so plainly,Though its answer little meaning—little relevancy bore; For we cannot help agreeing that no living human being Ever yet was blessed with seeing bird above his chamber door—Bird or beast upon the sculptured bust above his chamber door,\nWith such name as \"Nevermore.\"\n\nBut the Raven, sitting lonely on the placid bust, spoke only That one word, as if his soul in that one word he did outpour. Nothing farther then he uttered—not a feather then he fluttered— Till I scarcely more than muttered \"Other friends have flown before—On the morrow he will leave me, as my Hopes have flown before.\"\nThen the bird said \"Nevermore.\"\n\nStartled at the stillness broken by reply so aptly spoken,\"Doubtless,\" said I, \"what it utters is its only stock and storeCaught from some unhappy master whom unmerciful Disaster Followed fast and followed faster till his songs one burden bore— Till the dirges of his Hope that melancholy burden bore Of ‘Never—nevermore’.\"\n\n But the Raven still beguiling all my fancy into smiling, Straight I wheeled a cushioned seat in front of bird, and bust and door; Then, upon the velvet sinking, I betook myself to linking Fancy unto fancy, thinking what this ominous bird of yore— What this grim, ungainly, ghastly, gaunt, and ominous bird of yore\n\nMeant in croaking \"Nevermore.\" This I sat engaged in guessing, but no syllable expressing To the fowl whose fiery eyes now burned into my bosom’s core; This and more I sat divining, with my head at ease reclining On the cushion’s velvet lining that the lamp-light gloated o’er, But whose velvet-violet lining with the lamp-light gloating o’er,\nShe shall press, ah, nevermore!\n\nThen, methought, the air grew denser, perfumed from an unseen censer Swung by Seraphim whose foot-falls tinkled on the tufted floor. \"Wretch,\" I cried, \"thy God hath lent thee—by these angels he hath sent thee Respite—respite and nepenthe from thy memories of Lenore; Quaff, oh quaff this kind nepenthe and forget this lost Lenore!\"\nQuoth the Raven \"Nevermore.\"\n\n\"Prophet!\" said I, \"thing of evil!—prophet still, if bird or devil!—Whether Tempter sent, or whether tempest tossed thee here ashore, Desolate yet all undaunted, on this desert land enchanted— On this home by Horror haunted—tell me truly, I implore—Is there—is there balm in Gilead?—tell me—tell me, I implore!\"\nQuoth the Raven \"Nevermore.\"\n\n\"Prophet!\" said I, \"thing of evil!—prophet still, if bird or devil! By that Heaven that bends above us—by that God we both adore— Tell this soul with sorrow laden if, within the distant Aidenn, It shall clasp a sainted maiden whom the angels name Lenore—Clasp a rare and radiant maiden whom the angels name Lenore.\"\nQuoth the Raven \"Nevermore.\"\n\n\"Be that word our sign of parting, bird or fiend!\" I shrieked, upstarting—\"Get thee back into the tempest and the Night’s Plutonian shore!Leave no black plume as a token of that lie thy soul hath spoken!Leave my loneliness unbroken!—quit the bust above my door!Take thy beak from out my heart, and take thy form from off my door!\"\nQuoth the Raven \"Nevermore.\"\n\nAnd the Raven, never flitting, still is sitting, still is sitting On the pallid bust of Pallas just above my chamber door; And his eyes have all the seeming of a demon’s that is dreaming,And the lamp-light o’er him streaming throws his shadow on the floor;And my soul from out that shadow that lies floating on the floor\nShall be lifted—nevermore!";
    System.out.println(findNumValues(text, "Nevermore"));
  }
}
```

We have three variables:

* text - this variable contains the String we will search through.
* findText - this variable is used to store the String value we are searching for.
* count - this variable will store the number of times we find findText in text.
  
Our loop header looks a little different than usual. The loop control variable will start at 0 and continue to increase by 1 until it equals the value of the length of text minus the length of "findText". Why subtract this? This has to do with the condition in our if statement.

Our if statement’s condition is text.substring(i, i + findText.length()).equalsIgnoreCase(findText). This condition checks if the current element value and the succeeding several element values is equivalent to the value of findText. Since a String is an object, we will use the equalsIgnoreCase() function to compare if two values are equal.

We used a substring to select the current element value as well as the additional elements: text.substring(i, i + findText.length()). The length of findText determines how many of the succeeding elements will be included in the substring.

Here’s a question - what would happen if we were on the final iteration of our loop and tried to check what the next several element values of our String were?

If you guessed that we would get an error, you’re correct. StringIndexOutOfBoundsException errors occur when we try to select an element that exists outside the range of our String. This is why our loop stops iterating when i <= text.length()-findText.length() + 1. If, for example, findText.length() is 9, we don’t need to check what the last eight element values are because there’s no way that substring will be equivalent to findText.

If the substring’s value did equal the value of findText, we increased the value of count by 1. Outside the loop, we can return the value of count.

## Conclusion
Great job reaching the end of these code challenges. Let’s go over what we accomplished by using loops with Strings:

* We created an algorithm that can create a new String containing the reversed value of another String.
* We used a loop to iterate through a String in order to identify if a substring contained a particular property.
* We determined the number of times a substring possessed a certain criterion by traversing a String.
