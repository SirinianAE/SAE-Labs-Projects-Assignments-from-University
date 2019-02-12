package sae.animecalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.edittext);
        textView = (TextView)findViewById(R.id.textview);
        textView.setVisibility(View.GONE);
    }

    public void insertNewShow(View view){
        String Message = "";
        String temp;
        try {
            FileInputStream fileInputStream = openFileInput("test_file0");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while((temp=bufferedReader.readLine())!=null){
                Message+= temp + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message += editText.getText().toString();
        String file_name = "test_file0";
        try{
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "New show inserted", Toast.LENGTH_LONG).show();
            editText.setText("");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteWholeShow(View view){
        String Message = "";
        String Message2 = editText.getText().toString();
        String temp;

        if(Message2.contains(":")){
            try {
                FileInputStream fileInputStream = openFileInput("test_file0");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                while((temp=bufferedReader.readLine())!=null){
                    if (temp.equals(Message2)){
                        continue;
                    }
                    Message+= temp + "\n";
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                FileInputStream fileInputStream = openFileInput("test_file0");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                while((temp=bufferedReader.readLine())!=null){
                    String tempMessageHalfSplited[] = temp.split(":");
                    if (tempMessageHalfSplited[0].equals(Message2)){
                        continue;
                    }
                    Message+= temp + "\n";
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String file_name = "test_file0";
        try{
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Show deleted", Toast.LENGTH_LONG).show();
            editText.setText("");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertNewEpisodes(View view){
        String Message = editText.getText().toString();
        String messageHalfSplited[] = Message.split(":");
        String messageEpisodes[] = messageHalfSplited[1].trim().split(", ");

        String finalTextToBeWriten = "";

        String temp;
        try {
            FileInputStream fileInputStream = openFileInput("test_file0");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while((temp=bufferedReader.readLine())!=null){
                String tempMessageHalfSplited[] = temp.split(":");
                String tempMessageEpisodes[] = tempMessageHalfSplited[1].trim().split(", ");
                if(tempMessageHalfSplited[0].toString().equals(messageHalfSplited[0])){
                    ArrayList<String> tempList = new ArrayList<String>();
                    finalTextToBeWriten += messageHalfSplited[0] + ":";
                    for(int i=0; i<tempMessageEpisodes.length; i++){
                        tempList.add(tempMessageEpisodes[i]);
                    }

                    for(int i=0; i<messageEpisodes.length; i++){
                        if(!tempList.contains(messageEpisodes[i])){
                            tempList.add(messageEpisodes[i]);
                        }
                    }

                    ArrayList<Integer> tempList2 = getIntegerArray(tempList);
                    Collections.sort(tempList2);

                    for(int i=0; i<tempList2.size(); i++){
                        finalTextToBeWriten += tempList2.get(i) + "";
                        if(i+1 != tempList2.size()){
                            finalTextToBeWriten += ", ";
                        }
                    }

                    finalTextToBeWriten += "\n";
                }else{
                    finalTextToBeWriten += temp + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String file_name = "test_file0";
        try{
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(finalTextToBeWriten.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "New episodes inserted", Toast.LENGTH_LONG).show();
            editText.setText("");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteEpisodes(View view){
        String Message = editText.getText().toString();
        String messageHalfSplited[] = Message.split(":");
        String messageEpisodes[] = messageHalfSplited[1].trim().split(", ");

        String finalTextToBeWriten = "";

        String temp;
        try {
            FileInputStream fileInputStream = openFileInput("test_file0");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while((temp=bufferedReader.readLine())!=null){
                String tempMessageHalfSplited[] = temp.split(":");
                String tempMessageEpisodes[] = tempMessageHalfSplited[1].trim().split(", ");
                if(tempMessageHalfSplited[0].toString().equals(messageHalfSplited[0])){
                    ArrayList<String> tempList = new ArrayList<String>();
                    finalTextToBeWriten += messageHalfSplited[0] + ":";
                    for(int i=0; i<tempMessageEpisodes.length; i++){
                        tempList.add(tempMessageEpisodes[i]);
                    }

                    for(int i=0; i<messageEpisodes.length; i++){
                        if(tempList.contains(messageEpisodes[i])){
                            tempList.remove(tempList.indexOf(messageEpisodes[i]));
                        }
                    }

                    ArrayList<Integer> tempList2 = getIntegerArray(tempList);
                    Collections.sort(tempList2);

                    for(int i=0; i<tempList2.size(); i++){
                        finalTextToBeWriten += tempList2.get(i) + "";
                        if(i+1 != tempList2.size()){
                            finalTextToBeWriten += ", ";
                        }
                    }

                    finalTextToBeWriten += "\n";
                }else{
                    finalTextToBeWriten += temp + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String file_name = "test_file0";
        try{
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(finalTextToBeWriten.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Episodes deleted", Toast.LENGTH_LONG).show();
            editText.setText("");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh(View view){
        try {
            String message;
            FileInputStream fileInputStream = openFileInput("test_file0");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while((message=bufferedReader.readLine())!=null){
                stringBuffer.append(message + "\n");
            }
            textView.setText(stringBuffer.toString());
            textView.setVisibility(View.VISIBLE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String stringValue : stringArray) {
            try {
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        return result;
    }
}
