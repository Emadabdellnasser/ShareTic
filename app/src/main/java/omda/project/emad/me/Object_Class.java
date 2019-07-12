package omda.project.emad.me;

/**
 * Created by EMAD on 3/6/2018.
 */

public class Object_Class {

    private String fromch;
    private String priceCh;
    private String toch;
    private String datech;
    private String timech;
    private String degeech;
    private String phonech;
    private String passch;


    public Object_Class(String fromch, String toch,String priceCh,String datech,String timech,String degeech,String phonech,String passch) {
        this.fromch = fromch;
        this.priceCh = priceCh;
        this.toch = toch;
        this.datech = datech;
        this.timech = timech;
        this.degeech = degeech;
        this.phonech = phonech;
        this.passch = passch;


    }
    public Object_Class() {
    }

    public String getfromCh() {
        return fromch;
    }
    public void setfromCh(String fromch) {
        this.fromch = fromch;
    }

    public String gettoCh() {
        return toch;
    }
    public void settoCh(String toch) {
        this.toch = toch;
    }

    public String gettimeCh() {
        return timech;
    }
    public void settimeCh(String timech) {
        this.timech = timech;
    }

    public void setdateCh(String datech) {
        this.datech = datech;
    }
    public String getdateCh() {
        return datech;
    }

    public void setdegeeCh(String degeech) {
        this.degeech = degeech;
    }
    public String getdegeeCh() {
        return degeech;
    }

    public String getPriceCh() {
        return priceCh;
    }
    public void setPriceCh(String priceCh) {
        this.priceCh = priceCh;
    }

    public String getphoneCh() {
        return phonech;
    }
    public void setphoneCh(String phonech) {
        this.phonech = phonech;
    }
    public String getPassCh() {
        return passch;
    }
    public void setPass(String passch) {
        this.passch = passch;
    }


}
