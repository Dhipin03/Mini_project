package com.example.shareplate_final;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Background extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... voids) {
        String result="";
        String user = voids[0];
        String pass = voids[1];
        String connect = "https://localhost:8080/login.php";
        try {
            URL url = new URL(connect);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops =http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));

            String data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&&"
                    +URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line="";
            while ((line= reader.readLine())!=null){
                result +=line;

            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;

        } catch (MalformedURLException e){
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }
        return result;
    }
}
