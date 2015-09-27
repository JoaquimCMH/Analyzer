package br.com.ifpb.AppWS.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {  
	static String acentuado = "���������������������������������������������������";  
	static String semAcento = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";  
	static char[] tabela;  
	public static final String SEPARATOR=".";

	static {  
		tabela = new char[256];  
		for (int i = 0; i < tabela.length; ++i) {  
			tabela [i] = (char) i;  
		}  
		for (int i = 0; i < acentuado.length(); ++i) {  
			tabela [acentuado.charAt(i)] = semAcento.charAt(i);  
		}  
	}  

	public static String removerNaoLetras(String s){
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < s.length(); ++i) {  
			char ch = s.charAt (i);  
			if (Character.isLetter(ch) || Character.isWhitespace(ch)) {   
				sb.append (ch);  
			}
		}  
		return sb.toString();
	}
	
	public static StringBuffer onlyText(StringBuffer text){
		return new StringBuffer(onlyText(text.toString()));
	}

	public static String onlyText(String text){
		//remove tags html
		String s0 = removerTagsHtml(text);
		//removerLinks
		String s1 = s0.replaceAll("(https?|ftp|file)://([\\w+?\\.\\w+])+([a-zA-Z0-9\\~\\!\\@\\#\\$\\%\\^\\&amp;\\*\\(\\)_\\-\\=\\+\\\\\\/\\?\\.\\:\\;\\'\\,]*)?","");
		//remover mentions
		String s2 = s1.replaceAll("@[a-zA-Z0-9]+", "");
		//remover hashtags
		String s3 = s2.replaceAll("#[a-zA-Z0-9]+", "");
		//remover acentos
		String s4 = removerAcentos(s3);
		//remove os n�meros que restaram
		String s5 = removerNaoLetras(s4);
		
		return s5;
	}

	public static String removerAcentos (String s) {  
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < s.length(); ++i) {  
			char ch = s.charAt (i);  
			if (ch < 256) {   
				sb.append (tabela [ch]);  
			} else {  
				sb.append (ch);  
			}  
		}  
		return sb.toString();  
	}  

	public static String toConstantForm(String var) {
		String constante = removerAcentos(var.replace(" ", "_").toUpperCase());
		return constante;
	}

	public static String removerTagsHtml(String html) {
		return html.replaceAll("<.*?>", "");
	}

	public static String getStringInstant() {
		return new SimpleDateFormat("dd-MM-yyyy HH-mm").format(new Date(System.currentTimeMillis()));
	}
	
    /* remove leading whitespace */
    public static String ltrim(String source) {
        return source.replaceAll("^\\s+", "");
    }

    /* remove trailing whitespace */
    public static String rtrim(String source) {
        return source.replaceAll("\\s+$", "");
    }

    /* replace multiple whitespaces between words with single blank */
    public static String itrim(String source) {
        return source.replaceAll("\\b\\s{2,}\\b", " ");
    }

    /* remove all superfluous whitespaces in source string */
    public static String trim(String source) {
        return itrim(ltrim(rtrim(source)));
    }

    public static String lrtrim(String source){
        return ltrim(rtrim(source));
    }
    
    public StringUtil() {
	}
}


