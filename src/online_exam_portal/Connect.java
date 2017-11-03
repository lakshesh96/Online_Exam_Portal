package online_exam_portal;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Connect {
    
    static String str;
    static String str2;
    static String str3;
    public void setText(String set , String set2, String set3){
        str = set;
        str2 = set2;
        str3 = set3;
    }
    
    /*static int str4;
    static String str5;
    public void setQs(int set4, String set5){
        str4 = set4;
        str5 = set5;
    }*/
    
    
    //For Screen2B
    static String getusr;
    static String getpass;
    static String setusr;
    static String pass;
    static String getname;
    static String name;
    static String setScreen;
    static String setScreen2;
    static String setTableName;
    static int setQuestion;
    static String setAnswer;
    static String setUserid;
    static String getUser1;
    static String setResult;
    static int flg;
    static String flg2;
    static String res0;
    static String res1;
    static String res2;
    static String res3;
    static String getTables;
    static int qs;
    

    
    public String getPass(){
        return pass;
    }
    
    public String getName(){
        return name;
    }
    
    public void setText(String s){
        setusr = s;
    }
    
    public void setScreen(String q){
        setScreen = q;
    }
    
    public void setScreen2(String w){
        setScreen2 = w;
    }
    
    public void createTable(String t){
        setTableName = t;
    }
    
    public void setQuestion(int m){
        setQuestion = m;
    }
    
    public void setAnswer(String n){
        setAnswer = n;
    }
    
    public void setUserId(String o){
        setUserid = o;
    }
    
    public void setResult(String p){
        getUser1 = p;
    }
    
    public void deletePrev(int a){
        qs =a;
    }
        
    /*public void getFlag(int p){
        flg = p;
    }*/
    
    public void finalResultUpdate(String n, String a, String s, String d){
        res0 = n;
        res1 = a;
        res2 = s;
        res3 = d;
    }
    
    public void checkDup(int a,String b)
    {
        flg = a;
        flg2 = b;
    }
    
    public static void connection(){
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int i, j;
        
        try {
            
            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oep?user=root&password=");
            stmt = con.createStatement();
            
            //for truncating duplicate table
            if(flg==1)
            {
                try {
                        ps = con.prepareStatement("show tables;");
                        rs = ps.executeQuery();
                        while(rs.next()){
                           getTables = rs.getString("Tables_in_oep");
                           

                           if(flg2.equals(getTables)){

                               ps = con.prepareStatement("truncate " + flg2 + ";");
                               ps.executeUpdate();
                               break;
                           }
                        }
                        flg=0;
                    }catch (Exception e) {
                        //System.out.println(e);
                        JOptionPane.showMessageDialog(null,e, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
            }
            
            
            
            //for setting result table
            if(res1!=null)
            {
                try {
                        
                        ps = con.prepareStatement("insert into result (uid, s_name, score, result) values (?, ?, ?, ?)");
                        ps.setString(1, res1);
                        ps.setString(2, res0);
                        ps.setString(3, res2);
                        ps.setString(4, res3);
                        i = ps.executeUpdate();
                        res1 = null;
                    }catch (Exception e) {
                        //System.out.println(e);
                        JOptionPane.showMessageDialog(null,e, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
            }
            
            
            
            /*/for repeted signin
            if(flg==1)
            {
                flg2 = flg2+flg;
            }
            if(flg2>1)
            {
                try {
                        
                        ps = con.prepareStatement("truncate " + setusr + " ;");
                        ps.executeQuery();
                    }catch (Exception e) {
                        //System.out.println(e);
                        JOptionPane.showMessageDialog(null,e, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
            }
            */
            
            
            //for calculating score
            if(getUser1!=null)
            {
                try {
                        
                        ps = con.prepareStatement("select count(*) from correct_op," + getUser1 + " where qss_no = qno and c_op = ans;");
                        rs = ps.executeQuery();
                        while(rs.next()){
                           //getusr = rs.getString("user_id");

                           setResult = rs.getString("count(*)");
                           Screen_R r = new Screen_R();
                           r.showResult(setResult);


                        }

                    }catch (Exception e) {
                        //System.out.println(e);
                        JOptionPane.showMessageDialog(null,e, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
            }
            
                if(setTableName != null)
                {
                    String sql = "create table " + setTableName + " (qno int,ans varchar(100))";
                    stmt.executeUpdate(sql);
                    //ps.setString(1,setTableName);
                }
                
                if(qs != 0)
                {
                ps = con.prepareStatement("delete from " + setUserid + " where qno = " + qs + ";");
                
                j = ps.executeUpdate();
                qs=0;
                    
                }
                
                
                if(setAnswer != null)
                {
                ps = con.prepareStatement("insert into " + setUserid + " (qno, ans) values (?, ?)");
                ps.setInt(1, setQuestion);
                ps.setString(2, setAnswer);
                
                j = ps.executeUpdate();
                }
                
                
                if(str!=null){
                    switch(setScreen)
                    {
                        case "a":
                            ps = con.prepareStatement("insert into admin(user_id,user_name,pass) values (?,?,?)");
                            ps.setString(1, str);
                            ps.setString(2, str2);
                            ps.setString(3, str3);

                            i = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "New User created successfully.", "Congratulation!", JOptionPane.INFORMATION_MESSAGE);
                            break;
                            
                        case "b":
                            ps = con.prepareStatement("insert into teacher(user_id,user_name,pass) values (?,?,?)");
                            ps.setString(1, str);
                            ps.setString(2, str2);
                            ps.setString(3, str3);

                            i = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "New User created successfully.", "Congratulation!", JOptionPane.INFORMATION_MESSAGE);
                            str= null;
                            break;
                            
                        case "c":
                            ps = con.prepareStatement("insert into student(user_id,user_name,pass) values (?,?,?)");
                            ps.setString(1, str);
                            ps.setString(2, str2);
                            ps.setString(3, str3);

                            i = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "New User created successfully.", "Congratulation!", JOptionPane.INFORMATION_MESSAGE);
                            break;
                    }
                }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Please Check" , JOptionPane.ERROR_MESSAGE);
            //System.out.println(e);
        }
        
        
        if(setusr != null){
        switch(setScreen2)
        {
            case "a":
                    
                    try {
                        ps = con.prepareStatement("select * from admin");
                        rs = ps.executeQuery();
                        while(rs.next()){
                           getusr = rs.getString("user_id");
                           getpass = rs.getString("pass");
                           getname = rs.getString("user_name");

                           if(getusr.equals(setusr)){

                               pass = getpass;
                               name = getname;

                               break;
                           }


                        }

                    }catch (Exception e) {
                        //System.out.println(e);
                        JOptionPane.showMessageDialog(null,e, "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    break;
                
                
            case "b":
                    if(setusr != null){
                    try {
                        ps = con.prepareStatement("select * from teacher");
                        rs = ps.executeQuery();
                        while(rs.next()){
                           getusr = rs.getString("user_id");
                           getpass = rs.getString("pass");
                           getname = rs.getString("user_name");

                           if(getusr.equals(setusr)){

                               pass = getpass;
                               name = getname;

                               break;
                           }


                        }

                    }catch (Exception e) {
                        System.out.println(e);
                    }
                    }
                    break;
                
                
                
                case "c":
                    if(setusr != null){
                    try {
                        ps = con.prepareStatement("select * from student");
                        rs = ps.executeQuery();
                        while(rs.next()){
                           getusr = rs.getString("user_id");
                           getpass = rs.getString("pass");
                           getname = rs.getString("user_name");

                           if(getusr.equals(setusr)){

                               pass = getpass;
                               name = getname;

                               break;
                           }
                        }
                    

                    }catch (Exception e) {
                        System.out.println(e);
                    }
                    }
                    break;
        }

}        
    }
    
    
 public ArrayList<Result_Info> getAllRecord(String seeresult) {
        try {
            Connection con = null;
            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oep?user=root&password=");
            
            if (seeresult.equals("All")){
            
            String query = "select uid, s_name, score, result from result;";
            PreparedStatement stmt = con.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("here");
                ArrayList<Result_Info> allcourse = new ArrayList<Result_Info>();
                do {
                    Result_Info ri = new Result_Info();
                    ri.setuid(rs.getString("uid"));
                    ri.sets_name(rs.getString("s_name"));
                    ri.setscore(rs.getInt("score"));
                    ri.setresult(rs.getString("result"));

                    allcourse.add(ri);

                } while (rs.next());

                rs.close();
                stmt.close();
                
                return allcourse;
            }else
                return null;            
            
            }
            
            else{
            String query = "select uid, s_name, score, result from result where result = ?;";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, seeresult);

            ResultSet rs = stmt.executeQuery();
            
            
            
            if (rs.next()) {
                System.out.println("here");
                ArrayList<Result_Info> allcourse = new ArrayList<Result_Info>();
                do {
                    Result_Info ri = new Result_Info();
                    ri.setuid(rs.getString("uid"));
                    ri.sets_name(rs.getString("s_name"));
                    ri.setscore(rs.getInt("score"));
                    ri.setresult(rs.getString("result"));

                    allcourse.add(ri);

                } while (rs.next());

                rs.close();
                stmt.close();
                
                return allcourse;
            }else
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    /*public String getUser(){
        return getusr;        
    }*/
    
    
}
