package br.com.ifpb.AppWS.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.analysis.br.BrazilianStemFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;

public class LuceneUtil {

	public static final String[] BRAZILIAN_STOP_WORDS = {"a", "agora", "ainda", "alguEm", "algum", "alguma", "algumas", "alguns", "ampla", "amplas", "amplo", "amplos", "ante", 
		"antes", "ao", "aos", "apos", "aquela", "aquelas", "aquele", "aqueles", "aquilo", "as", "ate", "atraves", "cada", "coisa", 
		"coisas", "com", "como", "contra", "contudo", "da", "daquele", "daqueles", "das", "de", "dela", "delas", "dele", "deles", 
		"depois", "dessa", "dessas", "desse", "desses", "desta", "destas", "deste", "deste", "destes", "deve", "devem", "devendo", 
		"dever", "devera", "deverao", "deveria", "deveriam", "devia", "deviam", "disse", "disso", "disto", "dito", "diz", "dizem", 
		"do", "dos", "e", "ela", "elas", "ele", "eles", "em", "enquanto", "entre", "era", "essa", "essas", "esse", "esses", 
		"esta", "esta", "estamos", "est�o", "estas", "estava", "estavam", "est�vamos", "este", "estes", "estou", "eu", "fazendo", "fazer", 
		"feita", "feitas", "feito", "feitos", "foi", "for", "foram", "fosse", "fossem", "grande", "grandes", "h�", "isso", "isto", "j�", 
		"la", "la", "lhe", "lhes", "lo", "mas", "me", "mesma", "mesmas", "mesmo", "mesmos", "meu", "meus", "minha", "minhas", 
		"muita", "muitas", "muito", "muitos", "na", "n�o", "nas", "nem", "nenhum", "nessa", "nessas", "nesta", "nestas", "ninguem", 
		"no", "nos", "nossa", "nossas", "nosso", "nossos", "num", "numa", "nunca", "o", "os", "ou", "outra", "outras", "outro", 
		"outros", "para", "pela", "pelas", "pelo", "pelos", "pequena", "pequenas", "pequeno", "pequenos", "per", "perante", "pode", 
		"pode", "podendo", "poder", "poderia", "poderiam", "podia", "podiam", "pois", "por", "por�m", "porque", "posso", "pouca", 
		"poucas", "pouco", "poucos", "primeiro", "primeiros", "pr�pria", "proprias", "proprio", "proprios", "quais", "qual", "quando", 
		"quanto", "quantos", "que", "quem", "sao", "se", "seja", "sejam", "sem", "sempre", "sendo", "sera", "serao", "seu", "seus", "si", 
		"sido", "so", "sob", "sobre", "sua", "suas", "talvez", "tambem", "tampouco", "te", "tem", "tendo", "tenha", "ter", "teu", "teus", 
		"ti", "tido", "tinha", "tinham", "toda", "todas", "todavia", "todo", "todos", "tu", "tua", "tuas", "tudo", "ultima", "ultimas", 
		"ultimo", "ultimos", "um", "uma", "umas", "uns", "vendo", "ver", "vez", "vindo", "vir", "vos", "vos",
		
		//BRAZILIAN_INTERNET_ACRONYMNS
		
		"abs", "ae", "aew", "aff", "agr", "aham", "akela", 
		"akele", "aki", "akilo", "algm", "amr", "ants", "aqelas", "aqilo", "art", "ashuashua", "asim", "ass", 
		"ata", "atrv�s", "avia", "aw", "aww", "axar", "axo", "babak", "balear", "baleiar", "bastant", "baxo", 
		"baxtante", "bcta", "bgd", "bjs", "blz", "blza", "bm", "bns", "bora", "boral�", "brinks", "brow", 
		"btf", "bye", "c", "cara", "carai", "chau", "chauuuu", "cm", "cmg", "cmo", "cnta", "cntd", "cntro", 
		"coe", "coeh", "cole", "coleh", "crlh", "crtez", "crteza", "crtz", "ctg", "ctz", "ctza", "cya", "d", 
		"d+", "da", "daqelas", "daqele", "daqeles", "dbaxo", "debaxo", "dec", "detras", "detr�s", "dik", 
		"diz", "dmais", "dntro", "dpois", "dps", "ds", "dsc", "dxar", "eh", "end", "enqnto", "enqto", 
		"entaum", "eqipe", "esqec", "et", "ex", "exmplo", "extiveram", "fas", "fazr", "faz�", 
		"fcd", "fda", "fdac", "fik", "fikdik", "fikndo", "fikr", "fla", "flw", "fms", "fmz", "game", 
		"gent", "gnt", "grand", "grup", "gst", "haha", "hehe", "hj", "hmmm", "hoho", "hove", "hover", 
		"hovera", "hoveram", "hoveramos", "hoverao", "hoverem", "hoveremos", "hoveriam", "hoveriamos", 
		"hovesse", "hovessem", "hovessemos", "hr", "hrs", "huahua", "i", "img", "inda", "int�", "ja", "jnto", 
		"k", "kara", "karai", "kbeca", "kct", "kd", "kem", "kero", "kk", "kkk", "kkkk", "kkkkk", "kkkkkk", 
		"kkkkkkk", "koe", "koeh", "kole", "koleh", "kra", "krak", "krk", "ksa", "kse", "lg", "lok", "lol", 
		"magina", "max", "merec", "metad", "miga", "migo", "miguxa", "miguxo", "min", "miu", "mlk", "mlq", 
		"mnhas", "mo", "mont", "mrda", "ms", "mses", "msg", "msm", "mt", "mto", "my", "n", "nah", "namo", 
		"naqelas", "naqeles", "naum", "nd", "nda", "ne", "neh", "net", "ng", "ngm", "ningem", "ningm", 
		"nive", "niver", "nqto", "nr", "num", "nunk", "nvd", "obg", "off", "og", "omg", "on", "ontm", 
		"oq", "otra", "otro", "ouver", "overa", "overam", "overamos", "overao", "overem", "overemos", 
		"overiam", "overiamos", "ovesse", "ovessem", "ovessemos", "pa", "parec", "part", "pblm", "pd", 
		"pdm", "penc", "pera", "perai", "pk", "pls", "pntos", "po", "pobrema", "pobremas", "poca", "poco", 
		"pocos", "pod", "poha", "poko", "pos", "possivelmnte", "pow", "pq", "pqp", "pra", "primera", 
		"primeros", "pta", "putz", "p�", "q", "qais", "qd", "qdo", "qerem", "qeres", "qero", "qlqer", 
		"qlqr", "qm", "qnd", "qndo", "qnto", "qntos", "qq", "qr", "qrem", "qro", "qse", "qstao", "qst�o", 
		"qtd", "qtde", "qto", "qtos", "qts", "rs", "rsrs", "sab", "sdds", "sm", "smpre", "so", "soj", 
		"sort", "spera", "sta", "t+", "tampoco", "tard", "tb", "tbm", "tc", "tchau", "td", "tds", "ti", 
		"tm", "tmp", "tmpo", "tnc", "tnh", "tnham", "tnho", "to", "tow", "ta", "tao", "te", "utimo", 
		"utimos", "v6", "vamo", "vc", "vcs", "vdd", "vlr", "vlw", "vm", "vndo", "vo", "vsf", "vtnc", 
		"vzs", "x", "xata", "xato", "xau", "xpecial", "xta", "xtamos", "xtas", "xtava", "xtavam", 
		"xtavamos", "xte", "xteja", "xtejam", "xtejamos", "xteve", "xtive", "xtivemos", "xtivera", 
		"xtiveram", "xtiveramos", "xtivermos", "xtivesse", "xtivessem", "xtiveste", "xtivestes", 
		"xtiv�ssemos", "xtou", "xtas", "xtao", "n" };
	
	public static StringBuffer tokenizeString(StringBuffer text) {
		StringBuffer result = new StringBuffer();
		
		try {
			
			CharArraySet stopWords = new CharArraySet(Arrays.asList(BRAZILIAN_STOP_WORDS), true);
			
			Analyzer analyzer = new BrazilianAnalyzer(stopWords);

			TokenStream stream  = analyzer.tokenStream(null, new StringReader(text.toString()));
			stream.reset();
			
			BrazilianStemFilter filter = new BrazilianStemFilter(stream);
			
			while (filter.incrementToken()) {
				result.append(filter.getAttribute(CharTermAttribute.class).toString());
				result.append(" ");
			}
			
			filter.close();
			analyzer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public LuceneUtil() {
	}
}
