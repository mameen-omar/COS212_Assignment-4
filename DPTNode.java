
/*
Complete your details...
Name and Surname: Mohamed Ameen Omar
Student/staff Number: u16055323
*/

public class DPTNode
{
	public String[] prefix;//stores bit prefixes
	public String word;//for the final word, in string format
	public boolean isLeaf;
	public char[] bits;
	public DPTNode[] references;//references to next node

	public int[] index;//holds the index to check next in the string

	public DPTNode()
	{
		prefix = new String[3];

		for(int x =0 ; x<3;x++)
		{
			prefix[x] = "";
		}

		word = "";

		isLeaf = false;
		bits = new char[3];
		references = new DPTNode[3];

		for(int x = 0; x<3;x++)
		{
			references[x] = null;
		}

		bits[0] = '#';
		bits[1] = '0';
		bits[2] = '1';

		index = new int[3];

		for(int x = 0; x<3;x++)
		{
			index[x] = -1;
		}

	}

	//returns the index in  the bits array, for the character passed in,
	public int getIndex(char c)
	{
		if(c == '#')
		{
			return 0;
		}

		else if(c == '0')
		{
			return 1;
		}

		else if(c == '1')
		{
			return 2;
		}

		else
		{
			return 0;
		}
	}

	public boolean isLeaf()
	{
		return isLeaf;
	}

	public void setWord(String word)
	{
		this.word = word;
	}

	public void setPrefix(String prefix, int index)
	{
		this.index[index] = prefix.length();
		this.prefix[index] = prefix;
		this.isLeaf = false;
	}

	public String toString()
	{
		if(isLeaf == true)
		{
			return word;
		}

		String output = "";

		for(int x = 0; x< 3; x++)
		{
			if(references[x] != null)
			{
				output+= output == "" ? "[" + bits[x] + "]" : "[" + bits[x] + "]";
			}
		}

		return output;

	}//end of toString


	//returns the amount of non null references the current node has
	public int numReferences()
	{
		int count = 0;
		for(int x = 0; x<3;x++)
		{
			if(references[x] != null)
			{
				count++;
			}
		}

		return count;
	}

	//returns index of the non null reference
	public int nonNullReference()
	{
		int x = 0;
		for(; x< 3; x++)
		{
			if(references[x] != null)
			{
				return x;
			}
		}

		return -1;
	}
}//end of class
