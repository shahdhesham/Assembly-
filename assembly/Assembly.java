/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assembly;

import java.io.*;
import static java.sql.JDBCType.NULL;
import java.util.*;
import static javafx.application.Platform.exit;
/**
 *
 * @author s
 */
public class Assembly {

   static HashMap  <String,String> Assemtohedxa =new HashMap<String,String>();
   static HashMap  <String,String> inAssemtohedxa =new HashMap<String,String>();
    public static void main(String[] args) throws FileNotFoundException {
        File code=new File("F:\\input.txt");
        File Assembly=new File("C:\\Users\\Dell\\Desktop\\hexadecimal.txt");
        File inAssembly=new File("C:\\Users\\Dell\\Desktop\\Indirect-hexadecimal.txt");
        Scanner in =new Scanner(code);
        Scanner in1=new Scanner(Assembly);
        Scanner in2 =new Scanner (inAssembly);
        FileOutputStream out=new FileOutputStream(code);
        
        boolean b=true;
        ArrayList<String> ar;
        ar = new ArrayList<>();
        while(in.hasNextLine()){
        String x=in.nextLine();
        ar.add(x);
        
        }
        
        int len=ar.size();
        int lc=0;//counter
        String ar1[]=new String[len];
        
        String x1=new String();
        String[][] address=new String [len][2];//2d arrays
         String[][] hexa=new String [len][2];
        //first pass
        int k=0;int l=0;
        int k1=0;int l1=0;
        for(String s:ar ){
        Arrays.equals(ar1, Tokens(s,',',b));
        if(b==false){//not label
            String ch=new String();
           
                ch=s.substring(0, 2);
        if(ch.equals("ORG")){
        x1=s.substring(4);
                lc=Integer.parseInt(x1);
    
        }
        else if(ch.equals("End")){
             break;
        }
        else{
            lc++;}
        
        }
        else{//label
          
       
           
        address[k][l]=String.valueOf(lc);//add to array
           address[k][l+1]=ar1[0];
        lc++;
        
        
        
        
        }
        
        k++;
        l=0;
        }
        while(in1.hasNextLine()){//read from file to hashmap
        String s=new String();
          
            s=in1.nextLine();
            
            
        
             Arrays.equals(ar1, Tokens(s,' ',b));
             Assemtohedxa.put(ar1[0], ar1[1]);
        
        
        }
        
        
         while(in2.hasNextLine()){
        String s=new String();
          
            s=in2.nextLine();
           
            
        
             Arrays.equals(ar1, Tokens(s,' ',b));
             inAssemtohedxa.put(ar1[0], ar1[1]);
        
        
        }
         
          
      //  second pass
        lc=0;
        
        for(String ch:ar){
            
        String s=new String();//read first 3 letters
        for(int i=0;i<3;i++){
        s+=ch.charAt(i);
        }
        if(s.equals("ORG")){
        for(int i=4;i<5;i++){
        x1+=ch.charAt(i);//take number after origin and start counter
        
        }
        
        lc=Integer.parseInt(x1);
        
        
        }
        else if(s.equals("End")){
        
        exit();
        
        }
        else if(s.equals("DEC")){
        
         hexa[k1][l1]=String.valueOf(lc);
         String b8=new String();
         String b5=new String();
         for(int i=4;i<ch.length();i++){//to get the decimal
         b8+=ch.charAt(i);
         
         }
        
        b5=Assemtohedxa.get(b8);
         
        hexa[k1][l1+1]=b5;
        lc++;
        k1++;
        l1=0;
        }
        else if(s.equals("HEX")){
             String b3=new String();
        
        hexa[k1][l1]=String.valueOf(lc);
         for(int i=4;i<ch.length();i++){
         b3+=ch.charAt(i);
         
         }
          hexa[k1][l1+1]=b3;
        lc++;
        k1++;
        l1=0;
        
        }  
        else if(!s.equals("HEX")&&!s.equals("DEC")&&!s.equals("End")&&!s.equals("ORG")){
             hexa[k1][l1]=String.valueOf(lc);
         String b9=new String();
         String b5=new String();
         for(int i=4;i<ch.length();i++){
         b9+=ch.charAt(i);
         
         }
         
        b5=Assemtohedxa.get(b9);
         
         if(b5.startsWith("7")||b5.startsWith("F")){
        hexa[k1][l1+1]=b5;
        lc++;
        k1++;
        l1=0;}
         else{
         boolean v=true;
          Arrays.equals(ar1, Tokens(ch,',',v));
        if(v==false){
             hexa[k1][l1]=String.valueOf(lc);
            if(ch.endsWith("I")){
        
        String b6=new String();
        b6=ch.substring(0, 2);
        b6=inAssemtohedxa.get(b);
        String b7=new String();
       if(s.length()>2){
        
        b7=ch.substring(4, s.length());
       }
         for(int i=0;i<len;i++){
             if(b7.equals(address[len][0])){}
                 b6+=address[len][0];
             
             }
        hexa[k1][l1+1]=b6;
        lc++;
        k1++;
        l1=0;
        
        }
            else{//not label
            
             String b6=new String();
        b6=ch.substring(0, 2);
        b6=Assemtohedxa.get(b);
        String b7=new String();
       if(ch.length()>3){
        b7=ch.substring(4);
       
       }
            
          for(int i=0;i<len;i++){
             if(b7.equals(address[i][0])){}
                 b6+=address[i][0];
             
             }
   
            hexa[k1][l1+1]=b6;
        lc++;
        k1++;
        l1=0;
            
            } 
        
        }
        else{
        if(ch.substring(3, 5).equals("DEC")){
        String b8=new String();
        b8=ch.substring(7);
        int x9=Integer.parseInt(b8);
        b8=Integer.toHexString(x9);
         hexa[k1][l1]=String.valueOf(lc);
         hexa[k1][l1+1]=b8;
          lc++;
        k1++;
        l1=0;
        }
        else if(ch.substring(3, 5).equals("hex")){
         String b8=new String();
        b8=ch.substring(7);
        
         hexa[k1][l1]=String.valueOf(lc);
         hexa[k1][l1+1]=b8;
          lc++;
        k1++;
        l1=0;
          lc++;
        k1++;
        l1=0;
        
        }
        }//label
         
         }//mri instruction
             
        
        }//if not hexa or end or org
        
        }//arraylist for loop
       
      
      
              
       in.close();
       in1.close();
       in2.close();
    }
    public static  String[] Tokens(String line,char x,boolean b){
        String Result[]=new String[2];
        String word="";
        int ctr=0;
       b=false;
        for(int i=0;i<line.length();i++){
        if(line.charAt(i)!=x)
            word+=line.charAt(i);
        else{
        Result[ctr]=word;
        ctr++;
        word=new String();
        b=true;
        }
        
        }
        return Result;
}
    
}
