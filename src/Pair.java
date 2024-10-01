public class Pair {
    String subject;
    String teacher;
    String meetLink;
    String day;

    public Pair(String subject, String teacher, String meetLink, String day) {
        this.subject = subject;
        this.teacher = teacher;
        this.meetLink = meetLink;
        this.day = day;
    }

    @Override
    public String toString() {
        return subject + "," + teacher + "," + meetLink + "," + day;
    }

    public static Pair fromString(String line) {
        String[] parts = line.split(",");
        return new Pair(parts[0], parts[1], parts[2], parts[3]);
    }
}
