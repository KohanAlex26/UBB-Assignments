package run;

import java.io.*;
import java.util.Scanner;


import domain.A;
import domain.C;
import domain.Student;
import domain.B;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class main{
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1,2,3,4);
        int a=integers.stream().map(x->x*2).reduce(1,(x,y)->x*y);
//        for(int i=0;i<a.size();i++){
//            System.out.println(a.get(i));
//        }
System.out.println(a);
////        int len=integers.size();
//        int p=integers.stream().reduce(1,(x,y)->x*2);
//        System.out.println(p);
//        List<Integer> integers = Arrays.asList(3,2,1,5,4);
//        int max1=integers.stream().filter(x->x>1).max(Integer::compare).get();
//        System.out.println(max1);
////
//        Stream.of("a1", "a2", "a3")
//                .map(s -> s.substring(1)).mapToInt(Integer::parseInt)
//                .max()
//                .ifPresent(System.out::println);

//
//        List<String> string=Arrays.asList("a","b");
//        List<Integer> integers = Arrays.asList(3,2,1,5,4);
//        List<int> a=integers.stream().map(String::length);
//        int mn=integers.stream().filter(x->x>1)
//                .reduce(999999,Integer::min);
//        System.out.println(mn);

//        List<Integer> integers = Arrays.asList(3,2,1,5,4);
//        int mx=integers.stream().filter(x->x>1)
//                .reduce(0,Integer::max);
//        System.out.println(mx);

//        Stream.of(1,2,3,4)
//                .filter(x->x>1)
//                .mapToInt(Integer::valueOf)
//                .max()
//                .ifPresent(System.out::println);

//        A a=new A(1);
//        System.out.println(a.getS());
//        Stream<String>.

//        List<Integer> integers = Arrays.asList(3,2,1,5,4);
//        Integer p = integers.stream()
//                .reduce(1, (x,y)->x*y);
//        System.out.println(p);
//        List<Integer> b=integers.stream().filter(x->x>1).sorted().collect(Collectors.toList());
//        for(int i=0;i<b.size();i++){
//            System.out.println(b.get(i));
//        }


        //
//        List<String> list=Arrays.asList("ab2","bc3","de4","de4");
//        List<String> list1=list.stream().filter(x->Integer.parseInt(x.substring(2))>2).collect(Collectors.toList());
//        list1.forEach(System.out::println);
//        list1.forEach(x->System.out.println(x));
//        for(int i=0;i<list1.size();i++){
//            System.out.println(list1.get(i));
//        }

//
//        List<String> list2=list.stream().map(String::toUpperCase).collect(Collectors.toList());
//        for(int i=0;i<list2.size();i++){
//            System.out.println(list2.get(i));
//        }
////        int a=list.stream().map(x -> x.substring(2)).mapToInt(Integer::parseInt).reduce(1,(x,y)->x*y);
//        System.out.println(a);
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//        List<Integer> twoEvenSquares =
//                numbers.stream()
//                        .filter(n -> {System.out.println("filtering " + n); return n % 2 == 0;})
//                        .map(n -> { System.out.println("mapping " + n); return n * n;})
//                        .limit(2)
//                        .collect(Collectors.toList());
//        for(int i=0;i<twoEvenSquares.size();i++){
//            System.out.println(twoEvenSquares.get(i));
//        }

//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//        double twoEvenSquares =
//                numbers.stream()
//                        .filter(n ->n%2==0)
//                        .limit(2)
//                        .collect(Collectors.averagingInt(Integer::intValue));
//        System.out.println(twoEvenSquares);

//        List<Character> numbers = Arrays.asList('a','b','c');
//        List<Character> twoEvenSquares =
//                numbers.stream()
//                        .filter(n ->n>'a')
//                        .map(Character::toUpperCase)
//                        .limit(2)
//                        .collect(Collectors.toList());
//        for(int i=0;i<twoEvenSquares.size();i++){
//            System.out.println(twoEvenSquares.get(i));

//    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//    Double averageAge =
//            numbers
//                    .stream()
//                    .collect(Collectors.averagingInt(Integer::valueOf));
//    System.out.println(averageAge);

//        List<Integer> a=Arrays.asList(1,2,3,4,5,6,7,8,9);
//        int product=a.stream().filter(x->x%3!=0).reduce(1,
//                (element1, element2) -> element1 * element2);
//        System.out.println(product);
//
//        List<Integer> b=Arrays.asList(1,2,3,4,5,6,7,8,9);
//        b.stream().filter(x->x%3!=0).max().ifPresent(System.out::println);;

//        List<String> a=Arrays.asList("a1");
//        a.stream().map(s->s.substring(1)).mapToInt(Integer::parseInt).max().ifPresent(System.out::println);;
//        A a=new A(1);
//        Random rand = new Random();
//        for(int i=0;i<10;i++){
//            System.out.println(rand.nextInt(10));
//        }
        //1
        /*Scanner scanner=new Scanner(System.in);
        String inputString=scanner.nextLine();
        System.out.println(inputString);*/

        //2
        /*FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("fisier.txt");
            out = new FileOutputStream("fisier2.txt");
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            System.err.println("Eroare"  + e);
        } finally {
            if (in != null) try {
                in.close();
            } catch (IOException e) {
                System.err.println("eroare " + e);
            }
            if (out != null) try {
                out.close();
            } catch (IOException e) {
                System.err.println("eroare " + e);
            }
        }*/

        //3
        /*FileReader input = null;
        FileWriter output = null;
        try {
            input = new FileReader("fisier.txt");
            output = new FileWriter("fisier2.txt");
            int c;
            while ((c = input.read()) != -1) output.write(c);
        } catch (IOException e) {
            System.err.println("Eroare la citire / scriere" + e);
        } finally {
            if (input != null) try {
                input.close();
            } catch (IOException e) {
                System.err.println("eroare" + e);
            }
            if (output != null) try {
                output.close();
            } catch (IOException e) {
                System.err.println("Eroare" + e);
            }
        }*/

        //4
        /*List<Student> studs=new ArrayList<Student>();
        studs.add(new Student("a",1));
        studs.add(new Student("b",2));
        main.printStudentiDataOutput(studs,"fisier2.txt");*/

        //5
        //List<Student> studs = main.citesteStudentiDataInput("fisier.txt");
        //System.out.println(studs.get(0).getNume());
        //main.printStudentiDataOutput(studs,"fisier2.txt");

        //6
//        List<Student> studs = main.citesteStudenti("fisier.txt");
//        printStudentiRAF(studs,"fisier2.txt");
//        //printStudentiPWTabel(studs, "fisier2.txt");
//        //main.printStudentiBW(studs, "fisier2.txt");
//        float f=3.1232131232131f;
//        System.out.printf("%6.3f%n", f);
//    }
//
//    public static void printStudentiDataOutput(List<Student> studs, String numefis) {
//        DataOutputStream output = null;
//        try {
//            output = new DataOutputStream(new FileOutputStream(numefis));
//            for (Student stud : studs) {
//                output.writeUTF(stud.getNume());
//                output.writeDouble(stud.getMedia());
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("Eroare scriere DO " + e);
//        } catch (IOException e) {
//            System.err.println("Eroare scriere DO " + e);
//        } finally {
//            if (output != null) try {
//                output.close();
//            } catch (IOException e) {
//                System.err.println("Eroare scriere DO " + e);
//            }
//        }
//    }
//
//    public static List<Student> citesteStudentiDataInput(String numefis) {
//        List<Student> studs = new ArrayList<Student>();
//        DataInputStream input = null;
//        try {
//            input = new DataInputStream(new FileInputStream(numefis));
//            while (true) {
//                String nume = input.readUTF();
//                double media = input.readDouble();
//                studs.add(new Student(nume, media));
//            }
//        } catch (EOFException e) {
//        } catch (FileNotFoundException e) {
//            System.err.println("Eroare citire" + e);
//        } catch (IOException e) {
//            System.err.println("Eroare citire DI" + e);
//        } finally {
//            if (input != null) try {
//                input.close();
//            } catch (IOException e) {
//                System.err.println("Eroare inchidere fisier" + e);
//            }
//        }
//        return studs;
//    }
//
//    public static List<Student> citesteStudenti(String numefis) {
//        List<Student> ls = new ArrayList<Student>();
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new FileReader(numefis));
//            String linie;
//            while ((linie = br.readLine()) != null) {
//                String[] elems = linie.split("[|]");
//                if (elems.length < 2) {
//                    System.err.println("Linie invalida " + linie);
//                    continue;
//                }
//                Student stud = new Student(elems[0], Double.parseDouble(elems[1]));
//                ls.add(stud);
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("Eroare citire " + e);
//        } catch (IOException e) {
//            System.err.println("Eroare citire " + e);
//        } finally {
//            if (br != null) try {
//                br.close();
//            } catch (IOException e) {
//                System.err.println("Eroare inchidere fisier: " + e);
//            }
//        }
//        return ls;
//    }
//
//    public static void printStudentiBW(List<Student> studs, String numefis) {
//        BufferedWriter bw = null;
//        try {
//            bw = new BufferedWriter(new FileWriter(numefis));
//            //bw=new BufferedWriter(new FileWriter(numefis,true));
//            for (Student stud : studs) {
//                bw.write(stud.getNume() + '|' + stud.getMedia());
//                bw.newLine();    //scrie sfarsitul de linie
//            }
//        } catch (IOException e) {
//            System.err.println("Eroare scriere BW " + e);
//        } finally {
//            if (bw != null) try {
//                bw.close();
//            } catch (IOException e) {
//                System.err.println("Eroare inchidere fisier " + e);
//            }
//        }
//    }
//
//    public static void printStudentiPrintWriter(List<Student> studs, String numefis) {
//        PrintWriter pw = null;
//        try {
//            pw = new PrintWriter(numefis);
//            for (Student stud : studs) {
//                pw.println(stud.getNume() + '|' + stud.getMedia());
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("Eroare scriere PW " + e);
//        } finally {
//            if (pw != null) pw.close();
//        }
//    }
//
//    public static void printStudentiPWTabel(List<Student> studs, String numefis) {
//        PrintWriter pw = null;
//        try {
//            pw = new PrintWriter(numefis);
//            String linie = getLinie('-', 48);
//            int crt = 0;
//            for (Student stud : studs) {
//                pw.println(linie);//pw.printf("| %3d | %-30s | %5.2f |%n",(++crt),stud.getNume(),stud.getMedia());
//                pw.format("| %3d | %-30s | %5.2f |%n", (++crt), stud.getNume(), stud.getMedia());
//            }
//            if (crt > 0) pw.println(linie);
//        } catch (FileNotFoundException e) {
//            System.err.println("Eroare scriere PWTabel " + e);
//        } finally {
//            if (pw != null) pw.close();
//        }
//    }
//
//    public static String getLinie(char c, int length) {
//        char[] tmp = new char[length];
//        Arrays.fill(tmp, c);
//        return String.valueOf(tmp);
//    }
//
//    public static void printStudentiRAF(List<Student> studs, String numefis) {
//        RandomAccessFile out = null;
//        try {
//            out = new RandomAccessFile(numefis, "rw");
//            for (Student stud : studs) {
//                out.writeUTF(stud.getNume());
//                out.writeDouble(stud.getMedia());
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("Eroare RAF " + e);
//        } catch (IOException e) {
//            System.err.println("Eroare scriere RAF " + e);
//        } finally {
//            if (out != null) try {
//                out.close();
//            } catch (IOException e) {
//                System.err.println("Eroare inchidere fisier " + e);
//            }
//        }
    }
}
