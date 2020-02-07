package com.example.proiectmobilebanking.json;

import com.example.proiectmobilebanking.TranzactionJson;
import com.example.proiectmobilebanking.database.models.Tranzaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static HttpResponse parseJson(String json){
        if(json==null){
            return null;
        }
        try {
            JSONObject jsonObject=new JSONObject(json);
            List<TranzactionJson> transactionBrd=getListFromJSON(jsonObject.getJSONArray("tranzactionBRD"));
            List<TranzactionJson> transactionBt=getListFromJSON(jsonObject.getJSONArray("transactionBT"));
            List<TranzactionJson> transactionBcr=getListFromJSON(jsonObject.getJSONArray("transactionBCR"));
            return new HttpResponse(transactionBrd,transactionBt,transactionBcr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<TranzactionJson> getListFromJSON(JSONArray array) throws JSONException {
        if(array==null){
            return null;
        }
        List<TranzactionJson> transactions=new ArrayList<>();
        for(int i=0;i<array.length();i++){
            TranzactionJson tranzaction=getItemFromJSON(array.getJSONObject(i));
            if(tranzaction!=null){
                transactions.add(tranzaction);
            }
        }
        return transactions;
    }
    private static TranzactionJson getItemFromJSON(JSONObject jsonObject) throws JSONException {
        if(jsonObject==null){
            return null;
        }
        String beneficiary=jsonObject.getString("beneficiaryName");
        String account=jsonObject.getString("accountNumber");
        String amountS=jsonObject.getString("amount");
        Integer amount=Integer.parseInt(amountS);
        String status=jsonObject.getString("status");
        InfoExtraTransaction info=getInfoExtraTransaction(jsonObject.getJSONObject("infoExtraTransaction"));
        return new TranzactionJson(beneficiary,account,status,amount,info);
    }

    private static InfoExtraTransaction getInfoExtraTransaction(JSONObject jsonObject) throws JSONException {
        if(jsonObject==null){
            return null;
        }
        String senderAddress=jsonObject.getString("adressSender");
        String telephone=jsonObject.getString("telephone");
        return new InfoExtraTransaction(senderAddress,telephone);
    }

}
