package initech.common.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppStringUtil {
	public static String getUUID(){
        return String.valueOf(UUID.randomUUID());
    }

    public static String removeFilePathBlackList(String str){
        if(StringUtils.trimToEmpty(str).length() == 0) {
            return "";
        }

        str = str.replaceAll("\\.\\./", "");// ../
        str = str.replaceAll("\\.\\.\\\\", "");// ..\

        return str;
    }

    public static String removeXSS(String str) {
        if(StringUtils.trimToEmpty(str).length() == 0) {
            return "";
        }

        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&#34;");
        str = str.replaceAll("\'", "&#39;");
		/*str = str.replaceAll(".", "&#46;");
		str = str.replaceAll("%2E", "&#46;");
		str = str.replaceAll("%2F", "&#47;");*/

        return str;
    }

    public static String htmlXSS(String str) {
        String newLine = System.getProperty("line.separator");

        if(StringUtils.trimToEmpty(str).length() == 0) {
            return "";
        }

        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&#34;");
        str = str.replaceAll("\'", "&#39;");

        str = str.replaceAll(newLine, "<br/>");

        return str;
    }

    public static String getKwdForAddrApi(String kwd){
        String rs = kwd;

        if(rs == null){
            return "";
        }

        String[] banKwd = {"%", "=", ">", "<"
                , "OR", "SELECT", "INSERT", "DELETE"
                , "UPDATE", "CREATE", "DROP", "EXEC"
                , "UNION",  "FETCH", "DECLARE", "TRUNCATE"};

        for(String kwd_ : banKwd){
            rs = rs.replaceAll("(?i)"+kwd_, "");
        }

        return rs;
    }
    
    /**
     * szOriginal에서 szOld를 모두 szNew로 바꾼다.
     *
     * @param szOriginal 원래의 문자열.
     * @param szOld 바꾸고자하는 문자열.
     * @param szNew 새로운 문자열.
     * @return szOriginal 문자열에서 모든 szOld 문자열을 szNew 문자열로 대치한 문자열을 넘긴다.
     */
    public static String replace(String szOriginal, String szOld, String szNew) {
      return replace(szOriginal, szOld, szNew, 0);
    }

    /**
     * sszOriginal에서 처음부터 nReplaceCount개만큼 szOld를 szNew로 바꾼다.
     * 만약 nReplaceCount가 0이면 szOld를 모두 szNew로 바꾼다.
     *
     * @param szOriginal 원래의 문자열.
     * @param szOld 바꾸고자하는 문자열.
     * @param szNew 새로운 문자열.
     * @param nReplaceCount szOriginal의 처음부터 몇개까지의 szOld를 szNew로 바꿀지를 결정한다.
     *                      1이면 처음 나타나는 szOld 문자열만을 szNew로 바꾼다.
     *                      0이면 모든 szOld 문자열을 szNew로 바꾼다.
     * @return szOriginal 문자열에서 szOld 문자열을 찾아 nReplaceCount 갯수만큼 szNew 문자열로 대치한 문자열을 넘긴다.
     */
    public static String replace(String szOriginal, String szOld, String szNew,
                                 int nReplaceCount) {
      if (szOriginal == null || szOld == null || szNew == null) {
        throw new IllegalArgumentException();
      }

      StringBuffer sbResult = new StringBuffer();
      int nFromIndex = 0, nToIndex = 0;
      int nOldLength = szOld.length();
      int i = 0;

      while ( (nToIndex = szOriginal.indexOf(szOld, nFromIndex)) >= 0) {
        sbResult.append(szOriginal.substring(nFromIndex, nToIndex)).append(szNew);
        nFromIndex = nToIndex + nOldLength;

        if (nReplaceCount != 0 && ++i == nReplaceCount) {
          return sbResult.append(szOriginal.substring(nFromIndex)).toString();
        }
      }

      return sbResult.append(szOriginal.substring(nFromIndex)).toString();
    }

}
