package com.aristideniyungeko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * TODO: documentation.
 */
public class Tester {
   public static void main(String[] args) throws IOException {
      System.out.println("START");

      OutputStreamWriter writer = null;
      BufferedReader reader = null;

      try {

         URL url = new URL("http://localhost:5000/");
         // TODO use apache HttpClient here
         URLConnection urlConnection = url.openConnection();
         urlConnection.setDoOutput(true);
         urlConnection.setRequestProperty("Content-Type",
                                          "text/xml;charset=" +
                                          "UTF-8");

         // putting XML data
         String data = "<SSFSvc><SvcHdr><SvcInfo><svcNm>featureChange</svcNm>"
                       + "<subSvcNm>actLvlFeatLookUp</subSvcNm></SvcInfo><ClientInfo><clientId>FAM-LL"
                       + "</clientId><userId>default</userId><pwd>default</pwd></ClientInfo></SvcHdr>"
                       + "<SvcBdy><SvcReq><mdn>5552221111</mdn></SvcReq></SvcBdy></SSFSvc>";

         writer = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
         writer.write(data);
         writer.flush();

         // getting back XML data
         reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
         String line;
         StringBuilder sb = new StringBuilder();
         while ((line = reader.readLine()) != null) {
            sb.append(line);
         }

         System.out.println("The XML Response is " + sb.toString());

      } finally {
         if (writer != null) {
            writer.close();
         }

         if (reader != null) {
            reader.close();
         }
      }

   }
}
