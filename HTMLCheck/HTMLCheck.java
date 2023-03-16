import java.util.*;
import java.io.*;

class HTMLCheck {
  public static void main(String[] args) {
    try {
        File data = new File("somehtml_bad.txt");
        ArrayList<String> result = checkFile(data);

        if(result.size() == 0){
          System.out.println("Your HTML tags are all paired!");
        }else{
          System.out.println("The following are missing an end tag: " + result);
        }
        
    } catch (Exception e) {
        System.out.println("file not found");
    }
  }

  public static ArrayList<String> checkFile(File f){
    ArrayList<String> starts = new ArrayList<>();
    try{
      Scanner s = new Scanner(f);
      while(s.hasNextLine()){
        String line[] = s.nextLine().split(" ");
        for(String tag: line){
          //System.out.println("starts: " + starts);
          if(isTag(tag)){
            //System.out.println("added: " + tag);
            starts.add(tag);
          }
          else if(isEnd(tag)){
            for(String word: starts){
              if(isMatch(word, tag)){
                //System.out.println("Remved: " + word);
                starts.remove(starts.indexOf(word));
                break;
              }

            }
          }
        }
          
      }

    }catch(Exception e){}
    return starts;
  } 

  public static boolean isTag(String tag){
    if(tag.startsWith("<") && tag.endsWith(">") && !tag.contains("/")){
      return true;
    }
    return false;
  }
  public static boolean isEnd(String tag){
    if(tag.startsWith("<") && tag.endsWith(">") && tag.contains("/")){
      return true;
    }
    return false;
  }
  public static boolean isMatch(String start, String end){
    end = end.substring(2, end.length());
    end = "<" + end;
    if(start.equals(end)){
      return true;
    }
    return false;
  }
}