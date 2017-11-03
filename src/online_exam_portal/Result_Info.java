/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package online_exam_portal;

/**
 *
 * @author Nitish Singh
 */
public class Result_Info {
    
    private String uid, s_name, result;
    private int score;
    
    public String getuid() {
        
        return uid;
    }

    public void setuid(String uid) {
        
        this.uid = uid;
    }
    
    
    public String gets_name() {
        
        return s_name;
    }

    public void sets_name(String s_name) {
        
        this.s_name = s_name;
    }
    
    
    public int getscore() {
        
        return score;
    }

    public void setscore(int score) {
        
        this.score = score;
    }
    
    
    public String getresult() {
        
        return result;
    }

    public void setresult(String result) {
        
        this.result = result;
    }
    
}
