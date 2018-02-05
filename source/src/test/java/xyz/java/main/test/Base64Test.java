package xyz.java.main.test;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import xyz.frame.json.FrameJsonUtils;
import xyz.frame.utils.Base64Tools;

public class Base64Test {

    public static void main(String[] args) {
        String json = null;
        try {
            json = URLDecoder.decode(Base64Tools.decode(
                    "JTdCJTIyY2FzZUlkcyUyMjolMjIzOTAzODU5LDM5MDM3NjcsMTI3MTc3NSUyMiwlMjJjb2xsZWN0b3JzJTIyOiUyMjg2LDEyNCwxNjAsMTgzLDEyNzAsMzMzMyUyMiU3RA=="),
                    "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CaseMannulAssinBo caseMannulAssinBo = FrameJsonUtils.fromJson(json, CaseMannulAssinBo.class);
        System.out.println(caseMannulAssinBo);
    }

}

class CaseMannulAssinBo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String caseIds;
    private String collectors;

    public String getCaseIds() {
        return caseIds;
    }

    public void setCaseIds(String caseIds) {
        this.caseIds = caseIds;
    }

    public String getCollectors() {
        return collectors;
    }

    public void setCollectors(String collectors) {
        this.collectors = collectors;
    }
}
