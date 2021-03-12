package domain;

public class Student {
    public String Nume;
    public double Media;

    public Student(String nume, double media) {
        Nume = nume;
        Media = media;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public double getMedia() {
        return Media;
    }

    public void setMedia(double media) {
        Media = media;
    }
}
