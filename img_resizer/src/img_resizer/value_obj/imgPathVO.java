package img_resizer.value_obj;

public class imgPathVO {
    private static String origin_path = "C:/Users/김서용/Pictures/test_img/";
    private static String thumnail_path = origin_path + "thumnail/";
    private static String preview_path= origin_path + "preview/";
    
    public static String getOriginPath        () {return origin_path      ;} 
	public static String getThumnailPath      () {return thumnail_path    ;} 
	public static String getPreviewPath        () {return preview_path      ;}  
}
