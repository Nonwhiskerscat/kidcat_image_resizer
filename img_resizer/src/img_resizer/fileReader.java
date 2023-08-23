package img_resizer;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;


public class fileReader {
    public static List<File> getImgFileList(String path){        
        return getImgFileList(new File(path));
    }    

    /**
     * 해당 경로의 이미지 파일 목록 반환 
     */
    public static List<File> getImgFileList(File file){        
            
        List<File> resultList = new ArrayList<File>(); //이미지 파일을 저장할 리스트 생성
        
         //지정한 이미지폴더가 존재 할지 않을경우 빈 리스트 반환.
        if(!file.exists()) return resultList;
        
        File[] list = file.listFiles(new FileFilter() { //원하는 파일만 가져오기 위해 FileFilter정의
            
            String strImgExt = "jpg|jpeg|png|gif|bmp|webp"; //허용할 이미지타입
            
            @Override
            public boolean accept(File pathname) {                            
                
                //System.out.println(pathname);
                boolean chkResult = false;
                if(pathname.isFile()) {
                    String ext = pathname.getName().substring(pathname.getName().lastIndexOf(".")+1);
                    //System.out.println("확장자:"+ext);
                    chkResult = strImgExt.contains(ext.toLowerCase());        
                    //System.out.println(chkResult +" "+ext.toLowerCase());
                } else {
                    chkResult = true;
                }
                return chkResult;
            }
        });        
        
        for(File f : list) {            
            if(f.isDirectory()) {
                //폴더이면 이미지목록을 가져오는 현재메서드를 재귀호출
                resultList.addAll(getImgFileList(f));                 
            }else {            
                //폴더가 아니고 파일이면 리스트(resultList)에 추가
                resultList.add(f);
            }
        }            
        return resultList; 
    }
    
    //확장자를 제외한 파일 이름 만 출력
    public static String getFileNameNoExt(String filepath){        
        String fileName = filepath.substring(0,  filepath.lastIndexOf("."));
        return fileName;
    }    
    
    //파일 확장자만 출력
    public static String getFileExt(String filepath){
        String ext = filepath.substring(filepath.lastIndexOf(".")+1);
        return ext;
    }
    
    //파일패스에서 이미지 상대경로 출력
    //절대경로에서 이미지폴더(images)를 중심으로 상대경로를 반환.
    //패스 : 절대결로/images/1-A/1-A_0.jpg 
    //    => 1-A/1-A_0.jpg
    public static String getImgSrc(File target){
        String url = target.getPath().substring(target.getPath().lastIndexOf("images"));
        return url;
    }
    
    //이미지를 포함하고있는 폴더의 이름 얻기. ( 개인 용도로 작성한 메서드 이므로 무시해도 무방 )
    public static String getImgDirName(File target){
        String url = getImgSrc(target);
        
        //System.out.println(url);
        //System.out.println(url.indexOf("\\")+1+"/"+url.lastIndexOf("\\"));
        
        int comp = url.lastIndexOf("\\") - (url.indexOf("\\")+1) ;
        //System.out.println(comp);
        String dirName ="";
        if(comp<0) {
            dirName ="이미지";
        }else  {
            dirName = url.substring(url.indexOf("\\")+1,url.lastIndexOf("\\"));    
        }        
        System.out.println("폴더명:"+dirName);
        
        return dirName;
    }
    
    // 해당 경로에 파일이 누락되어 있을 경우, 자동으로 파일 생성
    public static void fileCreater(File target) {
    	if(!target.exists()) {
    		try {
        		target.mkdir();
    		}
    		catch(Exception e) {
    			e.getStackTrace();
    		}
    	}
    	
    	else {
    		// 이미 폴더가 생성된 경우
    	}
    }
    
}

