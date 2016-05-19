package com.puc.dad.olimpadas2016;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Arielle Sathler on 19/05/2016.
 */
public class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Modalidade>> {

    ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(Home.this, "Aguarde",
                "Baixando JSON, Por Favor Aguarde...");
    }

    //Acessa o serviço do JSON e retorna a lista de pessoas
    @Override
    protected List<Modalidade> doInBackground(String... params) {

            try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            URL url = new URL(params[0]);
            URLConnection urlConnection = url.openConnection();

            if (entity != null) {
                InputStream instream = new BufferedInputStream(urlConnection.getInputStream());
                String json = Home.getStringFromInputStream(instream);
                instream.close();
                List<Pessoa> pessoas = getPessoas(json);
                return pessoas;
            }
        } catch (Exception e) {
            Log.e("Erro", "Falha ao acessar Web service", e);
        }
        return null;
    }
}
