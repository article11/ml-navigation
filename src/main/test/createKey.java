/**
 * Created by Administrator on 2016/11/17.
 */
public class createKey {
    public static void main(String[] args) {

//        System.out.println("lhfs_recharge_" + getRoundNum(32));





    }


    public static String getRoundNum(int len) {
        //字符源，可以根据需要删减
        String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz";
//        String generateSource = "0123456789";
        String rtnStr = "";
        for (int i = 0; i < len; i++) {
            //循环随机获得当次字符，并移走选出的字符
            String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr += nowStr;
            generateSource = generateSource.replaceAll(nowStr, "");
        }
        return rtnStr;
    }

}
