import java.util.*;
import java.sql.*;
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
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","Arun@2001");

            //without using preparedstatement
            /*Statement statement  = connect.createStatement();
            StdMark object_of_student = new StdMark(inp1, inp2, inp3, inp4, inp5, inp6);
            String res=object_of_student.result();
            int updating_mark = statement.executeUpdate("INSERT INTO mark VALUES('"+inp1+"',"+inp2+","+inp3+","+inp4+","+inp5+","+inp6+",'"+res+"')");
            ResultSet output = statement.executeQuery("SELECT result FROM mark WHERE nam='"+inp1+"'");*/

            StdMark object_of_student = new StdMark(inp1, inp2, inp3, inp4, inp5, inp6);
            String res=object_of_student.result();
            PreparedStatement statement = connect.prepareStatement("INSERT INTO mark VALUES(?,?,?,?,?,?,?)");
            statement.setString(1,inp1);
            statement.setInt(2,inp2);
            statement.setInt(3,inp3);
            statement.setInt(4,inp4);
            statement.setInt(5,inp5);
            statement.setInt(6,inp6);
            statement.setString(7,res);
            int updating_mark = statement.executeUpdate();
            PreparedStatement stmnt = connect.prepareStatement("SELECT result FROM mark WHERE nam =?");
            stmnt.setString(1,inp1);
            ResultSet output = stmnt.executeQuery();
            while(output.next()) {
                String prt = output.getString("result");
                System.out.println(prt);
            }
            //statement.close();
            connect.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
class StdMark{
    int average_mark;
    String name;
    String pass_fail;
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

    String result(){
        average();
        System.out.println(average_mark);
        if (ValueRange.of(1,34).isValidIntValue(average_mark)){
            pass_fail="Fail";
        }
        else if(average_mark>34 && average_mark<=60){
            pass_fail="passed with grade C";
        }
        else if(average_mark>60 && average_mark<=80){
            pass_fail="passed with grade B";
        }
        else if(average_mark>80 && average_mark<=100){
            pass_fail="passed with grade A";
        }
        return pass_fail;
    }


}