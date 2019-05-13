package parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args){
		String path = "";
		List<File> javaFileList = null;
		try {
			javaFileList = findAllJavaFile(path);
		} catch (IOException e) {
			e.printStackTrace();
			javaFileList = new ArrayList<>();
		}
		
		
	}

	public static List<File> findAllJavaFile(String absolutePath) throws IOException {
	    return Files.walk(Paths.get(absolutePath))
	        .map(path -> path.toFile())
	        .filter(file -> file.isFile())
	        .filter(file -> isJava(file))
	        .collect(Collectors.toList());
	}
	
	private static boolean isJava(File f){
		String name = f.getName();
		String[] arr = name.split(".");
		int len = arr.length;
		if (len == 0){
			return false;
		}else if(arr[len-1].equals("java")){
			return true;
		}else{
			return false;
		}
	}
	
}
