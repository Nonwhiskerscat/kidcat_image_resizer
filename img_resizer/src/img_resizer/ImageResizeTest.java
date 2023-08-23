package img_resizer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.imageio.ImageIO;

import img_resizer.value_obj.imgPathVO;
import img_resizer.value_obj.imgSizeVO;

	 
public class ImageResizeTest extends fileReader {

    
    public static void main(String[] args) throws IOException  {
    	
        File thumnail_file = new File(imgPathVO.getThumnailPath());
        File preview_file = new File(imgPathVO.getPreviewPath());
        fileCreater(thumnail_file);
        fileCreater(preview_file);
       
        HashMap<String, String> img_map = new HashMap<String, String>();
        List img_path = new ArrayList<>();
        

        try{
        	List<File> list = getImgFileList(imgPathVO.getOriginPath());
        	for(File file : list) {
                img_map.put(file.getName(), getFileExt(file.getPath()));
            }
        	

        	
            for (Entry<String, String> entrySet : img_map.entrySet()) {
                
                String imgOriginalPath= imgPathVO.getOriginPath() + entrySet.getKey();
                String imgThumnailPath= imgPathVO.getThumnailPath() + entrySet.getKey();
                String imgPreviewPath= imgPathVO.getPreviewPath() + entrySet.getKey();
                String imgFormat = entrySet.getValue();
                
                // 원본 이미지 가져오기
//                System.out.println(imgOriginalPath);
                Image image = ImageIO.read(new File(imgOriginalPath));
                                
                Image resizeThumnail = image.getScaledInstance(imgSizeVO.getThumnailWidth(), imgSizeVO.thmint_height(image.getHeight(null),image.getWidth(null)), Image.SCALE_SMOOTH);
                Image resizePreview = image.getScaledInstance(imgSizeVO.getPreviewWidth(), imgSizeVO.preint_height(image.getHeight(null),image.getWidth(null)), Image.SCALE_SMOOTH);

                // 새 이미지  저장하기
                BufferedImage thumnailImage = new BufferedImage(imgSizeVO.getThumnailWidth(), imgSizeVO.thmint_height(image.getHeight(null),image.getWidth(null)), BufferedImage.TYPE_INT_RGB);
                BufferedImage previewImage = new BufferedImage(imgSizeVO.getPreviewWidth(), imgSizeVO.preint_height(image.getHeight(null),image.getWidth(null)), BufferedImage.TYPE_INT_RGB);
                Graphics g_thm = thumnailImage.getGraphics();
                Graphics g_pre = previewImage.getGraphics();
                g_thm.drawImage(resizeThumnail, 0, 0, null);
                g_thm.dispose();
                g_pre.drawImage(resizePreview, 0, 0, null);
                g_pre.dispose();
                ImageIO.write(thumnailImage, imgFormat, new File(imgThumnailPath));
                ImageIO.write(previewImage, imgFormat, new File(imgPreviewPath));
            }
             
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

