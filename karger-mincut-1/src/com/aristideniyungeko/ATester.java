package com.aristideniyungeko;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TODO: documentation.
 */
public class ATester {
   public static void main(String[] args) throws IOException {
      System.out.println("START");
      BufferedReader reader;

      // putting XML data
      final String data = "<SSFSvc><SvcHdr><SvcInfo><svcNm>featureChange</svcNm>"
                    + "<subSvcNm>actLvlFeatLookUp</subSvcNm></SvcInfo><ClientInfo><clientId>FAM-LL"
                    + "</clientId><userId>default</userId><pwd>default</pwd></ClientInfo></SvcHdr>"
                    + "<SvcBdy><SvcReq><mdn>5552221111</mdn></SvcReq></SvcBdy></SSFSvc>";

      CloseableHttpClient httpClient = HttpClients.createDefault();
      HttpPost httpPost = new HttpPost("http://localhost:5000/");
      httpPost.setEntity(new StringEntity(data, ContentType.create("text/xml", "UTF-8")));
      final CloseableHttpResponse response = httpClient.execute(httpPost);

      try {
         HttpEntity entity = response.getEntity();
         // getting back XML data
         reader = new BufferedReader(new InputStreamReader(entity.getContent()));
         String line;
         StringBuilder sb = new StringBuilder();
         while ((line = reader.readLine()) != null) {
            sb.append(line);
         }

         System.out.println("The XML Response issoo " + sb.toString());

         EntityUtils.consume(entity);
      } finally {
         response.close();
      }

   }
}
