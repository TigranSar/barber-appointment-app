import java.io.File;

public class Main {
    public static void main(String[] args) {
        String deletedDir = "C:/Users/benja/IdeaProjects/SpringLesson";
        String shortDir = "/uploads/photo_3f4cf05d-2cd6-43cc-92a9-0b0260ea5d36.jpg";
        File file = new File(deletedDir+shortDir);
        System.out.println(file.delete());
    }
}
