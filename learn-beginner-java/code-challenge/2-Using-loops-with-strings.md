# Code Challenge: Using Loops With Strings
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
