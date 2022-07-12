import java.util.*;
import java.time.temporal.ValueRange;
public class Mark {
    public static void main(String ab[]){
        Scanner a = new Scanner(System.in);
        System.out.println("Enter your name");
        String inp1 = a.nextLine();
        System.out.println("Enter Tamil mark");
        int inp2=a.nextInt();
        System.out.println("Enter English mark");
        int inp3=a.nextInt();
        System.out.println("Enter Maths mark");
        int inp4=a.nextInt();
        System.out.println("Enter Science mark");
        int inp5=a.nextInt();
        System.out.println("Enter Social Science mark");
        int inp6=a.nextInt();
        StdMark object_of_student= new StdMark(inp1,inp2,inp3,inp4,inp5,inp6);
        object_of_student.result();
    }
}
class StdMark{
    int average_mark;
    String name;
    int tamil,english,math,science,social_science;
    StdMark(String name,int tamil,int english,int math,int science,int social_science){
        this.tamil=tamil;
        this.english=english;
        this.name=name;
        this.math=math;
        this.science= science;
        this.social_science=social_science;
    }

    void average(){
        average_mark=(tamil+english+math+science+social_science)/5;
        //return average_mark;
    }

    void result(){
        average();
        System.out.println(average_mark);
        if (ValueRange.of(1,34).isValidIntValue(average_mark)){
            System.out.println(name+" Fail");
        }
        else if(average_mark>34 && average_mark<60){
            System.out.println(name+" passed with grade A");
        }
        else if(average_mark>60 && average_mark<80){
            System.out.println(name+" passed with grade B");
        }
        else if(average_mark>80 && average_mark<=100){
            System.out.println(name+" passed with grade A");
        }
    }


}