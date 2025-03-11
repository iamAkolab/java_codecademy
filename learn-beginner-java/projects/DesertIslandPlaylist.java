import java.util.Arrays;
import java.util.ArrayList

class Playlist {

   public static void main(String[] args) {
    String[] favoriteSongs = new String[10];

    //favoriteSongs[0] = "Artist1 - SongTitle1";

    favoriteSongs[0] = "Song 1";
    favoriteSongs[1] = "Song 2";
    favoriteSongs[2] = "Song 3";
    favoriteSongs[3] = "Song 4";
    favoriteSongs[4] = "Song 5";
    favoriteSongs[5] = "Song 6";
    favoriteSongs[6] = "Song 7";
    favoriteSongs[7] = "Song 8";
    favoriteSongs[8] = "Song 9";
    favoriteSongs[9] = "Song 10";

    System.out.println(favoriteSongs[0]);
    System.out.println(favoriteSongs[1]);
    System.out.println(favoriteSongs[2]);

    ArrayList<String> desertIslandPlaylist = new ArrayList<String>();
    desertIslandPlaylist.add("favorite Song1");
    desertIslandPlaylist.add("favorite Song2");
    desertIslandPlaylist.add("favorite Song3");
    desertIslandPlaylist.add("favorite Song4");
    desertIslandPlaylist.add("favorite Song5");

    System.out.println(desertIslandPlaylist);

    desertIslandPlaylist.addAll(Arrays.asList(favoriteSongs));
     System.out.println(desertIslandPlaylist.size());

     //for (int i = 0; i < 5; i++) { desertIslandPlaylist.remove(0); }

     desertIslandPlaylist.remove("Song 1");
     desertIslandPlaylist.remove("Song 2");
     desertIslandPlaylist.remove("Song 3");
     desertIslandPlaylist.remove("Song 4");
     desertIslandPlaylist.remove("Song 5");

     System.out.println(desertIslandPlaylist);

     int indexA = desertIslandPlaylist.indexOf("favorite Song1");
     int indexB = desertIslandPlaylist.indexOf("Song 6");

     String tempA = "favorite Song1";
     String tempB = "Song 6";

     //if (indexA != -1 && indexB != -1) { // proceed with swap }

     desertIslandPlaylist.set(indexA, tempB);
     System.out.println(desertIslandPlaylist);

     desertIslandPlaylist.set(indexB, tempA);
     System.out.println(desertIslandPlaylist);


   }
  
}


/*
Want to try something else? Check the hint for ideas:

Build another desert island playlist for food recipes, movies, or TV shows.
Create a method that shuffles the song order.
Reverse the song order.
*/
