package com.hovah_inc.beamraisingoperationchecklist.record;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface QuestionsSpreadsheetWebService {

    @POST("1FAIpQLSeaEyKsflHPiBIPY36eVhkU2H-sH3AnD2CBGtsErrDBIfIfEg/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.2097001313") String name,
            @Field("entry.1932005367") String section,
            @Field("entry.390598756") String potNo,
            @Field("entry.703202375") String startTime,
            @Field("entry.1450262309") String endTime,
            @Field("entry.547154915") String remarks
    );

}