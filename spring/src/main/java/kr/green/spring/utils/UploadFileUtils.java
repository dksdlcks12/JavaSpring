package kr.green.spring.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	public static String uploadFile(String uploadPath, String originalName, byte[] 	
			fileData)throws Exception{
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() +"_" + originalName;
		String savedPath = calcPath(uploadPath);
		//C:\Users=Administrator\Desktop\새폴더\2020/07/22\'UUID'test.jpg
		//new file을 통해 이름을 가진 빈 파일이 생성된다
		File target = new File(uploadPath + savedPath, savedName);
		//파일 복사
		FileCopyUtils.copy(fileData, target);
		//경로구분자를 '/'로 변경한다.
		String uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		return uploadFileName;
	}
	
	private static String calcPath(String uploadPath) {
		//업로드 날짜 정보를 생성		
		Calendar cal = Calendar.getInstance();
		//년도 경로를 생성 : ex) os가 win경우 : \2020
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		//년도,월 경로를 생성 : \2020\07
		String monthPath = yearPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		//년도,월,일 경로를 생성 : \2020\07\22
		String datePath = monthPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath, yearPath, monthPath, datePath);
		return datePath;
 
	}
	private static void makeDir(String uploadPath, String... paths) {
		if(new File(uploadPath+paths[paths.length-1]).exists())
			return;
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			if( !dirPath.exists())
				dirPath.mkdir();
		}
	}
	private static String makeIcon(String uploadPath, String path, String fileName)
        	throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}
