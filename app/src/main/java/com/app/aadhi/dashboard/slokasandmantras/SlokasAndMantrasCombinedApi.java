package com.app.aadhi.dashboard.slokasandmantras;

import com.app.aadhi.network.response.SlokasMantrasListApi;

public class SlokasAndMantrasCombinedApi {

    public SlokasMantrasListApi slokas;
    public SlokasMantrasListApi mantras;

    public SlokasAndMantrasCombinedApi(SlokasMantrasListApi slokas, SlokasMantrasListApi mantras) {
        this.slokas = slokas;
        this.mantras = mantras;
    }
}
