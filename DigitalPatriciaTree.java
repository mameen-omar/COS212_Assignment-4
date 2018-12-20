/*
Complete your details...
Name and Surname: Mohamed Ameen Omar
Student/staff Number: u16055323
*/

/*You must implement a fully functional Digital-Patricia tree.*/
public class DigitalPatriciaTree
{

	DPTNode root;
	/*You will have to design and implement your own node classes.
	You may add any additional fields or methods but you are not
	allowed to remove or change anything you were given apart from
	implementing the methods.*/

	public DigitalPatriciaTree()
	{
		/*You may add any initialization which your
		require for your trie here.  Your default constructor
		will be used to create your tree for marking*/

		root = null;
	}

	public boolean insert(String word)
	{
		/*Insert the word passed as a parameter into the tree.
		No duplicates are allowed.  If the insert fails for
		whatever reason, you should return false. You must return
		true to indicate a successful insert.*/

		if(word == null || word == "")
		{
			return false;
		}

		word = word.toUpperCase();
		if(root == null)
		{
			root = new DPTNode();
			String binaryString = stringToBinary(word);
			int index = root.getIndex(binaryString.charAt(0));
			root.references[index] = new DPTNode();
			DPTNode child = root.references[index];
			child.isLeaf = true;
			root.prefix[index] = binaryString;

			root.index[index] = 0;
			child.word = word;
			return true;
		}

		String found = this.search(word);
		//already in the tree
		if(found.charAt(found.length() -1) != '!')
		{
			return false;
		}

		//it is not in the tree

		return insertLookup(stringToBinary(word),root,stringToBinary(word),0);

		//we need to traverse and see where to insert
	}


	//27/04/2017 - 16:33 Last Tested - WORKED
	public String search(String word)
	{
		/*Search for the word passed as a parameter.
		If the tree is empty, return an exclamation mark.
		See the specification for more details.*/
		word = word.toUpperCase();
		if(root == null)
		{
			return "!";
		}

		String binaryString = stringToBinary(word);
		String subString = "";

		String output = "";

		DPTNode node = root;

		int index = -1;

		int checkedIndex = -1;

		while(true)
		{

			if(node == null)
			{
				output+= output == "" ? "!" : ",!";
				return output;
			}

			if(node.isLeaf == true)
			{
				if(node.word.equals(word))
				{
					output += output == "" ? node.toString() : "," + node.toString();
					return output;
				}

				else
				{
					output += output == "" ? "!" : ",!";
					return output;
				}
			}//end of isLeaf check

			//check end of word
			if(node.references[0] != null && node.references[0].isLeaf == true)
			{
				DPTNode leaf = node.references[0];

				if(leaf.word.equals(word))
				{
					output += output == "" ? node.toString() : "," + node.toString();
					output += output == "" ? leaf.toString() : "," + leaf.toString();
					return output;
				}
			}

			index = node.getIndex(binaryString.charAt(checkedIndex+1));



			/*if(!subString.equals(""))
			{

				index = node.getIndex(subString.charAt(subString.length()-1));
			}*/

			//if the references next is null
			if(node.references[index] == null)
			{
				output += output == "" ? node.toString() : "," + node.toString();
				output+= output == "" ? "!" : ",!";
				return output;
			}

			int lastIndex = binaryString.length()-1;

			//if the node represents a word longer than ours
			if(node.index[index] > lastIndex)
			{
				//check current
				output += output == "" ? node.toString() : "," + node.toString();

				//check next leaf
				node = node.references[index];
				output += output == "" ? node.toString() : "," + node.toString();
				if(node.references[0] == null)
				{
					output+= output == "" ? "!" : ",!";
				}

				else
				{
					if((node.references[0].word).equals(word))
					{
						output += output == "" ? node.references[0].word : "," + node.references[0].word;
					}

					else
					{
						output+= output == "" ? "!" : ",!";
					}
				}

				return output;
			}

			//reached the end of the word to check
			if(node.index[index] == lastIndex)
			{

				output += output == "" ? node.toString() : "," + node.toString();

				node = node.references[index];

				DPTNode leaf = node.references[0];
				output += output == "" ? node.toString() : "," + node.toString();

				int tempI = node.getIndex(binaryString.charAt(lastIndex));//remove?
				//redundant??
				if(node.references[node.getIndex(binaryString.charAt(lastIndex))] != null && node.references[node.getIndex(binaryString.charAt(lastIndex))].isLeaf == true)
				{
					if(node.references[node.getIndex(binaryString.charAt(lastIndex))].word.equals(word))
					{
						output += output == "" ? node.references[node.getIndex(binaryString.charAt(lastIndex))].toString() : "," + node.references[node.getIndex(binaryString.charAt(lastIndex))].toString();
						return output;
					}
				}

				if(leaf != null)
				{
					if(leaf.word.equals(word))
					{
						output += output == "" ? leaf.toString() : "," + leaf.toString();
						return output;
					}
				}

				output+= output == "" ? "!" : ",!";
				return output;

		}

			if(node.index[index] > binaryString.length()-1)
			{
				output += output == "" ? node.toString() : "," + node.toString();
				output+= output == "" ? "!" : ",!";

				return output;
			}

			output += output == "" ? node.toString() : "," + node.toString();


			subString = binaryString.substring(0,node.index[index]+1);

			checkedIndex = node.index[index] -1;

			node = node.references[index];

		}//end of while(true)


	}

	public String remove(String word)
	{
		/*Deletes the word passed as a parameter from the tree.
		If the tree was empty, then return an exclamation mark.*/
		word = word.toUpperCase();
		if(root == null)
		{
			return "!";
		}

		String output = this.search(word);

		if(output.charAt(output.length() -1) == '!')
		{
			return output;
		}

		removeLookup(stringToBinary(word),stringToBinary(word),root,null);
		return output;
	}

	/*

				Helper Functions

	*/

	public void removeLookup(String bString, String prefix,DPTNode node, DPTNode previous)
	{
		//PREFIX IS ACTUALLY THE REST OF THE WORD THAT NEEDS TO CHECKED, ie remaining part
		int bitIndex;

		if(node == null)
		{
			return;
		}

		if(prefix.length() <= 0)
		{
			bitIndex = 0;//index for my node to check
		}
		else
		{

		  bitIndex = node.getIndex(prefix.charAt(0));//index for my node to check
		}


		if(node.references[bitIndex] == null)
		{
			return;
		}

		//next is a leaf and it is not the root
		if(node.references[bitIndex].isLeaf == true && node != this.root)
		{
			if(previous == null)
			{
				return;
			}

			DPTNode leaf = node.references[bitIndex];
			if(leaf.word.equals(binaryToString(bString)))
			{

				node.references[bitIndex] = null;
				node.index[bitIndex] = -1;
				node.prefix[bitIndex] = "";

				if(node.numReferences() == 1)
				{

					int index = 0;
					index = node.nonNullReference();//index of the only non null in the current node

					int previousIndex = -1;

					//getPreviousIndex
					for(int x= 0;x<3;x++)
					{
						if(previous.references[x] == node)
						{
							previousIndex = x;
							break;
						}
					}

					if(node.references[index].isLeaf == true)
					{

						previous.references[previousIndex] = node.references[index];
						previous.index[previousIndex] = 0;
						previous.prefix[previousIndex] = stringToBinary(previous.references[previousIndex].word);
						return;
					}

					else
					{
						previous.references[previousIndex] = node.references[index];
						previous.index[previousIndex] = node.index[index];
						previous.prefix[previousIndex] = node.prefix[index];
						return;
					}

				}
				return;
			}

			return;

		}

		//next is a leaf and it is the root
		if(node.references[bitIndex].isLeaf == true && node == this.root)
		{
			if(node.references[bitIndex].word.equals(binaryToString(bString)))
			{
				DPTNode leaf = node.references[bitIndex];

				//if the word looking for is the only one in the tree
				if(node.numReferences() == 1)
				{
					this.root = null;
					return;
				}

				node.references[bitIndex] = null;
				node.prefix[bitIndex] = "";
				node.index[bitIndex] = -1;

				/*if(node.numReferences() == 1)
				{
					int indexNext = node.nonNullReference();

					if(node.references[indexNext].isLeaf == false)
					{
						root = node.references[indexNext];

						if(root.references[0] != null && root.references[0].isLeaf == true)
						{
							String newInsert = root.references[0].word;
							root.references[0] = null;
							root.index[0] = -1;
							root.references[0].isLeaf = false;
							root.prefix[0] = "";

							insert(newInsert);

							return ;
						}
					}
				}*/

				return;
			}

			return;

		}

		prefix = bString.substring(node.index[bitIndex]);

		removeLookup(bString,prefix,node.references[bitIndex],node);

	}

	public boolean insertLookup(String bString, DPTNode node, String prefixString, int stringIndex)
	{
		//System.out.println(node.toString());

		if(node == null)
		{
			return false;
		}

		int bitIndex = node.getIndex(bString.charAt(stringIndex));//get bit index

		if(node.references[bitIndex] == null)
		{

			DPTNode insertNode = new DPTNode();
			insertNode.isLeaf = true;
			insertNode.word = binaryToString(bString);
			node.prefix[bitIndex] = bString;
			node.index[bitIndex] = 0;
			node.references[bitIndex] = insertNode;

			return true;
		}

		/*if(prefixString.length() <= 1)
		{
			if(node.references[0] != null)
			{
				return false;
			}

			DPTNode insertNode = new DPTNode();
			insertNode.isLeaf = true;
			insertNode.word = binaryToString(bString);
			node.index[0] = 0;
			node.references[0] = insertNode;
			node.prefix[0] = bString;


			return true;
		}*/

		int lastEqui = compareString(node.prefix[bitIndex], bString);


		if(lastEqui < node.index[bitIndex]-1)
		{
			if(lastEqui == bString.length() -1)
			{
				node = node.references[bitIndex];
				node.references[0] = new DPTNode();
				node.references[0].isLeaf = true;
				node.index[0] = 0;
				node.references[0].word = binaryToString(bString);
				node.prefix[0] = bString;
				return true;
			}
			//System.out.println(node.prefix[bitIndex]);
			//System.out.println(lastEqui);

			DPTNode insertNode = new DPTNode();
			insertNode.isLeaf= false;


			int i = node.getIndex(node.prefix[bitIndex].charAt(lastEqui+1));

			//for(int x = 0; x<3;x++)
			//{
				//insertNode.references[] = node.references[x];
				//insertNode.prefix[x] = node.prefix[x];
				//insertNode.index[x] = node.index[x];
			//}

			insertNode.references[i] = node.references[bitIndex];
			insertNode.prefix[i] = node.prefix[bitIndex];
			insertNode.index[i] = node.index[bitIndex];


			node.index[bitIndex] = lastEqui+1;
			node.prefix[bitIndex] = node.prefix[bitIndex].substring(0,lastEqui+1);
			node.references[bitIndex] = insertNode;


			i = node.getIndex(bString.charAt(lastEqui+1));
			//System.out.println(i);
			insertNode.prefix[i] = bString;

			insertNode.index[i] = 0;
			insertNode.references[i] = new DPTNode();
			insertNode.references[i].isLeaf = true;
			insertNode.references[i].word = binaryToString(bString);

			return true;
		}

		if(node.references[bitIndex].isLeaf == true)
		{

			DPTNode insertNode = new DPTNode();

			insertNode.isLeaf= false;

			String leafString = stringToBinary(node.references[bitIndex].word);

			int lastEquivalentIndex = compareString(leafString, bString);

			node.index[bitIndex] = lastEquivalentIndex +1;

			node.references[bitIndex] = insertNode;

			node.prefix[bitIndex] = bString.substring(0,lastEquivalentIndex+1);



			//if one word is a full prefix of the other
			if(lastEquivalentIndex == leafString.length() -1 || lastEquivalentIndex == bString.length() -1)
			{


				if(leafString.length() > bString.length())
				{
					String swapS = bString;
					bString = leafString;
					leafString = swapS;
				}

				//leaf string holds the string we need to make the end

				insertNode.index[0] = 0;
				insertNode.references[0] = new DPTNode();
				insertNode.references[0].isLeaf = true;
				insertNode.references[0].word = binaryToString(leafString);
				insertNode.prefix[0] = leafString;

				bitIndex = node.getIndex(bString.charAt(lastEquivalentIndex+1));
				insertNode.index[bitIndex] = 0;
				insertNode.prefix[bitIndex] = bString;
				insertNode.references[bitIndex] = new DPTNode();
				insertNode.references[bitIndex].isLeaf = true;
				insertNode.references[bitIndex].word = binaryToString(bString);


				return true;

			}

			//next node is a leaf and not full word is a prefix of the other CHECK THIS AGAIN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			else
			{


				insertNode.isLeaf= false;
				int checkIndex = lastEquivalentIndex+1;
				bitIndex = node.getIndex(bString.charAt(lastEquivalentIndex+1));

				insertNode.index[bitIndex] = 0;
				insertNode.prefix[bitIndex] = bString;
				insertNode.references[bitIndex] = new DPTNode();
				insertNode.references[bitIndex].isLeaf = true;
				insertNode.references[bitIndex].word = binaryToString(bString);

				bitIndex = node.getIndex(leafString.charAt(lastEquivalentIndex+1));

				insertNode.index[bitIndex] = 0;
				insertNode.references[bitIndex] = new DPTNode();
				insertNode.references[bitIndex].isLeaf = true;
				insertNode.prefix[bitIndex] = leafString;
				insertNode.references[bitIndex].word = binaryToString(leafString);

			}

			return true;
		}//end of full case

		//next to check is greater than our string's last index
		if(node.index[bitIndex] > bString.length() -1)
		{

			DPTNode insertNode = new DPTNode();
			String oldPrefix = node.prefix[bitIndex];

			int nextIndex = compareString(bString, oldPrefix)+1 ;//next index to check in current node
			if(oldPrefix.equals(bString) )
			{
				node = node.references[bitIndex];
				node.index[0] = 0;
				node.references[0] = insertNode;
				insertNode.isLeaf = true;
				insertNode.word = binaryToString(bString);
				node.prefix[0] = bString;
				return true;
			}
			node.index[bitIndex] = nextIndex;


			int curIndex = compareString(bString, oldPrefix);
			node.prefix[bitIndex] = oldPrefix.substring(0,curIndex+1);
			curIndex = node.getIndex(bString.charAt(nextIndex));//bit index
			insertNode.references[curIndex] = new DPTNode();
			insertNode.index[curIndex] = 0;
			insertNode.prefix[curIndex] = bString;
			insertNode.references[curIndex].isLeaf = true;
			insertNode.references[curIndex].word = binaryToString(bString);
			curIndex = node.getIndex(oldPrefix.charAt(nextIndex));
			insertNode.references[curIndex] = node.references[bitIndex];
			insertNode.prefix[curIndex] = oldPrefix;
			insertNode.index[curIndex] = oldPrefix.length();
			node.references[bitIndex] = insertNode;
			return true;
		}


		stringIndex = node.index[bitIndex];
		node = node.references[bitIndex];
		prefixString = bString.substring(0,stringIndex+1);

		//System.out.println("FUCKMESideWays");
		return insertLookup(bString, node, prefixString, stringIndex);


	}

	//returns the last index for which the strings are equal
	public int compareString(String one, String two)
	{
		int x = 0;

		while(x<one.length() && x < two.length())
		{
			if(one.charAt(x) != two.charAt(x))
			{
				return x-1;
			}

			x++;
		}

		return x-1;
	}

	/*

				Binary Conversions

	*/

	//Takes a String parameter and returns the binary equivalent of the string
	private String stringToBinary(String temp)
	{
		int count = 0;

		String binary;

		binary = charToBinary(temp.charAt(count));

		count++;

		while(count < temp.length())
		{
			binary += charToBinary(temp.charAt(count));
			count++;
		}

		return binary;

	}//WORKS


	//takes a character parameter and returns the binary equaivalent of the character
	private String charToBinary(char temp)
	{
		int value = ((int)temp) - 65; //convert to ascii then get code, 'A' = 0

		String binaryString = Integer.toBinaryString(value);

		//convert to binary consisting of 5 bits
    while (binaryString.length() < 5)
		{
       binaryString = "0" + binaryString;
    }
    return binaryString;

	}//WORKS

	//takes in a Binary string and returns the character string equivalent
	private String binaryToString(String arg)
	{
		String temp = "";

		int count = 0;

		String output = "";

		while(count < arg.length())
		{
			temp = arg.substring(count, count+5);//create substring
			output += binaryToChar(temp);//convert to char and add to the string
			count +=5;
		}

		return output;
	}//WORKS

	//takes in a binary string and returns the character equivalent as a String
	private String binaryToChar(String arg)
	{
		int asciiInt = Integer.parseInt(arg, 2);//convert from binary to decimals

		char character = (char)(asciiInt + 65); //convert to actual ASCII and convert to char

		return Character.toString(character);
	}//WORKS



}
