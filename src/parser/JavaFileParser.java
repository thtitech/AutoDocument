package parser;

import java.io.*;
import java.util.*;

import entity.ClassInfo;

/**
 * 
 * @author hayashi
 * import, package, class定義文は1行に書かれていること
 * コンストラクタ，メソッド定義文は1行に書かれていること
 * Fieldの宣言がコンストラクタ，メソッドより前にあること
 * コメントに書かれてるやつだけ拾う
 */

public class JavaFileParser {
	
	private File targetFile;
	private ClassInfo info;
	private boolean isComment;
	private boolean isFiled;
	private boolean isInClass;
	private List<String> commentList;
	
	public JavaFileParser(File f){
		this.targetFile = f;
		this.isComment = false;
		this.isFiled = false;
		this.isInClass = true;
		commentList = new ArrayList<>();
	}

	public void parse(){
		try(BufferedReader br = new BufferedReader(new FileReader(targetFile))) {
			String line;
			while((line = br.readLine()) != null){
				parseLine(line, br);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param str
	 */
	private void parseLine(String str, BufferedReader br){
		String line = str.trim().replace("\t", "");
		line = line.replaceAll(" {2,}", "");
		if ("".equals(line)) return;

		// start or end comment
		if("/**".equals(line.substring(0, 3))){
			isComment = true;
		}else if ("*/".equals(line.substring(line.length() - 2))){
			isComment = false;
		}else if ("/*".equals(line.substring(0, 2)) || "//".equals(line.substring(0, 2))){
			// normal Java comment
			return;
		}
		
		//javadoc comment
		if(isComment){
			parseComment(line, br);
			isComment = false;
			return;
		}
		
		//package sentence
		if(line.length() > 7 && "package".equals(line.substring(0, 7))){
			String pack = line.split(" ")[1];
			info.packageName = pack;
			return;
		}
		
		//import sentence
		if(line.length() > 6 && "import".equals(line.substring(0, 6))){
			String tmp = line.split(" ")[1];
			String[] arr = tmp.split(".");
			String lib = String.join(".", Arrays.copyOfRange(arr, 0, arr.length-1));
			lib += ".*";
			info.importList.add(lib);
			return;
		}

		parseDeclareLine(line, br);
		
	}
	
	private void parseComment(String line, BufferedReader br){
		//parse 1 comment block
		String str = line.replaceFirst("*", "");
		str = str.trim();
		
	}

	private void parseDeclareLine(String line, BufferedReader br){
		//parse 1 sentence of delare of Class or Method or Field
		
	}
	
}
