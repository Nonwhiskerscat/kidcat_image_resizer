package img_resizer.value_obj;

public class imgSizeVO {
    private static float thumnail_width = 200f;
    private static float preview_width = 600f;
    
    private static int thmint_width = Math.round(thumnail_width);
    private static int preint_width = Math.round(preview_width);
    
    public static int getThumnailWidth        () {return thmint_width      ;} 
	public static int getPreviewWidth      () {return preint_width    ;} 
	
	public static int thmint_height(float cat, float rabbit) {
		if(thumnail_width==0) return 0;
		else {
			return (int)(cat*(thmint_width/rabbit));
		}
	}
	
	public static int preint_height(float cat, float rabbit) {
		if(preint_width==0) return 0;
		else {
			return (int)(cat*(preint_width/rabbit));
		}
	}

}
