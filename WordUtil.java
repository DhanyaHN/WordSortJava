import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;


public class WordUtil
{
	static int score[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
	
	static int calc_val(String word)
	{
		int value = 0;
		for (int i = 0; i < word.length(); i++ )
		{
			char c = word.charAt(i);
			value += score[c-'a'];
		}
		return value;
	}

	static void writeToFile(List<Word> l, String path)
	{
		try
		{
 
			File file = new File(path);
 
			if (!file.exists()) 
			{
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(Word word:l)
			{
				bw.write(word.key+" "+word.value+"\n");	
			}
			bw.close(); 
			System.out.println("Done");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	static List<Word> readFile(String path)
	{
		List<Word> l=new ArrayList<Word>();
		try
		{
			Scanner sc= new Scanner(new File(path));
			while(sc.hasNext())
			{
				String s=sc.next();
				Word w=new Word(s,calc_val(s));
				l.add(w);
			}	
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return l;
	}
	
	static List<Word> wordSort(List<Word> l)
	{
		Collections.sort(l);
		return l;
	}
	public static void main(String args[])
	{
		List<Word> list = readFile("C:\\Users\\test\\workspace\\Test\\src\\sowpods.txt");
		list = wordSort(list);
		writeToFile(list, "C:\\Users\\test\\workspace\\Test\\src\\sowpods_out.txt");
	}

}

class Word implements Comparable<Word>
{
	String value;
	int key;
	public Word(String value, int key)
	{
		this.value=value;
		this.key=key;
	}
	@Override
	public int compareTo(Word o)
	{
		if(this.key == o.key)
		{
			return (this.value.compareTo(o.value));
		}
		return o.key-this.key;
	}
}