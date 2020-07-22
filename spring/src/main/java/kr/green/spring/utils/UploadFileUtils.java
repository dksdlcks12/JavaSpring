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
		//C:\Users=Administrator\Desktop\������\2020/07/22\'UUID'test.jpg
		//new file�� ���� �̸��� ���� �� ������ �����ȴ�
		File target = new File(uploadPath + savedPath, savedName);
		//���� ����
		FileCopyUtils.copy(fileData, target);
		//��α����ڸ� '/'�� �����Ѵ�.
		String uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		return uploadFileName;
	}
	
	private static String calcPath(String uploadPath) {
		//���ε� ��¥ ������ ����		
		Calendar cal = Calendar.getInstance();
		//�⵵ ��θ� ���� : ex) os�� win��� : \2020
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		//�⵵,�� ��θ� ���� : \2020\07
		String monthPath = yearPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		//�⵵,��,�� ��θ� ���� : \2020\07\22
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
